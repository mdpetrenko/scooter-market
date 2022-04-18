angular.module('market-front').controller('catalogController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:5555';
    let currentPageIndex = 1;
    let currentPageSize = 3;
    let currentFilter;

    $scope.resetFilter = function () {
        currentFilter = {
            titlePart: null,
            minPrice: null,
            maxPrice: null
        };
        console.log(currentFilter);
    }

    $scope.loadProducts = function (pageIndex = 1, pageSize = 3) {
        currentPageIndex = pageIndex;
        currentPageSize = pageSize;
        $http({
            url: contextPath + '/core/api/v1/products',
            method: 'GET',
            params: {
                p: pageIndex,
                s: pageSize,
                min_price: currentFilter.minPrice,
                max_price: currentFilter.maxPrice,
                title_part: currentFilter.titlePart
            }
        }).then(function (response) {
            $scope.productsPage = response.data;
            $scope.paginationArray = $scope.generatePageIndexes(1, $scope.productsPage.totalPages);
            console.log(response.data);
        });
    };

    $scope.applyFilter = function (productFilter) {
        if (productFilter == null) {
            $scope.resetFilter();
        }
        currentFilter = productFilter;
        $scope.loadProducts();
    }

    $scope.editProduct = function (id) {
        $location.path('/edit_product/' + id);
    };

    $scope.removeProduct = function (id) {
        $http.delete(contextPath + '/core/api/v1/products/' + id)
            .then(function successCallback() {
                $scope.loadProducts(currentPageIndex, currentPageSize)
            }, function failureCallback(response) {
                alert(response.data.message);
            });
    };

    $scope.addToCart = function (productId) {
        $http.get(
            contextPath + '/cart/api/v1/cart/' + $localStorage.scooterMarketGuestCartId + '/add/' + productId)
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

    $scope.resetFilter();
    $scope.loadProducts();
});