angular.module('market-front').controller('addressController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:5555/auth/api/v1';

    $scope.addAddress = function () {
        $http.post(contextPath + '/user/billing', $scope.address)
            .then(function successCallback(){
                $location.path('/profile');
            }, function failureCallback(response) {
                alert(response.data);
            });
    }
});