(function () {
    'use strict';

    angular.module('app')
        .controller('DashboardGeneralCtrl', ['$scope', '_', '$timeout', 'ec', 'DashbordGeneralSerive', 'contChartService', DashboardGeneralCtrl])

    function DashboardGeneralCtrl($scope, _, $timeout, ec, DashbordGeneralSerive, contChartService) {

        var dateD, dateF,
            askForPromise,
            askForPromiseCollecte,
            askForPromiseNettoiement,
            askForPromiseTicketCountbyArrondissement,
            askForPromiseTicketCountbyDate,
            askForOverDelayTicketsCountPromise,
            askForOnDelayTicketsCountPromise, askForOpenTicketsCountPromise, askForClosedTicketsCountPromise;
        const DATE_FORMAT = "YYYY-MM-DD";
        const OVER_DELAY_CONST = "HORS_DELAI";
        const ON_DELAY_CONST = "EN_DELAI";

        $scope.datePicker = {
            startDate: moment().subtract(0, "days"),
            endDate: moment()
        };

        var dateD = $scope.datePicker.startDate.format(DATE_FORMAT)
        var dateF = $scope.datePicker.endDate.format(DATE_FORMAT);

        $scope.applyDate = function (ev, picker) {
            dateD = $scope.datePicker.startDate.format(DATE_FORMAT);
            dateF = $scope.datePicker.endDate.format(DATE_FORMAT);
            console.log('toto');
            console.log(dateD);
            console.log(dateF);
            initchart();

        }
        /*
         $timeout(function(){
         ec.showLoading(['chart1','chart2','chart3','chart4']);
         ec.connect(['chart2','chart3']);
         });

         $timeout(function(){
         ec.hideLoading('chart1');
         },1000);

         $timeout(function(){
         ec.hideLoading(['chart2','chart3']);
         },2500);

         $timeout(function(){
         ec.hideLoading('chart4');
         },3500);
         */
        ec.addMap("Ard", "data/ar.json");
        initchart();


        function initchart() {

            askForPromise = DashbordGeneralSerive.getPromise(dateD, dateF);
            askForPromiseCollecte = DashbordGeneralSerive.getPromisecollecte(dateD, dateF);
            askForPromiseNettoiement = DashbordGeneralSerive.getPromiseNettoiement(dateD, dateF);
            askForPromiseTicketCountbyArrondissement = DashbordGeneralSerive.getPromiseTicketCountbyArrondissement(dateD, dateF);
            askForPromiseTicketCountbyDate = DashbordGeneralSerive.getPromiseTicketCountbyDate(dateD, dateF);
            askForOverDelayTicketsCountPromise = DashbordGeneralSerive.getTicketsCountByDelayStateKPIPromise(dateD, dateF, OVER_DELAY_CONST);
            askForOnDelayTicketsCountPromise = DashbordGeneralSerive.getTicketsCountByDelayStateKPIPromise(dateD, dateF, ON_DELAY_CONST);
            askForOpenTicketsCountPromise = DashbordGeneralSerive.getOpenTicketsCountKPIPromise(dateD, dateF);
            askForClosedTicketsCountPromise = DashbordGeneralSerive.getCloseTicketsCountKPIPromise(dateD, dateF);

            askForOpenTicketsCountPromise.then(
                // OnSuccess function
                function (answer) {
                    $scope.openTicketsCountKPI = answer[0].count + answer[1].count;
                    $scope.sitaOpenTicketsCountKPI = answer[0].count;
                    $scope.sita = answer[0].name;
                    $scope.averdaOpenTicketsCountKPI = answer[1].count;
                    $scope.averda = answer[1].name;
                },
                // OnFailure function
                function (reason) {
                    $scope.name = reason;

                }
            );

            askForClosedTicketsCountPromise.then(
                // OnSuccess function
                function (answer) {
                    $scope.closeTicketsCountKPI = answer[0].count + answer[1].count;
                    $scope.sitaCloseTicketsCountKPI = answer[0].count;
                    $scope.sita = answer[0].name;
                    $scope.averdaCloseTicketsCountKPI = answer[1].count;
                    $scope.averda = answer[1].name;
                },
                // OnFailure function
                function (reason) {
                    $scope.name = reason;

                }
            );

            askForOnDelayTicketsCountPromise.then(
                // OnSuccess function
                function (answer) {
                    $scope.onDelayTicketsCountKPI = answer[0].count + answer[1].count;
                    $scope.sitaOnDelayTicketsCountKPI = answer[0].count;
                    $scope.sita = answer[0].name;
                    $scope.averdaOnDelayTicketsCountKPI = answer[1].count;
                    $scope.averda = answer[1].name;
                },
                // OnFailure function
                function (reason) {
                    $scope.name = reason;

                }
            );
            askForOverDelayTicketsCountPromise.then(
                // OnSuccess function
                function (answer) {
                    $scope.overDelayTicketsCountKPI = answer[0].count + answer[1].count;
                    $scope.sitaOverDelayTicketsCountKPI = answer[0].count;
                    $scope.averdaOverDelayTicketsCountKPI = answer[1].count;
                    $scope.sita = answer[0].name;
                    $scope.averda = answer[1].name;
                },
                // OnFailure function
                function (reason) {
                    $scope.name = reason;

                }
            );

            askForPromise.then(
                // OnSuccess function
                function (answer) {
                    $scope.option1 = contChartService.getOptionChartraddar(answer);
                },
                // OnFailure function
                function (reason) {
                    $scope.name = reason;

                }
            );

            askForPromiseCollecte.then(
                // OnSuccess function
                function (answer) {

                    $scope.option2 = contChartService.getOptionChartraddar(answer);
                },
                // OnFailure function
                function (reason) {
                    $scope.name = reason;

                }
            );

            askForPromiseNettoiement.then(
                // OnSuccess function
                function (answer) {

                    $scope.option3 = contChartService.getOptionChartraddar(answer);
                },
                // OnFailure function
                function (reason) {
                    $scope.name = reason;

                }
            );
            askForPromiseTicketCountbyArrondissement.then(
                // OnSuccess function
                function (answer) {

                    $scope.option5 = contChartService.getOptionChartMap(answer);

                },
                // OnFailure function
                function (reason) {
                    $scope.name = reason;

                }
            );
            askForPromiseTicketCountbyDate.then(
                // OnSuccess function
                function (answer) {
                    $scope.option4 = contChartService.getOptionChartBarline(answer);
                },
                // OnFailure function
                function (reason) {
                    $scope.name = reason;

                }
            );
        };
    }


})();