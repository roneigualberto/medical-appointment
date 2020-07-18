(function (angular) {
    'use strict'
    angular.module('app')
        .controller('doctor-list.ctrl', function ($scope, doctors) {

            $scope.doctors = doctors.data;

           

        });


})(angular);
