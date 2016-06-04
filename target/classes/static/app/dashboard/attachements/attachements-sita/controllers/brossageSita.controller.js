(function() {
	'use strict';

	angular.module('app').controller(
			'BalayageSitaCtrl',
			[ '$scope', '$http', '$filter', '_', 'BalayageSitaSerive',
					'contChartService', balayageSitaCtrl ]).directive(
			'datetimez', function() {
				return {
					restrict : 'A',
					require : 'ngModel',
					link : function(scope, element, attrs, ngModelCtrl) {
						element.datetimepicker({
							format : "yyyy-MM",
							viewMode : "months",
							minViewMode : "months",
							pickTime : false,
						}).on('changeDate', function(e) {
							ngModelCtrl.$setViewValue(e.date);
							scope.$apply();
						});
					}
				};
			});

	function balayageSitaCtrl($scope, $http, $filter, _, BalayageSitaSerive,
			contChartService) {
		$scope.date = "2016-02-01 00:00:00";
		$scope.tabDaysOfMonth = [];

		$scope.$watch('date', function(newVal, oldVal) {
			newVal = $filter('date')(newVal, "yyyy-MM-dd hh:mm:ss");
			oldVal = $filter('date')(oldVal, "yyyy-MM-dd hh:mm:ss");
			console.log(newVal, oldVal);

			// Liste des jours du mois
			BalayageSitaSerive.getPromiseTabDaysOfMonth(newVal).then(
					function(answer) {
						$scope.tabDaysOfMonth = answer;
					}, function(reason) {
						$scope.name = reason;
					});

			// Liste des balayages
			BalayageSitaSerive.getPromiseBalayageSita(newVal).then(
					function(answer) {
						$scope.balayagesSita = answer;
					}, function(reason) {
						$scope.name = reason;
					});

			// la somme des balayages du mois
			BalayageSitaSerive.getPromiseSumBalayageSita(newVal).then(
					function(answer) {
						$scope.sumBalayagesSita = answer;
					}, function(reason) {
						$scope.name = reason;
					});
			
			// generer Excel du mois indiqué dans le calendrier
			$scope.generateExcel = function() {
				BalayageSitaSerive.getExcelOfBalayageSita(newVal).then(
						function(answer) {
							$scope.value = answer;
							console.log(answer);
							alert("export succed");
						}, function(reason) {
							$scope.name = reason;
						});
			};
		});
	}
})();