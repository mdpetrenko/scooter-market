angular.module('market-front').controller('checkoutController', function ($scope, $http, $location, $localStorage) {
    $scope.orderDetails = null;
    const contextPath = 'http://localhost:8189/market';

    $scope.loadCart = function () {
        $http.get(contextPath + '/api/v1/cart/' + $localStorage.scooterMarketGuestCartId)
            .then(function (response) {
                console.log(response);
                $scope.cartContent = response.data;
            });
    };

    $scope.returnToCart = function () {
        $location.path('/cart');
    };

    $scope.processOrder = function () {
        $scope.orderDetails.cartUuid = $localStorage.scooterMarketGuestCartId;
        $http.post(contextPath + '/api/v1/orders', $scope.orderDetails)
            .then(function () {
                $scope.orderDetails = null;
                $http.get(contextPath + '/api/v1/cart/' + $localStorage.scooterMarketGuestCartId + '/clear')
                    .then(function () {
                        alert('Order created!');
                    });
            }, function (response) {
                alert(response.data.message);
            });
    };

    $scope.loadCart();
});