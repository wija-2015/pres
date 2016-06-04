/**
 * Created by abenouhoud on 21/03/2016.
 */
(function () {
    'use strict';
    angular.module('app').service('DashbordGeneralSerive', ['$http', '$q', DashbordGeneralSerive])

    function DashbordGeneralSerive($http, $q) {

        this.getPromise = function (dateD,dateF) {
            var deferObject;
            var promise = $http({
                method: 'GET',
                url: 'data/data.json',
                //url: 'http://rest-service.guides.spring.io/greeting',
                headers: {
                    'Content-Type': 'application/json; charset=utf-8'
                }

            })
            deferObject = deferObject || $q.defer();

            promise.then(
                // OnSuccess function
                function (answer) {
                    // This code will only run if we have a successful promise.
                    deferObject.resolve(answer.data);
                    var item = answer.data;
                    return item;
                },
                // OnFailure function
                function (reason) {
                    // This code will only run if we have a failed promise.
                    deferObject.reject(reason);
                });

            return deferObject.promise;
        };
        this.getPromisecollecte = function (dateD,dateF) {
            var deferObjectCollecte;
            var promise = $http({
                method: 'GET',
                url: 'data/collecte.json',
                //url: 'http://rest-service.guides.spring.io/greeting',
                headers: {
                    'Content-Type': 'application/json; charset=utf-8'
                }

            })
            deferObjectCollecte = deferObjectCollecte || $q.defer();

            promise.then(
                // OnSuccess function
                function (answer) {
                    // This code will only run if we have a successful promise.
                    deferObjectCollecte.resolve(answer.data);
                    var item = answer.data;
                    return item;
                },
                // OnFailure function
                function (reason) {
                    // This code will only run if we have a failed promise.
                    deferObjectCollecte.reject(reason);
                });

            return deferObjectCollecte.promise;
        };
        this.getPromiseNettoiement = function (dateD,dateF) {
            var deferObjectNettoiement;
            var promise = $http({
                method: 'GET',
                url: 'data/nettoiement.json',
                //url: 'http://rest-service.guides.spring.io/greeting',
                headers: {
                    'Content-Type': 'application/json; charset=utf-8'
                }

            })
            deferObjectNettoiement = deferObjectNettoiement || $q.defer();

            promise.then(
                // OnSuccess function
                function (answer) {
                    // This code will only run if we have a successful promise.
                    deferObjectNettoiement.resolve(answer.data);
                    var item = answer.data;
                    return item;
                },
                // OnFailure function
                function (reason) {
                    // This code will only run if we have a failed promise.
                    deferObjectNettoiement.reject(reason);
                });

            return deferObjectNettoiement.promise;
        };
        this.getPromiseTicketCountbyArrondissement = function (dateD,dateF) {
            var deferObjectTicketCountbyArrondissement;
            var promise = $http({
                method: 'GET',
                url: 'data/Ar_data.json',
                //url: 'http://rest-service.guides.spring.io/greeting',
                headers: {
                    'Content-Type': 'application/json; charset=utf-8'
                }

            })
            deferObjectTicketCountbyArrondissement = deferObjectTicketCountbyArrondissement || $q.defer();

            promise.then(
                // OnSuccess function
                function (answer) {
                    // This code will only run if we have a successful promise.
                    deferObjectTicketCountbyArrondissement.resolve(answer.data);
                    var item = answer.data;
                    return item;
                },
                // OnFailure function
                function (reason) {
                    // This code will only run if we have a failed promise.
                    deferObjectTicketCountbyArrondissement.reject(reason);
                });

            return deferObjectTicketCountbyArrondissement.promise;
        };
        this.getPromiseTicketCountbyDate = function (dateD,dateF) {
            var deferObjectTicketCountbyDate;
            var promise = $http({
                method: 'GET',
                url: 'data/EvolutionConstats.json',
                //url: 'http://rest-service.guides.spring.io/greeting',
                headers: {
                    'Content-Type': 'application/json; charset=utf-8'
                }

            });
            deferObjectTicketCountbyDate = deferObjectTicketCountbyDate || $q.defer();

            promise.then(
                // OnSuccess function
                function (answer) {
                    // This code will only run if we have a successful promise.
                    deferObjectTicketCountbyDate.resolve(answer.data);
                    var item = answer.data;
                    return item;
                },
                // OnFailure function
                function (reason) {
                    // This code will only run if we have a failed promise.
                    deferObjectTicketCountbyDate.reject(reason);
                });

            return deferObjectTicketCountbyDate.promise;
        };

        this.getTicketsCountByDelayStateKPIPromise = function (dateDebut,dateFin,DelayState) {
            var deferObjectOD;
            var promise = $http({
                method: 'POST',
                url: 'http://10.0.50.173:8080/acpServer/rest/tbsr/closedticketcountbydelaystate?firstDate='+dateDebut+'&secondDate='+dateFin+'&delayState='+DelayState,
                //url: 'http://rest-service.guides.spring.io/greeting',
                headers: {
                    'Content-Type': 'application/json; charset=utf-8'
                }

            })
            deferObjectOD = deferObjectOD || $q.defer();

            promise.then(
                // OnSuccess function
                function (answer) {
                    // This code will only run if we have a successful promise.
                    deferObjectOD.resolve(answer.data);
                    var item = answer.data;
                    return item;

                },
                // OnFailure function
                function (reason) {
                    // This code will only run if we have a failed promise.
                    deferObjectOD.reject(reason);
                });

            return deferObjectOD.promise;
        };
        this.getOpenTicketsCountKPIPromise = function (dateDebut,dateFin) {
            var deferObjectOD;
            var promise = $http({
                method: 'POST',
                url: 'http://localhost:8080/acpServer/rest/tbsr/openticketcountbydelegate?firstDate='+dateDebut+'&secondDate='+dateFin,
                //url: 'http://rest-service.guides.spring.io/greeting',
                headers: {
                    'Content-Type': 'application/json; charset=utf-8'
                }

            })
            deferObjectOD = deferObjectOD || $q.defer();

            promise.then(
                // OnSuccess function
                function (answer) {
                    // This code will only run if we have a successful promise.
                    deferObjectOD.resolve(answer.data);
                    var item = answer.data;
                    return item;

                },
                // OnFailure function
                function (reason) {
                    // This code will only run if we have a failed promise.
                    deferObjectOD.reject(reason);
                });

            return deferObjectOD.promise;
        };
        this.getCloseTicketsCountKPIPromise = function (dateDebut,dateFin) {
            var deferObjectOD;
            var promise = $http({
                method: 'POST',
                url: 'http://localhost:8080/acpServer/rest/tbsr/closeticketcountbydelegate?firstDate='+dateDebut+'&secondDate='+dateFin,
                //url: 'http://rest-service.guides.spring.io/greeting',
                headers: {
                    'Content-Type': 'application/json; charset=utf-8'
                }

            })
            deferObjectOD = deferObjectOD || $q.defer();

            promise.then(
                // OnSuccess function
                function (answer) {
                    // This code will only run if we have a successful promise.
                    deferObjectOD.resolve(answer.data);
                    var item = answer.data;
                    return item;

                },
                // OnFailure function
                function (reason) {
                    // This code will only run if we have a failed promise.
                    deferObjectOD.reject(reason);
                });

            return deferObjectOD.promise;
        };


    };
})();