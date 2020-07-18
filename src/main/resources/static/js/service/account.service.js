angular.module("app")

.factory("AccountService",function($http,constants) {

    const api = {};


    api.create = function(account) {

        return $http.post(`${constants.BASE_URL}/api/v1/account `,account)
        
        .then((response) => {
             const data = response.data;
			localStorage.setItem('auth_token',data.token);
        });
    };
    

    return api;


});
			