/**
 * Created by abenouhoud on 21/03/2016.
 */
(function () {
    'use strict';
    angular.module('app').service('ConsultBalayageSitaService', ['$http', '$q', consultBalayageSitaService])

    function consultBalayageSitaService($http, $q) {
        
        this.getTwoDates = function (dateDebut,dateFin) {
            var deferForTest;
            var promise = $http({
                method: 'POST',
                url: 'http://localhost:8080/balayage/consultBalayagesSita?firstDate='+dateDebut+'&lastDate='+dateFin,
                headers: {
                    'Content-Type': 'application/json; charset=utf-8'
                }

            })
            deferForTest = deferForTest || $q.defer();
            promise.then(
                 function (answer) {
                    deferForTest.resolve(answer.data);
                    var item = answer.data;
                    return item;

                },
                function (reason) {
                    deferForTest.reject(reason);
                });

            return deferForTest.promise;
        };
    };
})();