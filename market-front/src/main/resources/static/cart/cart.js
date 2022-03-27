angular.module('market-front').controller('cartController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:5555/cart';

    $scope.loadCart = function () {
        console.log($localStorage.scooterMarketGuestCartId);
        $http.get(contextPath + '/api/v1/cart/' + $localStorage.scooterMarketGuestCartId)
            .then(function (response) {
                console.log(response);
                $scope.cartContent = response.data;
            });
    };

    $scope.removeItem = function (productId) {
        console.log($localStorage.scooterMarketGuestCartId);
        $http.get(contextPath + '/api/v1/cart/' + $localStorage.scooterMarketGuestCartId + '/remove/' + productId)
            .then(function successCallback() {
                $scope.loadCart();
            }, function failureCallback(response) {
                alert(response.data.message);
        });
    };

    $scope.decrementItem = function (productId) {
        console.log($localStorage.scooterMarketGuestCartId);
        $http.get(contextPath + '/api/v1/cart/' + $localStorage.scooterMarketGuestCartId + '/decrement/' + productId)
            .then(function successCallback() {
                $scope.loadCart();
            }, function failureCallback(response) {
                alert(response.data.message);
            });
    };

    $scope.incrementItem = function (productId) {
        console.log($localStorage.scooterMarketGuestCartId);
        $http.get(contextPath + '/api/v1/cart/' + $localStorage.scooterMarketGuestCartId + '/add/' + productId)
            .then(function successCallback() {
                $scope.loadCart();
            }, function failureCallback(response) {
                alert(response.data.message);
            });
    };

    $scope.checkout = function() {
        $location.path('/checkout')
    }

    $scope.loadCart();
});