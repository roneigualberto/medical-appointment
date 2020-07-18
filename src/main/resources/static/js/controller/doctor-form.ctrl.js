(function (angular) {
    'use strict'
    angular.module('app')
        .controller('doctor-form.ctrl', function ($scope, DoctorService) {

            $scope.doctor = {};


            $scope.create = (doctorForm, doctor) => {

                if (doctorForm.$valid) {
                    $scope.loading = true;
                    DoctorService.create(doctor)
                        .then((response) => {
                            $scope.loading = false;
                            doctorForm.$setPristine();
                            $scope.doctor = {};
                            $scope.okMessage = 'Doctor saved successfully'
                            $scope.errorMessage = null;
                           
                        }, function (error) {
                            $scope.loading = false;
                            if (error.status == 409) {
                               $scope.errorMessage = error.data.message;
                            } else {
                                $scope.errorMessage = 'Error';
                            }
                        });
                } else {
                    $scope.errorMessage = null;
                    $scope.okMessage = null
                    $scope.loading = false;
                }

            };

        });


})(angular);
