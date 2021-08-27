angular.module('market-front', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market/';
    let currentPageIndex = 1;
    let currentPageSize = 3;
    $scope.edited = 0;

    $scope.editProduct = function (id) {
        $scope.edited = id;
        $scope.getCategories();
    };


});