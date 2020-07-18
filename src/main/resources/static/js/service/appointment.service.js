angular.module("app")

.factory("AppointmentService",function($http,constants) {

    const BASE_URL = `${constants.BASE_URL}/api/v1/appointments`;

    const api = {};

    api.create = function(appointment) {
        return $http.post(`${BASE_URL}`,appointment);
    };

    api.list = function() {
        return $http.get(`${BASE_URL}`);
    };
    
    

    return api;


});
			