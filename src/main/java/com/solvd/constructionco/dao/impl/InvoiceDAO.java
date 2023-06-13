package com.solvd.constructionco.dao.impl;

import com.solvd.constructionco.Main;
import com.solvd.constructionco.dao.IInvoiceDAO;
import com.solvd.constructionco.models.Contractor;
import com.solvd.constructionco.models.Customer;
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

    private static final String GET_ALL_QUERY = "SELECT i.invoice_id, i.due_date, i.total_due, " +
            "p.purchase_order_id, p.purchaseorder_name, p.budget, p.status, " +
            "c.customer_id, c.customer_name, c.email, c.phone_number, c.address, " +
            "co.contractor_id, co.contractor_name, co.email, co.phone_number, co.address " +
            "FROM invoices AS i " +
            "JOIN projects AS p ON i.purchase_order_id = p.purchase_order_id " +
            "JOIN customers AS c ON i.customer_id = c.customer_id " +
            "JOIN contractors AS co ON i.contractor_id = co.contractor_id";


    private static final String GET_BY_ID_QUERY = "SELECT i.invoice_id, i.due_date, i.total_due, " +
            "p.purchase_order_id, p.purchaseorder_name, p.budget, p.status, " +
            "c.customer_id, c.customer_name, c.email, c.phone_number, c.address, " +
            "co.contractor_id, co.contractor_name, co.email, co.phone_number, co.address " +
            "FROM invoices AS i " +
            "JOIN projects AS p ON i.purchase_order_id = p.purchase_order_id " +
            "JOIN customers AS c ON i.customer_id = c.customer_id " +
            "JOIN contractors AS co ON i.contractor_id = co.contractor_id " +
            "WHERE i.invoice_id = ?";


    private static final String SAVE_QUERY = "INSERT INTO invoices VALUES (?, ?, ?, ?, ?)";


    private static final String DELETE_QUERY = "DELETE FROM invoices WHERE invoice_id = ?";


    private static final String UPDATE_QUERY = "UPDATE invoices SET due_date = ?, total_due = ?, purchase_order_id = ?, customer_id = ?, contractor_id = ? WHERE invoice_id = ?";

    public InvoiceDAO() {

    }

    @Override
    public Invoice getById(Integer invoiceId) {
        Invoice invoice = null;

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_ID_QUERY)) {
            statement.setInt(1, invoiceId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    invoice = new Invoice();
                    invoice.setInvoiceId(resultSet.getInt("invoiceId"));
                    invoice.setDueDate(resultSet.getDate("dueDate"));
                    invoice.setTotalDue(resultSet.getInt("totalDue"));

                    // Set the project object
                    Project project = new Project();
                    project.setPurchaseOrderId(resultSet.getInt("purchaseOrderId"));
                    project.setPurchaseOrderName(resultSet.getString("purchaseOrderName"));
                    project.setBudget(resultSet.getInt("budget"));
                    project.setClosed(resultSet.getBoolean("status"));
                    invoice.setProject(project);

                    // Set the customer object
                    Customer customer = new Customer();
                    customer.setCustomerId(resultSet.getInt("customerId"));
                    customer.setCustomerName(resultSet.getString("customerName"));
                    customer.setEmail(resultSet.getString("email"));
                    customer.setPhoneNumber(resultSet.getString("phoneNumber"));
                    customer.setAddress(resultSet.getString("address"));
                    invoice.setCustomer(customer);

                    // Set the contractor object
                    Contractor contractor = new Contractor();
                    contractor.setContractorId(resultSet.getInt("contractorId"));
                    contractor.setContractorName(resultSet.getString("contractorName"));
                    contractor.setEmail(resultSet.getString("email"));
                    contractor.setPhoneNumber(resultSet.getString("phoneNumber"));
                    contractor.setAddress(resultSet.getString("address"));
                    invoice.setContractor(contractor);

                    logger.info("Invoice successfully retrieved");
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
            // Set the parameter values
            statement.setInt(1, invoice.getInvoiceId());
            statement.setDate(2, new java.sql.Date(invoice.getDueDate().getTime()));
            statement.setInt(3, invoice.getTotalDue());
            statement.setInt(4, invoice.getProject().getPurchaseOrderId());
            statement.setInt(5, invoice.getCustomer().getCustomerId());
            statement.setInt(6, invoice.getContractor().getContractorId());

            // Execute the query
            statement.executeUpdate();
            logger.info("Successfully added invoice order with ID " + invoice.getInvoiceId() + " to the database");
        } catch (SQLException e) {
            logger.info("SQL Exception Occurred: " + e.getMessage());
        }
    }

    @Override
    public void update(Invoice invoice) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            // Set the parameter values
            statement.setDate(1, new java.sql.Date(invoice.getDueDate().getTime()));
            statement.setInt(2, invoice.getTotalDue());
            statement.setInt(3, invoice.getProject().getPurchaseOrderId());
            statement.setInt(4, invoice.getCustomer().getCustomerId());
            statement.setInt(5, invoice.getContractor().getContractorId());
            statement.setInt(6, invoice.getInvoiceId());

            // Execute the query
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
            // Set the parameter value
            statement.setInt(1, invoiceId);

            // Execute the query
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

                // Retrieve and set the customer object
                Customer customer = new CustomerDAO().getById(resultSet.getInt("customer_id"));
                invoice.setCustomer(customer);

                // Retrieve and set the contractor object
                Contractor contractor = new ContractorDAO().getById(resultSet.getInt("contractor_id"));
                invoice.setContractor(contractor);

                // Retrieve and set the project object
                Project project = new ProjectDAO().getById(resultSet.getInt("purchase_order_id"));
                invoice.setProject(project);

                invoices.add(invoice);
            }
        } catch (SQLException e) {
            logger.info("SQL Exception Occurred: " + e.getMessage());
        }

        return invoices;
    }
}