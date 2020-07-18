(function (angular) {
    'use strict'
    angular.module('app')
        .controller('home.ctrl', function ($scope) {


            const token = localStorage.getItem('auth_token');

            var user = jwt_decode(token);

            $scope.user = user;


           

            

        });


})(angular);
