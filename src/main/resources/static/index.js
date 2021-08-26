angular.module('market-front', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market/';
    let currentPageIndex = 1;
    let currentPageSize = 3;
    $scope.edited = 0;

    $scope.loadProducts = function (pageIndex = 1, pageSize = 3) {
        currentPageIndex = pageIndex;
        currentPageSize = pageSize;
        $http({
            url: contextPath + 'api/v1/products',
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

    $scope.loadProduct = function (id) {
        $http.get(contextPath + '/api/v1/products/' + id)
            .then(function successCallback(response) {
                alert(response.data.title);
            }, function failureCallback(response) {
                alert(response.data.message);
            });
    };

    $scope.generatePageIndexes = function (startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            arr.push(i);
        }
        return arr;
    };

    $scope.createNewProduct = function () {
        $http.post(contextPath + 'api/v1/products/', $scope.newProduct)
            .then(function successCallback() {
                $scope.loadProducts(currentPageIndex, currentPageSize);
                $scope.newProduct = null;
            }, function failureCallback(response) {
                alert(response.data.message);
            });
    };

    $scope.removeProduct = function (id) {
        $http.delete(contextPath + 'api/v1/products/' + id)
            .then(function successCallback() {
                $scope.loadProducts(currentPageIndex, currentPageSize)
            }, function failureCallback(response) {
                alert(response.data.message);
            });

    };

    $scope.editProduct = function (id) {
        $scope.edited = id;
        $scope.getCategories();
    };

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
                $scope.loadProducts(currentPageIndex, currentPageSize)
                $scope.edited = 0;
            }, function failureCallback(response) {
                alert(response.data.message);
            });
    };

    $scope.loadProducts();
    $scope.getCategories();
});