angular.module("app")

.factory("DoctorService",function($http,constants) {

    const BASE_URL = `${constants.BASE_URL}/api/v1/doctors`;

    const api = {};

    api.create = function(doctor) {
        return $http.post(`${BASE_URL}`,doctor);
    };

    api.list = function() {
        return $http.get(`${BASE_URL}`);
    };
    
    

    return api;


});
			