(function (angular) {
    'use strict'
    angular.module('app')
        .controller('appointment-form.ctrl', function ($scope,doctors, AppointmentService) {

            $scope.appointment = {};
            $scope.doctors = doctors.data;


            $scope.create = (appointmentForm, appointment) => {
                $scope.errorMessage = null;
                $scope.okMessage = null;

                if (appointmentForm.$valid) {

                    const appointmentBody = {
                        doctorId: appointment.doctor.id,
                        startDate: getDateWithoutTimeZone(appointment.startDate)
                    }

                    AppointmentService.create(appointmentBody)
                        .then((response) => {

                            appointmentForm.$setPristine();
                            $scope.appointment = {};
                            $scope.okMessage = 'Appointment saved successfully'
                            $scope.errorMessage = null;
                           
                        }, function (error) {

                            if (error.status == 409) {
                               $scope.errorMessage = error.data.message;
                            } else {
                                $scope.errorMessage = 'Error';
                            }
                        });
                } else {
                    $scope.errorMessage = null;
                    $scope.okMessage = null;
                }

            };

        });


        function getDateWithoutTimeZone(strDate) {

            const dt = new Date(strDate);

            const date = dt.toISOString().split('T')[0]

            const time  = dt.toLocaleTimeString();
            
            return `${date}T${time}`;

        }




})(angular);
