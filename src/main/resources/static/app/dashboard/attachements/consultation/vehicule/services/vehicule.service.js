/**
 * Created by abenouhoud on 21/03/2016.
 */
(function() {

	angular.module('app.table').service('VehiculeService',
			[ '$http', '$q', vehiculeService ]);

	function vehiculeService($http, $q) {

		// Récuperer tous les vehicules
		this.getAllVehicules = function(page) {
			var deferAllVehicules;
			var promise = $http({
				method : 'GET',
				url : 'http://localhost:8080/vehiculesDestination/allVehicules?page='
						+ page,
				headers : {
					'Content-Type' : 'application/json; charset=utf-8'
				}
			});
			deferAllVehicules = deferAllVehicules || $q.defer();

			promise.then(function(answer) {
				deferAllVehicules.resolve(answer.data);
				var item = answer.data;
				return item;
			}, function(reason) {
				deferAllVehicules.reject(reason);
			});

			return deferAllVehicules.promise;
		};

		// Ajouter un vehicule
		this.addVehicule = function(vehicule) {
			var deferSaveVehicule;
			var promise = $http({
				method : 'POST',
				url : 'http://localhost:8080/vehiculesDestination/saveVehicule',
				data : vehicule,
				headers : {
					'Content-Type' : 'application/json; charset=utf-8'
				}
			});
			deferSaveVehicule = deferSaveVehicule || $q.defer();

			promise.then(function(answer) {
				deferSaveVehicule.resolve(answer.data);
				var item = answer.data;
				return item;
			}, function(reason) {
				deferSaveVehicule.reject(reason);
			});

			return deferSaveVehicule.promise;
		};
	}
	;
})();