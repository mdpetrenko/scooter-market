angular.module('market-front').controller('cartController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:8189/market/';

    $scope.loadCart = function () {
        $http.get(contextPath + '/api/v1/cart')
            .then(function (response) {
                console.log(response);
                $scope.cartContent = response.data;
            });
    };

    $scope.removeFromCart = function (id) {
        $http.delete(contextPath + '/api/v1/cart/' + id)
            .then(function successCallback(response) {
                $scope.loadCart();
            }, function failureCallback(response) {
                alert(response.data.message);
        });
    };

    $scope.loadCart();
});