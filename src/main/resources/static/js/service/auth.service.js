angular.module("app")

.factory("AuthService",function($http,constants) {

    const api = {};


    api.login = function(credencial) {

        return $http.post(`${constants.BASE_URL}/api/v1/auth/login `,credencial)
        
        .then((response) => {
             const data = response.data;
			localStorage.setItem('auth_token',data.token);
        });
    };
    

    return api;


});
			