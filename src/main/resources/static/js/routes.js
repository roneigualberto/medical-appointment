(function (angular) {


    angular.module('app')
    .config(function($routeProvider){

        $routeProvider.when('/home',{
            templateUrl: 'template/home.html',
            controller: 'home.ctrl'
        })
        
        .otherwise({ redirectTo: '/home' });

    });




})(angular);