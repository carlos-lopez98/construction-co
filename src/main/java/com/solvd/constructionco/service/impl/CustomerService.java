package com.solvd.constructionco.service.impl;

import com.solvd.constructionco.dao.impl.CustomerDAO;
import com.solvd.constructionco.interfaces.ConstructionServiceOperations;
import com.solvd.constructionco.models.Customer;

import java.util.List;

public class CustomerService implements ConstructionServiceOperations<Customer, Integer> {


    private CustomerDAO customerDAO;

    public CustomerService(CustomerDAO customerDAO){
        this.customerDAO = customerDAO;
    }

    @Override
    public Customer getById(Integer integer) {
        return customerDAO.getById(integer);
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
