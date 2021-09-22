angular.module('market-front').controller('profileController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:8189/market';

    $scope.loadOrders = function () {
        $http.get(contextPath + '/api/v1/orders')
            .then(function (response) {
                $scope.orders = response.data;
                console.log($scope.orders);
            });
    };

    $scope.loadOrders();
});