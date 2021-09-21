angular.module('market-front').controller('checkoutController', function ($scope, $http, $location) {
    $scope.orderDetails = null;
    const contextPath = 'http://localhost:8189/market';

    $scope.loadCart = function () {
        $http.get(contextPath + '/api/v1/cart')
            .then(function (response) {
                console.log(response);
                $scope.cartContent = response.data;
            });
    };

    $scope.returnToCart = function () {
        $location.path('/cart');
    };

    $scope.processOrder = function () {
        $scope.orderDetails.cartItems = $scope.cartContent.items;
        $http.post(contextPath + '/api/v1/order', $scope.orderDetails)
            .then(function () {
                alert('Order complete!');
                $scope.orderDetails = null;
                }, function (response) {
                alert(response.data.message);
            });
    };

    $scope.loadCart();
});