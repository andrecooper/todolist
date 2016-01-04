/**
 * Created by andrew on 02.09.15.
 */
(function () {
    angular
        .module('users')
        .controller('UsersController', UserController);

    UserController.$inject = ["$scope", "$http"];

    function UserController($scope, $http) {

        getOnlineUsers = function () {
            var response = $http.get('/todoList/api/user');
            response.success(function (data, status, header, config) {
                $scope.onlineUserList = data;
            });
            response.error(function (data, status, header, config) {
                console.log('Failed to request online user list', status);
            })
        };

        getOnlineUsers();

    }
})();
