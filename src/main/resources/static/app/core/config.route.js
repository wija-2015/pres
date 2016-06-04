(function () {
    'use strict';

    angular.module('app')
        .config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider) {
            $urlRouterProvider.otherwise('tableau-de-bord');
            var routes, setRoutes;
            routes = [
			    {dossier:'dashboard/general/', name:'d.general',alias:'tableau-de-bord', controller:'DashboardGeneralCtrl'},
                {dossier:'dashboard/delegataire/', name:'d.delegataire',alias:'tableau-de-bord-delegataire', controller:'DashboardDelegateCtrl'},
                {dossier:'dashboard/controleur/', name:'d.controleur',alias:'tableau-de-bord-controleur', controller:'DashboardControleurCtrl'},
				
				{dossier:'dashboard/attachements/', name:'d.lavageAverda',alias:'lavage-mecanise-averda', controller:'AttachementAverdaCtrl'},
				{dossier:'dashboard/attachements/', name:'d.balayageAverda',alias:'balayage-mecanise-averda', controller:'tableCtrl'},
				{dossier:'dashboard/attachements/', name:'d.lavageBrossageAverda',alias:'lavage-brossage-averda', controller:'AttachementAverdaCtrl'},
                
				{dossier:'dashboard/attachements/attachements-sita/html/', name:'d.lavageSita',alias:'lavage-mecanise-sita', controller:'AttachementAverdaCtrl'},
				{dossier:'dashboard/attachements/attachements-sita/html/', name:'d.balayageSita',alias:'balayage-mecanise-sita', controller:'BalayageSitaCtrl'},
				{dossier:'dashboard/attachements/attachements-sita/html/', name:'d.lavageBrossageSita',alias:'lavage-brossage-sita', controller:'DashboardDelegateCtrl'},
				
				{dossier:'dashboard/attachements/consultation/vehicule/html/', name:'d.vehicules',alias:'vehicules', controller:'VehiculeCtrl'},
				{dossier:'dashboard/attachements/consultation/sita/html/', name:'d.attachementsSita',alias:'attachements-sita', controller:'ConsultBalayageSitaCtrl'},
				{dossier:'dashboard/attachements/consultation/averda/html/', name:'d.attachementsAverda',alias:'attachements-averda', controller:'ConsultBalayageAverdaCtrl'},
			     
				{dossier:'dashboard/attachements/consultation/sita/html/', name:'balayageSitaConsult',alias:'consult-balayage-sita', controller:'ConsultBalayageSitaCtrl'},
				{dossier:'dashboard/attachements/consultation/sita/html/', name:'lavageSitaConsult',alias:'consult-lavage-sita', controller:'ConsultLavageSitaCtrl'},
				{dossier:'dashboard/attachements/consultation/sita/html/', name:'lavageBrossageSitaConsult',alias:'consult-lavage-brossage-sita', controller:'ConsultLavageBrossageSitaCtrl'}

				
            ]

            setRoutes = function(route) {
                var config, url,templateURL;
                url = '/' + route.alias;
                templateURL = 'app/' + route.dossier+route.name+ '.html';
                $stateProvider.state(route.alias,{url:url, templateUrl : templateURL, controller : route.controller });
                return $stateProvider;
            };

            routes.forEach(function(route) {
                return setRoutes(route);
            });

        }]
    );

})(); 