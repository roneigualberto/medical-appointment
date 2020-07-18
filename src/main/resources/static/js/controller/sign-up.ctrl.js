(function (angular) {
    'use strict'
    angular.module('app')
        .controller('sign-up.ctrl', function ($scope, AccountService) {

            $scope.account = {};


            $scope.login = (signupForm, account) => {

                if (signupForm.$valid) {
                    $scope.loading = true;
                    AccountService.create(account)
                        .then((response) => {
                            location.href = 'app.html';
                            $scope.loading = false;
                        }, function (error) {
                            $scope.loading = false;
                            if (error.status == 409) {
                               $scope.errorMessage = error.data.message;
                            } else {
                                $scope.errorMessage = 'Error';
                            }
                       });
                }

            };

        });


})(angular);
