angular.module('market-front', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market';
    let currentPageindex = 1;
    let currentPageSize = 3;

    $scope.loadProducts = function (pageIndex = 1, pageSize = 3) {
        currentPageindex = pageIndex;
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
        })
    }
});