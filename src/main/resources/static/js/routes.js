(function (angular) {


    angular.module('app')
    .config(function($routeProvider){

        $routeProvider.when('/home',{
            templateUrl: 'template/home.html',
            controller: 'home.ctrl'
        })
        .when('/doctor-form',{
            templateUrl: 'template/doctor-form.html',
            controller: 'doctor-form.ctrl'
        })
        .when('/doctor-list',{
            templateUrl: 'template/doctor-list.html',
            controller: 'doctor-list.ctrl',
            resolve: {
                doctors: function(DoctorService) {
                    return DoctorService.list();
                }
            }
        })
        .when('/appointment-form',{
            templateUrl: 'template/appointment-form.html',
            controller: 'appointment-form.ctrl',
            resolve: {
                doctors: function(DoctorService) {
                    return DoctorService.list();
                }
            }
        })
        .when('/appointment-list',{
            templateUrl: 'template/appointment-list.html',
            controller: 'appointment-list.ctrl',
            resolve: {
                appointments: function(AppointmentService) {
                    return AppointmentService.list();
                }
            }
        })
        
        .otherwise({ redirectTo: '/home' });

    });




})(angular);