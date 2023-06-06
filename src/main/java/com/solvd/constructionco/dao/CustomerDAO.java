package com.solvd.constructionco.dao;

import com.solvd.constructionco.interfaces.ConstructionDAO;
import com.solvd.constructionco.models.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerDAO implements ConstructionDAO<Customer, Integer> {

    private List<Customer> customers;

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
