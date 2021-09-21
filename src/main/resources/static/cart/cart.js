angular.module('market-front').controller('cartController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:8189/market';

    $scope.loadCart = function () {
        $http.get(contextPath + '/api/v1/cart')
            .then(function (response) {
                console.log(response);
                $scope.cartContent = response.data;
            });
    };

    $scope.removeItem = function (id) {
        $http.get(contextPath + '/api/v1/cart/remove/' + id)
            .then(function successCallback() {
                $scope.loadCart();
            }, function failureCallback(response) {
                alert(response.data.message);
        });
    };

    $scope.decrementItem = function (id) {
        $http.get(contextPath + '/api/v1/cart/decrement/' + id)
            .then(function successCallback() {
                $scope.loadCart();
            }, function failureCallback(response) {
                alert(response.data.message);
            });
    };

    $scope.incrementItem = function (id) {
        $http.get(contextPath + '/api/v1/cart/add/' + id)
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