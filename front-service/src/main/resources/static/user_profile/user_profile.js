angular.module('market-front').controller('profileController', function ($scope, $http, $location) {

    $scope.loadOrders = function () {
        $http.get('http://localhost:5555/core/api/v1/orders')
            .then(function (response) {
                $scope.orders = response.data;
                console.log($scope.orders);
            });
    };

    $scope.showOrderDetails = function (id) {

    }

    $scope.loadOrders();
});