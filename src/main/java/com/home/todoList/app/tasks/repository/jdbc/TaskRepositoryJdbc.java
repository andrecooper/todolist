package com.home.todoList.app.tasks.repository.jdbc;

import com.home.todoList.app.tasks.model.Task;
import com.home.todoList.app.tasks.repository.TaskRepository;
import com.home.todoList.app.users.model.User;
import com.home.todoList.common.utilities.annotations.persistance.PersistanceSource;
import com.home.todoList.common.utilities.annotations.persistance.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@Repository
@PersistanceSource(value = Source.JDBC)
public class TaskRepositoryJdbc implements TaskRepository {

    @Autowired
    DriverManagerDataSource dataSource;

    private final static String FIND_ALL_QUERY = "select * from tasks left join users on tasks.owner_id = users.id";
    private final static String FIND_BY_USER_QUERY = "select * from tasks left join users on tasks.owner_id = users.id where tasks.owner_id=?";
    private final static String ADD_QUERY = "insert into tasks(id,creationDate, executionDate, taskName, owner_id) values(?,?,?,?,?)";
    private final static String DELETE_QUERY = "delete from tasks where id=?";

    @Override
    public List<Task> getAllTasks() {
        System.out.println("\n WHOOOHOO!!!! I'M USING JDBC !!!");
        List<Task> taskList = new LinkedList<>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_QUERY);
            while (resultSet.next()) {
                Task task = getTask(resultSet);
                taskList.add(task);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return taskList;
    }

    @Override
    public List<Task> getTasksByOwner(User username) {
        System.out.println("\n WHOOOHOO!!!! I'M USING JDBC !!!");
        List<Task> taskList = new LinkedList<>();
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(FIND_BY_USER_QUERY)) {
                statement.setLong(1, username.getId());
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    Task task = getTask(rs);
                    taskList.add(task);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return taskList;
    }

    @Override
    public boolean addTask(Task task) {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(ADD_QUERY)) {
                statement.setLong(1, task.getId());
                statement.setDate(2, task.getCreationDate());
                statement.setDate(3, task.getExecutionDate());
                statement.setString(4, task.getTaskName());
                statement.setLong(5, task.getOwner().getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteTask(long id) {

        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
                statement.setLong(1, id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private Task getTask(ResultSet resultSet) throws SQLException {
        Task task = new Task();
        User user = new User();
        long id = resultSet.getLong("id");
        String taskName = resultSet.getString("taskName");
        task.setId(id);
        task.setTaskName(taskName);
        String userName = resultSet.getString("username");
        user.setUsername(userName);
        task.setOwner(user);
        return task;
    }
}
