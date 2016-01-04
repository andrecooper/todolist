<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="tasks" ng-controller="TasksController">
    <div class="container task-container">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Tasks dashboard</h3>
            </div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-md-3">
                        <div class="input-group">
                            <input type="text" class="form-control" ng-model="newTask" placeholder="task name">
                        </div>
                    </div>
                    <div class="col-md-1">
                        <div class="input-group">
                            <button class="btn btn-default" type="button" ng-click="addTask()">Add</button>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Task name</th>
                            <th>
                                <div class="row">
                                    <div class="col-xs-2">
                                        <span>Owner</span>
                                    </div>
                                    <div class="col-xs-7">
                                        <div class="dropdown">
                                            <button class="btn btn-default dropdown-toggle" type="button"
                                                    data-toggle="dropdown">filter by {{selectedUser}}
                                                <span class="caret"></span></button>
                                            <ul class="dropdown-menu">
                                                <li ng-repeat="person in users" ng-click="setPerson(person)"><a
                                                        href="#">
                                                    {{ person }}</a>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </th>
                        </tr>
                        </thead>
                        <tbody class="task-panel">
                        <tr ng-repeat="task in tasklist">
                            <td>{{task.id}}</td>
                            <td>{{task.taskName}}</td>
                            <td>{{task.owner}}</td>
                            <td><span class="glyphicon glyphicon-trash clickable" ng-click="removeTask(task)"></span>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>