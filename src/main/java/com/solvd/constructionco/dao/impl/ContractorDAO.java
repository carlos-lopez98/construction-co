package com.solvd.constructionco.dao.impl;

import com.solvd.constructionco.Main;
import com.solvd.constructionco.dao.IContractorDAO;
import com.solvd.constructionco.models.Contractor;
import com.solvd.constructionco.util.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContractorDAO implements IContractorDAO<Contractor, Integer> {

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final Logger logger = LogManager.getLogger(Main.class);

    private static final String GET_ALL_QUERY = "SELECT contractor_id, contractor_name, email, phone_number, address FROM contractors";
    private static final String GET_BY_ID_QUERY = "SELECT contractor_id, contractor_name, email, phone_number, address FROM contractors WHERE contractor_id = ?";
    private static final String SAVE_QUERY = "INSERT INTO contractors VALUES(?,?,?,?,?)";
    private static final String DELETE_QUERY = "DELETE FROM contractors WHERE contractor_id = ?";
    private static final String UPDATE_QUERY = "UPDATE contractors SET contractor_name = ?, email = ?, phone_number = ?, address = ? WHERE contractor_id = ?";

    public ContractorDAO() {
    }

    @Override
    public Contractor getById(Integer contractorID) {
        Contractor contractor = null;

        Connection connection = connectionPool.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(GET_BY_ID_QUERY)) {
            statement.setInt(1, contractorID);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                contractor = new Contractor(resultSet.getString("contractor_name"), resultSet.getString("email"));
                contractor.setContractorId(resultSet.getInt("contractor_id"));
                contractor.setPhoneNumber(resultSet.getString("phone_number"));
                contractor.setAddress(resultSet.getString("address"));
            }
        } catch (SQLException e) {
            logger.info("SQL Exception Occurred: " + e.getMessage());
        } finally{
            connectionPool.releaseConnection(connection);
        }

        return contractor;
    }

    @Override
    public void save(Contractor contractor) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SAVE_QUERY)) {
            statement.setInt(1, contractor.getContractorId());
            statement.setString(2, contractor.getContractorName());
            statement.setString(3, contractor.getEmail());
            statement.setString(4, contractor.getPhoneNumber());
            statement.setString(5, contractor.getAddress());

            statement.executeUpdate();
            logger.info("Successfully added contractor with ID " + contractor.getContractorId() + " to the database");
        } catch (SQLException e) {
            logger.info("SQL Exception Occurred: " + e.getMessage());
        }
    }

    @Override
    public void update(Contractor contractor) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setString(1, contractor.getContractorName());
            statement.setString(2, contractor.getEmail());
            statement.setString(3, contractor.getPhoneNumber());
            statement.setString(4, contractor.getAddress());
            statement.setInt(5, contractor.getContractorId());

            statement.executeUpdate();
            logger.info("Successfully updated contractor with ID " + contractor.getContractorId());
        } catch (SQLException e) {
            logger.info("SQL Exception Occurred: " + e.getMessage());
        }
    }

    @Override
    public void delete(Integer contractorID) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, contractorID);

            statement.executeUpdate();
            logger.info("Successfully deleted contractor with ID " + contractorID);
        } catch (SQLException e) {
            logger.info("SQL Exception Occurred: " + e.getMessage());
        }
    }

    @Override
    public List<Contractor> getAll() {
        List<Contractor> contractorList = new ArrayList<>();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ALL_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Contractor contractor = new Contractor();
                contractor.setContractorId(resultSet.getInt("contractor_id"));
                contractor.setContractorName(resultSet.getString("contractor_name"));
                contractor.setEmail(resultSet.getString("email"));
                contractor.setPhoneNumber(resultSet.getString("phone_number"));
                contractor.setAddress(resultSet.getString("address"));
                contractorList.add(contractor);
            }
        } catch (SQLException e) {
            logger.info("SQL Exception Occurred: " + e.getMessage());
        }

        return contractorList;
    }
}








