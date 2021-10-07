(function () {
    angular
        .module('market-front', ['ngRoute', 'ngStorage'])
        .config(config)
        .run(run);

    function config($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'catalog/catalog.html',
                controller: 'catalogController'
            })
            .when('/edit_product/:productId', {
                templateUrl: 'edit_product/edit_product.html',
                controller: 'editProductController'
            })
            .when('/create_product', {
                templateUrl: 'create_product/create_product.html',
                controller: 'createProductController'
            })
            .when('/cart', {
                templateUrl: 'cart/cart.html',
                controller: 'cartController'
            })
            .when('/checkout', {
                templateUrl: 'checkout/checkout.html',
                controller: 'checkoutController'
            })
            .when('/profile', {
            templateUrl: 'user_profile/user_profile.html',
            controller: 'profileController'
        })
            .when('/register', {
                templateUrl: 'register/register_user.html',
                controller: 'registerUserController'
            })
            .otherwise({
                redirectTo: '/'
            });
    }

    function run($rootScope, $http, $localStorage) {
        const contextPath = 'http://localhost:8189/market'
        if ($localStorage.scooterMarketUser) {
            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.scooterMarketUser.token;
        }
        if (!$localStorage.scooterMarketGuestCartId) {
            $http.get(contextPath + '/api/v1/cart/generate')
                .then(function successCallback(response) {
                    $localStorage.scooterMarketGuestCartId = response.data.value;
                });
        }
    }
})();

angular.module('market-front').controller('indexController', function ($rootScope, $scope, $http, $localStorage, $location) {
    const contextPath = 'http://localhost:8189/market/api/v1';

    $scope.tryToAuth = function () {
        $http.post(contextPath + '/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.scooterMarketUser = {username: $scope.user.username, token: response.data.token};
                    $scope.user.username = null;
                    $scope.user.password = null;
                    $http.get(contextPath + '/cart/' + $localStorage.scooterMarketGuestCartId + '/merge')
                        .then(function successCallback(response) {
                        });
                }
            }, function errorCallback(response) {
            });
    };

    $scope.readUserFromStorage = function () {
        if ($rootScope.isUserLoggedIn()) {
            return $localStorage.scooterMarketUser.username;
        }
        return null;
    }

    $scope.tryToLogout = function () {
        $scope.clearUser();
        if ($scope.user.username) {
            $scope.user.username = null;
        }
        if ($scope.user.password) {
            $scope.user.password = null;
        }
        if ($location.path() === '/profile') {
            $location.path('/');
        }
    };

    $scope.clearUser = function () {
        delete $localStorage.scooterMarketUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $rootScope.isUserLoggedIn = function () {
        return !!$localStorage.scooterMarketUser;
    };

});