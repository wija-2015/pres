/**
 * Created by abenouhoud on 24/03/2016.
 */

(function () {
    'use strict';
    angular.module('app').service('contChartService', ['_', contChartService])
    function contChartService(_) {
        var dates = [],  optionsChartBarLine;



        function ConstruireIndicator_Chartraddar(answer, _Axe_Y) {
            // {text: 'sales', max: 6000},
            var _Indicator_Chart = [];
            for (var i = 0; i < _Axe_Y.length; i++) {
                var list = _.where(answer, {axe_y: _Axe_Y[i]})
                _Indicator_Chart.push({
                    text: _Axe_Y[i], max: (_.max(answer, function (answer) {
                        return answer.data;
                    })).data
                });
            }
            return _Indicator_Chart;
        };
        function ConstruireData_Chartraddar(answer, _Axe_Y, _Axe_X) {
            var _Data_Chart = [];
            /*
             data: [
             {
             value: [4300, 10000, 28000, 35000, 50000, 19000],
             name: Axe_X[0]
             },
             {
             value: [5000, 14000, 28000, 31000, 42000, 21000],
             name: Axe_X[1]
             }
             ]
             */
            for (var i = 0; i < _Axe_X.length; i++) {
                var _valuel = [], _list, _value = [];
                _list = _.where(answer, {axe_x: _Axe_X[i]});
                for (var j = 0; j < _Axe_Y.length; j++) {
                    _valuel.push(_.findWhere(_list, {axe_y: _Axe_Y[j]}));
                    if (_valuel[j] != undefined)
                        _value.push(_valuel[j].data);
                    else
                        _value.push(0);

                }
                _Data_Chart.push({value: _value, name: _Axe_X[i]});
            }
            return _Data_Chart;

        };
        function getAxe_X(answer) {
            var _Axe_X = [];
            for (var i = 0; i < answer.length; i++) {
                if (_Axe_X.indexOf(answer[i].axe_x) === -1) {
                    _Axe_X.push(answer[i].axe_x);
                }
            }
            return _Axe_X;

        };
        function getAxe_Y(answer) {
            var _Axe_Y = [];
            for (var i = 0; i < answer.length; i++) {
                if (_Axe_Y.indexOf(answer[i].axe_y) === -1) {
                    _Axe_Y.push(answer[i].axe_y);
                }
            }
            return _Axe_Y;

        };
        function getAxe_YDate() {
            dates = [];
            const DATE_FORMAT = "YYYY-MM-DD";
            var dateRange = 15;
            for (var i = 0; i <= dateRange; i++)
                dates.push(moment().subtract(dateRange - i, "days").format(DATE_FORMAT));
            console.log("dates");
            console.log(dates);
            return dates;
        };
        function getsSrieChartComb(answer, _axe_X, _axe_Y) {
            var series = [], color;
            for (var i = 0, l = _axe_X.length; i < l; i++) {
                if (_axe_X[i] == 'AVERDA Casa') {
                    color = '#1e6bbb';
                } else {
                    color = '#69b03a';
                }
                var serie = {
                    name: _axe_X[i],
                    type: 'bar',
                    stack: _axe_X[i] + '_sum',
                    barCategoryGap: '50%',
                    itemStyle: {
                        normal: {
                            color: color,
                            barBorderColor: color,
                            barBorderWidth: 6,
                            barBorderRadius: 0,
                            label: {
                                show: true, position: 'insideTop'
                            }
                        }
                    },
                    data: getdataseriesbarcombine(answer, _axe_X[i], 1, _axe_Y)
                };
                var serie2 = {
                    name: _axe_X[i] + ' Ouvert',
                    type: 'bar',
                    stack: _axe_X[i] + '_sum',
                    barCategoryGap: '50%',
                    itemStyle: {
                        normal: {
                            color: '#fff',
                            barBorderColor: color,
                            barBorderWidth: 6,
                            barBorderRadius: 0,
                            label: {
                                show: true,
                                position: 'top',
                                formatter: function (params) {
                                    for (var i = 0, l = optionsChartBarLine.xAxis[0].data.length; i < l; i++) {
                                        if (optionsChartBarLine.xAxis[0].data[0] == params.name) {
                                            return optionsChartBarLine.series[0].data[i] + params.value;
                                        }
                                    }
                                },
                                textStyle: {
                                    color: color
                                }
                            }
                        }
                    },
                    data: getdataseriesbarcombine(answer, _axe_X[i], 2, _axe_Y)
                };
                series.push(serie);
                series.push(serie2);
            }

            return series;
        };
        function getdataseriesbarcombine(answer, _axe_X, Grp, _axe_Y) {
            var _list = _.where(answer, {axe_x: _axe_X});
            var value = [];
            console.log("list");
            console.log(_list);
            for (var i = 0; i <= dates.length; i++) {
                if (_.contains(_axe_Y, dates[i])) {
                    if (_.findWhere(_list, {axe_y: dates[i]}) != undefined) {

                        if (Grp == 1) {
                            value.push((_.findWhere(_list, {axe_y: dates[i]})).data);
                        }
                        else {
                            value.push((_.findWhere(_list, {axe_y: dates[i]})).data2);
                        }
                    } else {
                        value.push(0);
                    }
                }
                else {
                    value.push(0);
                }
            }
            console.log("value");
            console.log(value);
            return value;

        }

        //ChartRaddar
        this.getOptionChartraddar = function (answer) {
            var _axe_X = getAxe_X(answer), _axe_Y = getAxe_Y(answer);

            var optionsChart = {
                animation: true,
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    orient: 'vertical',
                    x: 'right',
                    y: 'bottom',
                    data: _axe_X
                },
                toolbox: {
                    show: false,
                    feature: {
                        restore: {show: true, title: "restore"},
                        saveAsImage: {show: true, title: "save as image"}
                    }
                },
                polar: [
                    {
                        indicator: ConstruireIndicator_Chartraddar(answer, _axe_Y)
                    }
                ],
                calculable: true,
                series: [
                    {
                        name: 'Budget vs spending',
                        type: 'radar',
                        data: ConstruireData_Chartraddar(answer, _axe_Y, _axe_X)
                    }
                ]
            };
            return optionsChart;
        };

        //ChartMap
        this.getOptionChartMap = function (answer) {
            console.log("AA");
            console.log(answer);

            var optionsChart = {
                tooltip: {
                    trigger: 'item',
                    showDelay: 0,
                    transitionDuration: 0.2,
                    formatter: function (params) {
                        console.log("Anass")
                        console.log(params.value);
                        var value = (params.value + '').split('.');
                        value = value[0].replace(/(\d{1,3})(?=(?:\d{3})+(?!\d))/g, '$1,');
                        return params.name + ' : ' + params.value + ' constats';
                    }
                },
                dataRange: {
                    x: 'right',
                    min: (_.min(answer, function (answer) {
                        return answer.value;
                    })).value,
                    max: (_.max(answer, function (answer) {
                        return answer.value;
                    })).value,
                    color: ['orangered', 'yellow', 'lightskyblue'],
                    text: ['Max', 'Min'],
                    calculable: true
                },
                toolbox: {
                    show: true,
                    //orient : 'vertical',
                    x: 'left',
                    y: 'top',
                    feature: {
                        mark: {show: true},
                        dataView: {show: true, readOnly: false},
                        restore: {show: true},
                        saveAsImage: {show: true}
                    }
                },
                series: [
                    {
                        name: 'Constat par arrondissement',
                        type: 'map',
                        roam: true,
                        mapType: 'Ard',
                        itemStyle: {
                            normal: {label: {show: true}},
                            emphasis: {label: {show: true}}
                        },


                        data: answer
                    }
                ]
            };
            return optionsChart;
        };
        //ChartBar&Line

        this.getOptionChartBarline = function (answer) {
            var _axe_X = getAxe_X(answer), _axe_Y = getAxe_Y(answer);

            optionsChartBarLine = {
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow'        // 'line' | 'shadow'
                    },
                    formatter: function (params) {
                        return params[0].name + '<br/>'
                            + params[0].seriesName + ' : ' + params[0].value + '<br/>'
                            + params[1].seriesName + ' : ' + (params[1].value + params[0].value) + '<br/>'
                            + params[2].seriesName + ' : ' + params[2].value + '<br/>'
                            + params[3].seriesName + ' : ' + (params[3].value + params[0].value);
                    }
                },
                legend: {
                    selectedMode: false,
                    data: _axe_X
                },
                toolbox: {
                    show: true,
                    feature: {
                        mark: {show: true},
                        dataView: {show: true, readOnly: false},
                        restore: {show: true},
                        saveAsImage: {show: true}
                    }
                },
                calculable: true,
                xAxis: [
                    {
                        type: 'category',
                        data: getAxe_YDate()
                    }
                ],
                yAxis: [
                    {
                        type: 'value',
                        boundaryGap: [0, 0.1]
                    }
                ],
                series: getsSrieChartComb(answer, _axe_X, _axe_Y)

            };
            return optionsChartBarLine;

        };


    }

})();
