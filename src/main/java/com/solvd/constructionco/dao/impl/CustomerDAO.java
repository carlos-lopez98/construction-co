package com.solvd.constructionco.dao.impl;

import com.solvd.constructionco.Main;
import com.solvd.constructionco.dao.ICustomerDAO;
import com.solvd.constructionco.models.Customer;
import com.solvd.constructionco.util.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO implements ICustomerDAO<Customer, Integer> {

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final Logger logger = LogManager.getLogger(Main.class);

    private static final String GET_ALL_QUERY = "SELECT customer_id, customer_name, email, phone_number, address FROM customers";
    private static final String GET_BY_ID_QUERY = "SELECT customer_id, customer_name, email, phone_number, address FROM customers WHERE customer_id = ?";
    private static final String SAVE_QUERY = "INSERT INTO customers VALUES(?,?,?,?,?)";
    private static final String DELETE_QUERY = "DELETE FROM customers WHERE customer_id = ?";
    private static final String UPDATE_QUERY = "UPDATE customers SET customer_name = ?, email = ?, phone_number = ?, address = ? WHERE customer_id = ?";

    public CustomerDAO() {
    }

    @Override
    public Customer getById(Integer customerId) {

        Customer customer = null;
        Connection connection = connectionPool.getConnection();

        try (
             PreparedStatement statement = connection.prepareStatement(GET_BY_ID_QUERY)) {
            statement.setInt(1, customerId);

            //Try with resources - auto closes resultSet
            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    customer.setCustomerId(resultSet.getInt("customer_id"));
                    customer.setCustomerName(resultSet.getString("customer_name"));
                    customer.setEmail(resultSet.getString("email"));
                    customer.setPhoneNumber(resultSet.getString("phone_number"));
                    customer.setAddress(resultSet.getString("address"));
                }
            }
        } catch (SQLException e) {
            logger.info("SQL Exception Occurred: " + e.getMessage());
        }finally{
            connectionPool.releaseConnection(connection);
        }

        return customer;
    }

    @Override
    public void save(Customer customer) {

        Connection connection = connectionPool.getConnection();

        try (
             PreparedStatement statement = connection.prepareStatement(SAVE_QUERY)) {

            statement.setInt(1, customer.getCustomerId());
            statement.setString(2, customer.getCustomerName());
            statement.setString(3, customer.getEmail());
            statement.setString(4, customer.getPhoneNumber());
            statement.setString(5, customer.getAddress());

            statement.executeUpdate();
            logger.info("Successfully added customer with ID " + customer.getCustomerName() + " to the database");
        } catch (SQLException e) {
            logger.info("SQL Exception Occurred: " + e.getMessage());
        }finally{
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void update(Customer customer) {

        Connection connection = connectionPool.getConnection();

        try (
             PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setInt(1, customer.getCustomerId());
            statement.setString(2, customer.getCustomerName());
            statement.setString(3, customer.getEmail());
            statement.setString(4, customer.getPhoneNumber());
            statement.setString(5, customer.getAddress());

            statement.executeUpdate();
            logger.info("Successfully updated customer with ID " + customer.getCustomerId());
        } catch (SQLException e) {
            logger.info("SQL Exception Occurred: " + e.getMessage());
        }finally{
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void delete(Integer customerId) {
        Connection connection = connectionPool.getConnection();

        try (
             PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, customerId);

            statement.executeUpdate();
            logger.info("Successfully deleted customer with ID " + customerId);
        } catch (SQLException e) {
            logger.info("SQL Exception Occurred: " + e.getMessage());
        }finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> customerList = new ArrayList<>();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ALL_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(resultSet.getInt("customer_id"));
                customer.setCustomerName(resultSet.getString("customer_name"));
                customer.setEmail(resultSet.getString("email"));
                customer.setPhoneNumber(resultSet.getString("phone_number"));
                customer.setAddress(resultSet.getString("address"));
                customerList.add(customer);
            }
        } catch (SQLException e) {
            logger.info("SQL Exception Occurred: " + e.getMessage());
        }
        return customerList;
    }
}
