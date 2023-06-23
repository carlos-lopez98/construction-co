package com.solvd.constructionco.service.impl;

import com.solvd.constructionco.dao.impl.CustomerDAO;
import com.solvd.constructionco.models.Customer;
import com.solvd.constructionco.service.interfaces.ICustomerService;

import java.util.List;

public class CustomerService implements ICustomerService {


    private static final CustomerDAO customerDAO = new CustomerDAO();

    public CustomerService(){

    }

    @Override
    public Customer getById(Integer customerId) {

        if(customerId == null || customerId < 0){
            throw new IllegalArgumentException("The customerId provided is in incorrect format");
        }

        Customer customer = customerDAO.getById(customerId);

        if(customer == null){
            throw new RuntimeException("Customer is not in database");
        }

        return customer;


    }

    @Override
    public void save(Customer customer) {
            customerDAO.save(customer);
    }

    @Override
    public void update(Customer customer) {
        customerDAO.update(customer);
    }

    @Override
    public void delete(Integer customerID) {
        customerDAO.delete(customerID);
    }

    @Override
    public List getAll() {
        return customerDAO.getAll();
    }
}
