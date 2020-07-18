
const app = angular.module('app', ['ngRoute','ngMessages']);


app.controller('main.ctrl', function($scope) {
    $scope.test = 'test';
});


app.constant('constants', {
    BASE_URL: 'http://localhost:8080',
    
});

