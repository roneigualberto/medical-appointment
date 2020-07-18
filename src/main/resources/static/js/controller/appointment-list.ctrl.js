(function (angular) {
    'use strict'
    angular.module('app')
        .controller('appointment-list.ctrl', function ($scope, appointments) {

            $scope.appointments = appointments.data;

        });


})(angular);
