(function (angular) {
    'use strict'
    angular.module('app')
        .controller('sign-in.ctrl', function ($scope, AuthService) {

            $scope.credencial = {};


            $scope.login = (signinForm, credencial) => {

                $scope.errorMessage = null;

               

                if (signinForm.$valid) {

                    $scope.loading = true;
                    $scope.errorMessage = null;

                    AuthService.login(credencial)
                        .then((response) => {
                            location.href = 'app.html';
                           
                        }, function (error) {
                            $scope.loading = false;
                            $scope.errorMessage = 'Incorrect authentication credentials';

                        });
                }

            };

        });


})(angular);
