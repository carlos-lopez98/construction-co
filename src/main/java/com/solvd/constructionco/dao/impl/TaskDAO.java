package com.solvd.constructionco.dao.impl;

import com.solvd.constructionco.Main;
import com.solvd.constructionco.dao.ITaskDAO;
import com.solvd.constructionco.models.Task;
import com.solvd.constructionco.util.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO implements ITaskDAO<Task, Integer> {
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final Logger logger = LogManager.getLogger(Main.class);

    private static final String GET_ALL_QUERY = "SELECT task_id, task_name, status, due_date FROM tasks";
    private static final String GET_BY_ID_QUERY = "SELECT task_id, task_name, status, due_date FROM tasks WHERE task_id = ?";
    private static final String SAVE_QUERY = "INSERT INTO tasks VALUES(?,?,?,?)";
    private static final String DELETE_QUERY = "DELETE FROM tasks WHERE task_id = ?";
    private static final String UPDATE_QUERY = "UPDATE tasks SET task_id = ?, task_name = ?, status = ?,due_date = ? WHERE task_id = ?";

    public TaskDAO(){};

    @Override
    public Task getById(Integer taskId) {
        Task task = null;

        Connection connection = connectionPool.getConnection();

        try (
             PreparedStatement statement = connection.prepareStatement(GET_BY_ID_QUERY)

        ) {

            statement.setInt(1, taskId);

            //Moved to try-with-resources to autoClose
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    task = new Task();
                    task.setTaskId(resultSet.getInt("task_id"));
                    task.setTaskName(resultSet.getString("task_name"));
                    task.setClosed(Boolean.parseBoolean(resultSet.getString("status")));
                    task.setDueDate(resultSet.getDate("due_date"));
                    return task;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            connectionPool.releaseConnection(connection);
        }

        return task;
    }

    @Override
    public void save(Task task) {

        Connection connection = connectionPool.getConnection();

        try (
             PreparedStatement statement = connection.prepareStatement(SAVE_QUERY)) {
            statement.setInt(1, task.getTaskId());
            statement.setString(2, task.getTaskName());
            statement.setString(3, String.valueOf(task.isClosed()));
            statement.setDate(4, task.getDueDate());

            statement.executeUpdate();
            logger.info("Successfully added " + task.getTaskName() + " to database");
        } catch (SQLException e) {
            logger.info("SQL Exeption Occured" + e + e.getMessage());
        }finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void update(Task task) {

        Connection connection = connectionPool.getConnection();

        try (
             PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setInt(1, task.getTaskId());
            statement.setString(2, task.getTaskName());
            statement.setString(3, String.valueOf(task.isClosed()));
            statement.setDate(4, task.getDueDate());
            statement.setInt(5, task.getTaskId());

            statement.executeUpdate();
            logger.info("Successfully updated task with ID " + task.getTaskId());
        } catch (SQLException e) {
            logger.info("SQL Exception Occurred: " + e.getMessage());
        }finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void delete(Integer id) {

        Connection connection = connectionPool.getConnection();

        try (
             PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, id);

            statement.executeUpdate();
            logger.info("Successfully deleted task with ID " + id);
        } catch (SQLException e) {
            logger.info("SQL Exception Occurred: " + e.getMessage());
        }finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public List<Task> getAll() {
        List<Task> taskList = new ArrayList<>();

        Connection connection = connectionPool.getConnection();

        try (
             PreparedStatement statement = connection.prepareStatement(GET_ALL_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Task task = new Task();
                task.setTaskId(resultSet.getInt("task_id"));
                task.setTaskName(resultSet.getString("task_name"));
                task.setClosed(Boolean.parseBoolean(resultSet.getString("status")));
                task.setDueDate(resultSet.getDate("due_date"));
                taskList.add(task);
            }
        } catch (SQLException e) {
            logger.info("SQL Exception Occurred: " + e.getMessage());
        }finally {
            connectionPool.releaseConnection(connection);
        }

        return taskList;
    }
}
