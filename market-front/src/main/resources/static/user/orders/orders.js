angular.module('market-front').controller('ordersController', function ($scope, $http, $location) {

    $scope.loadOrders = function () {
        $http.get('http://localhost:5555/core/api/v1/orders')
            .then(function (response) {
                $scope.orders = response.data;
                console.log($scope.orders);
            });
    };

    $scope.checkoutOrder = function (orderId) {
        $location.path('/order_pay/' + orderId);
    }

    $scope.loadOrders();
});