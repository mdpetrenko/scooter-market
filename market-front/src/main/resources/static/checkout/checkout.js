angular.module('market-front').controller('checkoutController', function ($scope, $http, $location, $localStorage) {
    $scope.orderDetails = null;
    const contextPath = 'http://localhost:5555';

    $scope.loadCart = function () {
        $http.get(contextPath + '/cart/api/v1/cart/' + $localStorage.scooterMarketGuestCartId)
            .then(function (response) {
                console.log(response);
                $scope.cartContent = response.data;
            });
    }

    $scope.returnToCart = function () {
        $location.path('/cart');
    }

    $scope.processOrder = function () {
        $scope.orderDetails.guestCartUuid = $localStorage.scooterMarketGuestCartId;
        $http.post(contextPath + '/core/api/v1/orders', $scope.orderDetails)
            .then(function () {
                $scope.orderDetails = null;
                $http.get(contextPath + '/cart/api/v1/cart/' + $localStorage.scooterMarketGuestCartId + '/clear');
                $location.path('/orders');
            }, function (response) {
                alert(response.data.message);
            });
    }

    $scope.loadCart();
});