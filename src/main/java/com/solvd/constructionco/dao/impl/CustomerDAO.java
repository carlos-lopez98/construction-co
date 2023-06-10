package com.solvd.constructionco.dao.impl;

import com.solvd.constructionco.dao.ICustomerDAO;
import com.solvd.constructionco.models.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerDAO implements ICustomerDAO<Customer, Integer> {

    private List<Customer> customers;
    private static final String getAllQuery = "SELECT customer_id, customer_name, email, phone_number, address FROM customers";
    private static final String getByIdQuery = "SELECT customer_id, customer_name, email, phone_number, address FROM customers WHERE customer_id = ?";
    private static final String saveQuery = "INSERT INTO customers VALUES(?,?,?,?,?)";
    private static final String deleteQuery = "DELETE FROM customers WHERE customer_id = ?";
    private static final String updateQuery = "UPDATE customers SET customer_name = ?, email = ?, phone_number = ?, address = ? WHERE customer_id = ?";


    public CustomerDAO() {
        customers = new ArrayList<>();
    }

    @Override
    public Customer getById(Integer id) {

        for (Customer customer : customers) {
            if (customer.getCustomerId() == id) {
                return customer;
            }
        }

        return null;
    }

    @Override
    public void save(Customer customer) {
        customers.add(customer);
    }

    @Override
    public void update(Customer customer) {
        Customer existingCustomer = getById(customer.getCustomerId());
        if (existingCustomer != null) {
            existingCustomer.setCustomerName(customer.getCustomerName());
            existingCustomer.setEmail(customer.getEmail());
            existingCustomer.setPhoneNumber(customer.getPhoneNumber());
            existingCustomer.setAddress(customer.getAddress());
        }
    }

    @Override
    public void delete(Integer id) {
        Customer customerToRemove = getById(id);
        if (customerToRemove != null) {
            customers.remove(customerToRemove);
        }
    }

    @Override
    public List<Customer> getAll() {
        return customers;
    }
}
