angular.module('market-front').controller('catalogController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:5555/core';
    let currentPageIndex = 1;
    let currentPageSize = 3;

    $scope.loadProducts = function (pageIndex = 1, pageSize = 3) {
        currentPageIndex = pageIndex;
        currentPageSize = pageSize;
        $http({
            url: contextPath + '/api/v1/products',
            method: 'GET',
            params: {
                p: pageIndex,
                s: pageSize
            }
        }).then(function (response) {
            console.log(response);
            $scope.productsPage = response.data;
            $scope.paginationArray = $scope.generatePageIndexes(1, $scope.productsPage.totalPages);
        });
    };

    $scope.editProduct = function (id) {
        $location.path('/edit_product/' + id);
    };

    $scope.removeProduct = function (id) {
        $http.delete(contextPath + '/api/v1/products/' + id)
            .then(function successCallback() {
                $scope.loadProducts(currentPageIndex, currentPageSize)
            }, function failureCallback(response) {
                alert(response.data.message);
            });
    };

    $scope.addToCart = function (productId) {
        $http.get(
            contextPath + '/api/v1/cart/' + $localStorage.scooterMarketGuestCartId + '/add/' + productId)
            .then(function successCallback() {
                $scope.loadProducts(currentPageIndex, currentPageSize);
            }, function failureCallback(response) {
                alert(response.data.message);
            })
    }

    $scope.generatePageIndexes = function (startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            arr.push(i);
        }
        return arr;
    };

    $scope.loadProducts();
});