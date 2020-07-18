(function (angular) {


    angular.module('app')
    .config(function($routeProvider){

        $routeProvider.when('/sign-in',{
            templateUrl: 'template/sign-in.html',
            controller: 'sign-in.ctrl'
        })
        $routeProvider.when('/sign-up',{
            templateUrl: 'template/sign-up.html',
            controller: 'sign-up.ctrl'
        })
        .otherwise({ redirectTo: '/sign-in' });

    });




})(angular);