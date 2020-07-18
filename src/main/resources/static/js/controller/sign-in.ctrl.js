(function (angular) {
    'use strict'
    angular.module('app')
        .controller('sign-in.ctrl', function ($scope, AuthService) {

            $scope.credencial = {};


            $scope.login = (signinForm, credencial) => {

                if (signinForm.$valid) {

                    AuthService.login(credencial)
                        .then((response) => {
                            location.href = 'app.html#/home';
                        }, function (error) {

                            $scope.errorMessage = 'Incorrect authentication credentials';

                        });
                }

            };

        });


})(angular);
