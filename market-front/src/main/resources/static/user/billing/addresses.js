angular.module('market-front').controller('userAddressController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:5555/auth/api/v1/user/billing';

    $scope.addAddress = function (address) {
        $http.post(contextPath, address)
            .then(function (){
                location.back();
            });
    }
});