package com.solvd.constructionco.dao.impl;

import com.solvd.constructionco.Main;
import com.solvd.constructionco.dao.IInvoiceDAO;
import com.solvd.constructionco.models.Invoice;
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

public class InvoiceDAO implements IInvoiceDAO<Invoice, Integer> {

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final Logger logger = LogManager.getLogger(Main.class);

    private static final String GET_ALL_QUERY = "SELECT i.invoiceId, i.dueDate, i.totalDue, " +
            "p.purchaseOrderId, p.purchaseOrderName, p.budget, p.isClosed, " +
            "c.customerId, c.customerName, c.email, c.phoneNumber, c.address, " +
            "co.contractorId, co.contractorName, co.email, co.phoneNumber, co.address " +
            "FROM invoices AS i " +
            "JOIN projects AS p ON i.projectId = p.projectId " +
            "JOIN customers AS c ON i.customerId = c.customerId " +
            "JOIN contractors AS co ON i.contractorId = co.contractorId";


    private static final String GET_BY_ID_QUERY = "SELECT i.invoiceId, i.dueDate, i.totalDue, " +
            "p.purchaseOrderId, p.purchaseOrderName, p.budget, p.isClosed, " +
            "c.customerId, c.customerName, c.email, c.phoneNumber, c.address, " +
            "co.contractorId, co.contractorName, co.email, co.phoneNumber, co.address " +
            "FROM invoices AS i " +
            "JOIN projects AS p ON i.projectId = p.projectId " +
            "JOIN customers AS c ON i.customerId = c.customerId " +
            "JOIN contractors AS co ON i.contractorId = co.contractorId " +
            "WHERE i.invoiceId = ?";


    private static final String SAVE_QUERY = "INSERT INTO invoices VALUES (?, ?, ?, ?, ?)";


    private static final String DELETE_QUERY = "DELETE FROM invoices WHERE invoice_id = ?";


    private static final String UPDATE_QUERY = "UPDATE invoices SET purchase_order_id = ?, customer_id = ?, contractor_id = ?, due_date = ?, total_due = ? WHERE invoice_id = ?";

    public InvoiceDAO() {

    }

    @Override
    public Invoice getById(Integer invoiceId) {
        Invoice invoice = null;
        //Try with resources auto closes connection
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_ID_QUERY)) {
            statement.setInt(1, invoiceId);

            //Try with resources - auto closes resultSet
            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    invoice.setInvoiceId(resultSet.getInt("invoice_id"));
                    invoice.setPurchaseOrderId(resultSet.getInt("purchase_order_id"));
                    invoice.setCustomerId(resultSet.getInt("customer_id"));
                    invoice.setContractorId(resultSet.getInt("contractor_id"));
                    invoice.setDueDate(resultSet.getDate("due_date"));
                    invoice.setTotalDue(resultSet.getInt("total_due"));

                }
            }
        } catch (SQLException e) {
            logger.info("SQL Exception Occurred: " + e.getMessage());
        }

        return invoice;
    }

    @Override
    public void save(Invoice invoice) {
        //Try with Resources
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SAVE_QUERY)) {

            statement.setInt(1, invoice.getInvoiceId());
            statement.setInt(2, invoice.getPurchaseOrderId());
            statement.setInt(3, invoice.getCustomerId());
            statement.setInt(4, invoice.getContractorId());
            statement.setDate(5,invoice.getDueDate());
            statement.setInt(6,invoice.getTotalDue());

            statement.executeUpdate();
            logger.info("Successfully added invoice order with ID " + invoice.getInvoiceId() + " to the database");
        } catch (SQLException e) {
            logger.info("SQL Exception Occurred: " + e.getMessage());
        }
    }

    @Override
    public void update(Invoice invoice) {
        //Try with Resources
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {

            statement.setInt(1, invoice.getInvoiceId());
            statement.setInt(2, invoice.getPurchaseOrderId());
            statement.setInt(3, invoice.getCustomerId());
            statement.setInt(4, invoice.getContractorId());
            statement.setDate(5,invoice.getDueDate());
            statement.setInt(6,invoice.getTotalDue());

            statement.executeUpdate();
            logger.info("Successfully updated invoice order with ID " + invoice.getInvoiceId());
        } catch (SQLException e) {
            logger.info("SQL Exception Occurred: " + e.getMessage());
        }
    }

    @Override
    public void delete(Integer invoiceId) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, invoiceId);

            statement.executeUpdate();

            logger.info("Successfully deleted invoice with ID " + invoiceId);
        } catch (SQLException e) {
            logger.info("SQL Exception Occurred: " + e.getMessage());
        }
    }

    @Override
    public List<Invoice> getAll() {
        List<Invoice> invoices = new ArrayList<>();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ALL_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Invoice invoice = new Invoice();
                invoice.setInvoiceId(resultSet.getInt("invoice_id"));
                invoice.setPurchaseOrderId(resultSet.getInt("purchase_order_id"));
                invoice.setCustomerId(resultSet.getInt("customer_id"));
                invoice.setContractorId(resultSet.getInt("contractor_id"));
                invoice.setDueDate(resultSet.getDate("due_date"));
                invoice.setTotalDue(resultSet.getInt("total_due"));
                invoices.add(invoice);
            }
        } catch (SQLException e) {
            logger.info("SQL Exception Occurred: " + e.getMessage());
        }
        return invoices;
    }
}