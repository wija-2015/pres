(function () {
    'use strict';

    var app = angular.module('app', [
        // Angular modules
        'ui.router',
        'ngAnimate',
        'ngAria',

        //UnderScore Module
        'underscore',

        //Layz Load
        //'oc.lazyLoad',

        // 3rd Party Modules
        'ui.bootstrap',
        'ui.tree',
        'ngMap',
        'ngTagsInput',
        'textAngular',
        'angular-loading-bar',
        'ui.calendar',
        'duScroll',
        'mgo-angular-wizard',
        'daterangepicker',
        //'openlayers-directive',

        // Custom modules
        'app.nav',
        'app.i18n',
        'ngECharts',
        'app.ui',
        'app.ui.form',
        'app.ui.form.validation',
        'app.page',
        'app.table',
        'app.task',
        'app.calendar',

        // Our modules

    ]);

    app.run(['$rootScope', '$state', '$stateParams', function ($rootScope, $state, $stateParams) {
        $rootScope.$state = $state;
        $rootScope.$stateParams = $stateParams;
    }]);
   /**
    app.config(['$httpProvider', function($httpProvider) {
        $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
    }]);
**/
})();






    

