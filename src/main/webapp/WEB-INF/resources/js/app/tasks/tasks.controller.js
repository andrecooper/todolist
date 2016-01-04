(function () {
    angular
        .module('tasks')
        .controller('TasksController', TaskController);

    TaskController.$inject = ["$scope", "$http"];

    function TaskController($scope, $http) {

        $scope.selectedUser = 'All Users';
        $scope.users = [];

        $scope.getTasks = function (user) {
            console.log("USER", user);
            if (!user || user === 'All Users'){
                user="";
            }
            var response = $http.get('/todoList/api/task' + user);
            response.success(function (data, status, header, config) {
                console.log("received tasklist:", data);

                $scope.tasklist = data;
            });
            response.error(function (data, status, header, config) {
                console.log('Failed to request task list', status);
            })
        };

        $scope.addTask = function () {
            console.log("execution of add", $scope.newTask);
            var task = {
                taskName : $scope.newTask
            };
            var request = $http.post('/todoList/api/task', task);
            request.success(function (data, status, header, config) {
                console.log('successfully added task', data);
                $scope.getTasks($scope.selectedUser);

            });
            request.error(function (data, status, header, config) {
                console.log('failed to add task' + status)
            })
        };

        $scope.setPerson = function (person) {
            console.log("selected person", person);
            $scope.selectedUser = person;
            $scope.getTasks(person);
        };

        $scope.getTaskCreators = function () {
            var response = $http.get('/todoList/api/task/owners');
            response.success(function (data, status, header, config) {
                console.log("received tasklist:", data);
                $scope.users = data;
                $scope.users.push('All Users');
            });
            response.error(function (data, status, header, config) {
                console.log('Failed to request task list', status);
            })
        };

        $scope.removeTask = function(task){
            alert("Removing task: " + task.id + " : " + task.taskName);

        };

        $scope.getTasks();
        $scope.getTaskCreators();

    }
})();