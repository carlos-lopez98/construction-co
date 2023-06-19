package com.solvd.constructionco.dao.impl;

import com.solvd.constructionco.Main;
import com.solvd.constructionco.dao.IProjectDAO;
import com.solvd.constructionco.models.Customer;
import com.solvd.constructionco.models.Project;
import com.solvd.constructionco.util.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectDAO implements IProjectDAO<Project, Integer> {

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final Logger logger = LogManager.getLogger(Main.class);

    private static final String GET_ALL_QUERY = "SELECT purchase_order_id, purchaseorder_name, budget, status FROM purchase_order";
    private static final String GET_BY_ID_QUERY = "SELECT purchase_order_id, purchaseorder_name, budget, status FROM purchase_order WHERE purchase_order_id = ?";
    private static final String SAVE_QUERY = "INSERT INTO purchase_order VALUES(?,?,?,?)";
    private static final String DELETE_QUERY = "DELETE FROM purchase_order WHERE purchase_order_id = ?";
    private static final String UPDATE_QUERY = "UPDATE purchase_order SET purchase_order_id = ?, purchaseorder_name = ?, budget = ?, description = ?, status = ? WHERE purchase_order_id = ?";


    public ProjectDAO() {
    }

    @Override
    public Project getById(Integer purchaseOrderId) {
        Project purchaseOrder = null;

        Connection connection = connectionPool.getConnection();

        try (
             PreparedStatement statement = connection.prepareStatement(GET_BY_ID_QUERY)) {
            statement.setInt(1, purchaseOrderId);

            //Try with resources - auto closes resultSet
            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    purchaseOrder.setPurchaseOrderId(resultSet.getInt("purchase_order_id"));
                    purchaseOrder.setPurchaseOrderName(resultSet.getString("purchaseorder_name"));
                    purchaseOrder.setBudget(resultSet.getInt("budget"));
                    purchaseOrder.setClosed(resultSet.getBoolean("status"));
                }
            }
        } catch (SQLException e) {
            logger.info("SQL Exception Occurred: " + e.getMessage());
        }finally {
            connectionPool.releaseConnection(connection);
        }

        return purchaseOrder;
    }

    @Override
    public void save(Project project) {

        Connection connection = connectionPool.getConnection();

        try (
             PreparedStatement statement = connection.prepareStatement(SAVE_QUERY)) {

            statement.setInt(1, project.getPurchaseOrderId());
            statement.setString(2, project.getPurchaseOrderName());
            statement.setInt(3, project.getBudget());
            statement.setBoolean(4, project.isClosed());

            statement.executeUpdate();
            logger.info("Successfully added purchase order with ID " + project.getPurchaseOrderId() + " to the database");
        } catch (SQLException e) {
            logger.info("SQL Exception Occurred: " + e.getMessage());
        }finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void update(Project project) {

        Connection connection = connectionPool.getConnection();

        try (
             PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {

            statement.setInt(1, project.getPurchaseOrderId());
            statement.setString(2, project.getPurchaseOrderName());
            statement.setInt(3, project.getBudget());
            statement.setBoolean(4, project.isClosed());

            statement.executeUpdate();
            logger.info("Successfully updated project with ID " + project.getPurchaseOrderId());
        } catch (SQLException e) {
            logger.info("SQL Exception Occurred: " + e.getMessage());
        }finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void delete(Integer purchaseOrderId) {

        Connection connection = connectionPool.getConnection();

        try (
             PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, purchaseOrderId);

            statement.executeUpdate();

            logger.info("Successfully deleted customer with ID " + purchaseOrderId);
        } catch (SQLException e) {
            logger.info("SQL Exception Occurred: " + e.getMessage());
        }finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public List<Project> getAll() {
        List<Project> purchaseOrderList = new ArrayList<>();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ALL_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Project purchaseOrder = new Project();
                purchaseOrder.setPurchaseOrderId(resultSet.getInt("purchase_order_id"));
                purchaseOrder.setPurchaseOrderName(resultSet.getString("purchaseorder_name"));
                purchaseOrder.setBudget(resultSet.getInt("budget"));
                purchaseOrder.setClosed(resultSet.getBoolean("status"));
                purchaseOrderList.add(purchaseOrder);
            }
        } catch (SQLException e) {
            logger.info("SQL Exception Occurred: " + e.getMessage());
        }
        return purchaseOrderList;
    }
}
