/**
 * Created by abenouhoud on 21/03/2016.
 */
(function() {
	'use strict';
	angular.module('app').service('BalayageSitaSerive',
			[ '$http', '$q', balayageSitaSerive ])

	function balayageSitaSerive($http, $q) {

		// Récuperer les jours d'un mois
		this.getPromiseTabDaysOfMonth = function(myDate) {
			var deferDaysOfMonth;
			var promise = $http({
				method : 'GET',
				url : 'http://localhost:8080/date/tabDaysOfMonth?dateString='
						+ myDate,
				headers : {
					'Content-Type' : 'application/json; charset=utf-8'
				}
			})
			deferDaysOfMonth = deferDaysOfMonth || $q.defer();

			promise.then(function(answer) {
				deferDaysOfMonth.resolve(answer.data);
				var item = answer.data;
				return item;
			}, function(reason) {
				deferDaysOfMonth.reject(reason);
			});

			return deferDaysOfMonth.promise;
		};
		// Les bayalayages effectués par sita dans un mois
		this.getPromiseBalayageSita = function(myDate) {
			var deferBalayageSita;
			var promise = $http({
				method : 'GET',
				url : 'http://localhost:8080/balayage/balayagesSita?dateString='
						+ myDate,
				headers : {
					'Content-Type' : 'application/json; charset=utf-8'
				}
			})
			deferBalayageSita = deferBalayageSita || $q.defer();

			promise.then(
			// OnSuccess function
			function(answer) {
				deferBalayageSita.resolve(answer.data);
				var item = answer.data;
				return item;
			},
			// OnFailure function
			function(reason) {
				// This code will only run if we have a failed promise.
				deferBalayageSita.reject(reason);
			});

			return deferBalayageSita.promise;
		};
		// La somme des balayages
		this.getPromiseSumBalayageSita = function(myDate) {
			var deferSumBalayagesSita;
			var promise = $http({
				method : 'GET',
				url : 'http://localhost:8080/balayage/sumBalayagesSita?dateString='
						+ myDate,
				headers : {
					'Content-Type' : 'application/json; charset=utf-8'
				}
			});
			deferSumBalayagesSita = deferSumBalayagesSita || $q.defer();

			promise.then(function(answer) {
				deferSumBalayagesSita.resolve(answer.data);
				var item = answer.data;
				return item;
			}, function(reason) {
				deferSumBalayagesSita.reject(reason);
			});

			return deferSumBalayagesSita.promise;
		};

		// Exporter to Excel
		this.getExcelOfBalayageSita = function(myDate) {
			var deferExcelOfBalayageSita;
			var promise = $http({
				method : 'GET',
				url : 'http://localhost:8080/balayage/getExcelOfBalayagesSita?dateString='
						+ myDate,
				headers : {
					'Content-Type' : 'application/json; charset=utf-8'
				}
			})
			deferExcelOfBalayageSita = deferExcelOfBalayageSita || $q.defer();

			promise.then(function(answer) {
				deferExcelOfBalayageSita.resolve(answer.data);
				var item = answer.data;
				return item;
			}, function(reason) {
				deferExcelOfBalayageSita.reject(reason);
			});

			return deferExcelOfBalayageSita.promise;
		};

	}
	;
})();