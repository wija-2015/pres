/**
 * Created by abenouhoud on 17/03/2016.
 */

(function () {
    'use strict';
    angular.module('app').factory('itemProvider', ['$http', '$q', itemProvider])

    function itemProvider($http, $q) {

        var deferObject,

            myMethods = {


                getPromise: function () {
                    var promise = $http({
                        method: 'GET',
                        //url:'http://158.69.28.16:8080/acpServer/rest/connexion/check'
                        //url: 'http://10.0.50.151:8080/acpServer/rest/account/connect?matricule=mehdi&password=7f7d49795dcf0a82605fb1103ed20d28',
                         url: 'http://rest-service.guides.spring.io/greeting',
                        //url: 'http://10.0.50.68:8080/greeting',
                        //data: JSON.stringify(data),
                       // url: 'http://10.0.50.68:8080/acpServer/rest/test/allAccounts',
                        //url: 'http://10.0.50.68:8080/acpServer/rest/test/greeting'

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
                }
            };

        return myMethods;
    }
})();