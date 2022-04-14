angular.module('market-front').controller('profileController', function ($scope, $rootScope, $http, $location) {
    const contextPath = 'http://localhost:5555/auth/api/v1'


    $scope.getUserData = function () {
        $http.get(contextPath + '/user')
            .then(function (response) {
                $scope.userData = response.data;
                console.log($scope.userData);
            });
    }

    $scope.removeAddress = function (addressId) {
        $http.delete(contextPath + '/user/billing/' + addressId)
            .then(function () {
                $scope.getUserData();
            }, function (response) {
                alert(response.status);
            });
    }

    $scope.addAddress = function () {
        $http.post(contextPath + '/user/billing/', $scope.address)
            .then(function () {
                $scope.getUserData();
            });
    }

    $scope.updateUserData = function () {
        $http.put(contextPath + '/user', $scope.userData)
            .then(function () {
                $scope.getUserData()
            })
    }

    $scope.getUserData();

});