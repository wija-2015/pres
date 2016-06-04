(function() {
	'use strict';

	angular.module('app.table', [ "xeditable" ]).run(function(editableOptions) {
		editableOptions.theme = 'bs3';
	}).controller('VehiculeCtrl',
			[ '$scope', '$http', '$filter', 'VehiculeService', vehiculeCtrl ]);

	function vehiculeCtrl($scope, $http, $filter, VehiculeService) {

		// get tous les vehicules
		$scope.pageCourante = 0;
		$scope.pages = new Array();
		function getAll() {
			VehiculeService.getAllVehicules($scope.pageCourante).then(
			// OnSuccess function
			function(answer) {
				$scope.vehicules = answer;
				console.log($scope.vehicules);
				$scope.pages = new Array(answer.totalPages);
			}, function(reason) {
				$scope.name = reason;
			});
		};
		$scope.run = getAll();

		// Methode pour parcourir la pagination
		$scope.gotoPage = function(page) {
			$scope.pageCourante = page;
			getAll();
		};

		// Ajouter vehicule
		$scope.vehicule = {};
		$scope.save = function() {
			console.log($scope.vehicule);
			VehiculeService.addVehicule($scope.vehicule).then(
			function(answer) {
				$scope.insert = answer;
				console.log(answer);
				alert("vehicule aded");
			}, function(reason) {
				$scope.name = reason;
			});
		};

		// Modifier vehicule
		
	}

})();