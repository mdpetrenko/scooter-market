angular.module('market-front').controller('createProductController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:8189/market/';

    $scope.createNewProduct = function () {
        if ($scope.newProduct == null) {
            alert('Не заполнена информация о продукте')
            return;
        }
        $http.post(contextPath + 'api/v1/products/', $scope.newProduct)
            .then(function successCallback() {
                $scope.newProduct = null;
                alert('Product created')
                $location.path('/')
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

    $scope.getCategories();

});