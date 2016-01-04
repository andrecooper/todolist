/**
 * Created by andrew on 11.09.15.
 */
(function(){
    angular
        .module('taskListApp',[]).controller('tasksController', TaskController);

    TaskController.$inject = ["$scope", "$http"];

    function TaskController($scope, $http) {

        $scope.getTasks = function () {
            var response = $http.get('/todoList/tasks/get');
            response.success(function (data, status, header, config) {
                $scope.tasklist = data;
            });
            response.error(function (data, status, header, config) {
                console.log('Failed to request task list', status);
            })
        };

        $scope.addTask = function () {
            console.log("execution of add", $scope.newTask);
            var request = $http.post('/todoList/tasks/add', $scope.newTask);
            request.success(function (data, status, header, config) {
                console.log('successfully added task', data);
                $scope.getTasks();

            });
            request.error(function (data, status, header, config) {
                console.log('failed to add task' + status)

            })
        };

        $scope.getTasks();
    }
})();
