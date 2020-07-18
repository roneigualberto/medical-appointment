
const app = angular.module('app', ['ngRoute', 'ngMessages']);


app.controller('main.ctrl', function ($scope) {
    $scope.logout = () => {

        localStorage.removeItem('auth_token');
        location.href = '/';

    };
});





app.config(function ($httpProvider) {

    $httpProvider.interceptors.push('AuthInterceptor');
  
});

app.run(function($rootScope) {
    $rootScope.$on('$routeChangeStart', function (event, next, current) {
        $rootScope.loadingView = true;
    });

    $rootScope.$on('$routeChangeSuccess', function (event, next, current) {
        $rootScope.loadingView = false;
    });
})


app.factory("AuthInterceptor", function ($q) {

    var service = {};


    /***
     * Request Handle
     */
    service.request = function (config) {

        const token = localStorage.getItem('auth_token');

        if (!token) {
            location.href = '/';
        }

        config.headers['Authorization'] = 'Bearer ' + token;

        return config;

    }


    service.responseError = function (error) {

        if (error.status == 401 || error.status == 403) {
            localStorage.removeItem('auth_token');
            location.href = '/';
        }
        return $q.reject(error);
    };


    return service;
});


app.constant('constants', {
    BASE_URL: '',

});

