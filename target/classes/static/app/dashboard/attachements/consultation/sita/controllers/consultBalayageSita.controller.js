(function () {
    'use strict';

    angular.module('app')
        .controller('ConsultBalayageSitaCtrl', ['$scope', '_', '$timeout', 'ec', 'ConsultBalayageSitaService', consultBalayageSitaCtrl])

    function consultBalayageSitaCtrl($scope, _, $timeout, ec, ConsultBalayageSitaService) {
        var dateD, dateF,askForTestController;
        const DATE_FORMAT = "YYYY-MM-DD";
        const OVER_DELAY_CONST = "HORS_DELAI";
        const ON_DELAY_CONST = "EN_DELAI";

        $scope.datePicker = {
            startDate: moment().subtract(0, "days"),
            endDate: moment()
        };

        var dateD = $scope.datePicker.startDate.format(DATE_FORMAT);
        var dateF = $scope.datePicker.endDate.format(DATE_FORMAT);

        $scope.applyDate = function (ev, picker) {
            dateD = $scope.datePicker.startDate.format(DATE_FORMAT);
            dateF = $scope.datePicker.endDate.format(DATE_FORMAT);
            console.log('toto');
            console.log(dateD);
            console.log(dateF);
            initchart();

        }
        initchart();


        function initchart() {

            askForTestController=ConsultBalayageSitaService.getTwoDates(dateD,dateF);
            
            askForTestController.then(
                    function (answer) {
                        $scope.result=answer;
                        console.log(answer);
                    },
                    function (reason) {
                        $scope.name = reason;

                    }
                );

        };
    }

})();