/**
 * Created by abenouhoud on 17/03/2016.
 */

(function () {
    'use strict';

    angular.module('app')
        .controller('TableauBordCtrl', ['$scope',"itemProvider",TableauBordCtrl])
    function TableauBordCtrl($scope, itemProvider) {

        var askForPromise = itemProvider.getPromise();

        askForPromise.then(
            // OnSuccess function
            function(answer) {
                $scope.name = answer;

            },
            // OnFailure function
            function(reason) {
                $scope.name = reason;

            }
        )


    }
})();
