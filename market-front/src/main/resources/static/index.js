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
            .when('/orders', {
                templateUrl: 'user/orders/orders.html',
                controller: 'ordersController'
            })
            .when('/add_address', {
                templateUrl: 'user/address_form/address_form.html',
                controller: 'addressController'
            })
            .when('/profile', {
                templateUrl: 'user/profile/profile.html',
                controller: 'profileController'
            })
            .when('/register', {
                templateUrl: 'register/register_user.html',
                controller: 'registerUserController'
            })
            .when('/order_pay/:orderId', {
                templateUrl: 'order_pay/order_pay.html',
                controller: 'payController'
            })
            .otherwise({
                redirectTo: '/'
            });
    }

    function run($rootScope, $http, $localStorage) {
        if ($localStorage.scooterMarketUser) {
            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.scooterMarketUser.token;
        }
        if (!$localStorage.scooterMarketGuestCartId) {
            $http.get('http://localhost:5555/cart/api/v1/cart/generate')
                .then(function successCallback(response) {
                    $localStorage.scooterMarketGuestCartId = response.data.value;
                });
        }
    }
})();

angular.module('market-front').controller('indexController', function ($rootScope, $scope, $http, $localStorage, $location) {
    const contextPath = 'http://localhost:5555';

    $scope.tryToAuth = function () {
        $http.post(contextPath + '/auth/api/v1/auth', $scope.user)
            .then(function successCallback(response) {
                console.log(response.data)
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.scooterMarketUser = {username: $scope.user.username, token: response.data.token};
                    $scope.user.username = null;
                    $scope.user.password = null;
                    $http.get('http://localhost:5555/cart/api/v1/cart/' + $localStorage.scooterMarketGuestCartId + '/merge')
                        .then(function successCallback() {
                            if ($location.path() === '/cart') {
                                $http.get('http://localhost:5555/cart/api/v1/cart/' + $localStorage.scooterMarketGuestCartId);
                            }
                        });
                }
            }, function errorCallback(response) {
                console.log(response.data);
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
        if (($location.path() === '/profile') || ($location.path() === '/cart')) {
            $location.path('/').replace();
            $scope.$apply();
        }
    };

    $scope.clearUser = function () {
        delete $localStorage.scooterMarketUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $rootScope.isUserLoggedIn = function () {
        return !!$localStorage.scooterMarketUser;
    };

    //TODO: implement admin check
    $rootScope.isUserAdmin = function () {
        return $rootScope.isUserLoggedIn();
    }

    $rootScope.addAddress = function (address) {
        $http.post('http://localhost:5555/auth/api/v1/user/billing', address)
            .then(function successCallback() {
            }, function failureCallback(response) {
                alert(response.data);
            });
    }

});