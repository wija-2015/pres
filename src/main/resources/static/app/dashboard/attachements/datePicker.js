var app = angular.module('plunker', []);

app.controller('MainCtrl', function($scope) {
  $scope.var1 = '2013-07';
});


app.directive('datetimez', function() {
    return {
        restrict: 'A',
        require : 'ngModel',
        link: function(scope, element, attrs, ngModelCtrl) {
          element.datetimepicker({
           format: "yyyy-MM",
           viewMode: "months", 
            minViewMode: "months",
              pickTime: false,
          }).on('changeDate', function(e) {
            ngModelCtrl.$setViewValue(e.date);
            scope.$apply();
          });
        }
    };
});
