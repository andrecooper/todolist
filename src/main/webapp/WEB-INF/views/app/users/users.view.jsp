<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div ng-controller="UsersController">
    <div id="users" class="container user-container">
        <row>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Registered users</h3>
                </div>
                <div class="panel-body user-panel">
                    <table class="table table-striped">
                        <thead>
                        </thead>
                        <tbody>
                        <tr ng-repeat="user in onlineUserList track by $index">
                            <td>{{user.username}}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </row>
    </div>
</div>

