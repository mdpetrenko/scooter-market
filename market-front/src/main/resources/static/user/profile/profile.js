angular.module('market-front').controller('profileController', function ($scope, $http, $location) {
    $scope.prefix = 'http://localhost:5555/auth/api/v1'


    $scope.getUserInfo = function () {
        $http.get($scope.prefix + '/user')
            .then(function (response) {
                $scope.userData = response.data;
                console.log($scope.userData);
            });
    }

    $scope.removeAddress = function (addressId) {
        $http.delete($scope.prefix + '/user/billing/' + addressId)
            .then(function () {
                $scope.getUserInfo();
            },function (response){
                alert(response.status);
            });
    }

    $scope.getUserInfo();

});