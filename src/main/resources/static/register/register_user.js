angular.module('market-front').controller('registerUserController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:8189/market/api/v1/';

    $scope.registerUser = function () {
        if ($scope.newUser.username == null) {
            alert('Не указано имя пользователя')
            return;
        }
        else if ($scope.newUser.password == null) {
            alert('Не указан пароль')
        }
        $http.post(contextPath + 'register', $scope.newUser)
            .then(function successCallback() {
                $scope.newUser = null;
                alert('Successfully registered!')
                $location.path('/')
            }, function failureCallback(response) {
                alert(response.data.message);
            });
    };

});