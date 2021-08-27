angular.module('market-front', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market/';

    $scope.getCategories = function () {
        $http.get(contextPath + 'api/v1/categories/')
            .then(function (response) {
                console.log(response)
                $scope.categories = response;
            });
    }

    $scope.updateProduct = function (product) {
        $http.put(contextPath + 'api/v1/products/', product)
            .then(function successCallback() {
            }, function failureCallback(response) {
                alert(response.data.message);
            });
    };
});