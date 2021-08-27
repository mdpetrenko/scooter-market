angular.module('market-front').controller('editProductController', function ($scope, $http, $routeParams, $location) {
    const contextPath = 'http://localhost:8189/market/';

    $scope.loadProduct = function () {
        $http.get(contextPath + 'api/v1/products/' + $routeParams.productId)
            .then(function (response) {
                $scope.product = response.data;
            }, function failureCallback (response) {
                console.log(response);
                alert(response.data.messages);
                $location.path('/');
            });
    };

    $scope.getCategories = function () {
        $http.get(contextPath + 'api/v1/categories/')
            .then(function (response) {
                console.log(response)
                $scope.categories = response.data;
            });
    }

    $scope.updateProduct = function (product) {
        $http.put(contextPath + 'api/v1/products/', product)
            .then(function successCallback() {
                alert('Product updated');
                $location.path('/');
            }, function failureCallback(response) {
                alert(response.data.message);
            });
    };

    $scope.getCategories = function () {
        $http.get(contextPath + 'api/v1/categories/')
            .then(function (response) {
                console.log(response);
                $scope.categories = response.data;
            });
    };

    $scope.navToCart = function () {

    };

    $scope.loadProduct();
    $scope.getCategories();
});