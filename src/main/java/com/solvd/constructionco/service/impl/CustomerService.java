package com.solvd.constructionco.service.impl;

import com.solvd.constructionco.dao.impl.CustomerDAO;
import com.solvd.constructionco.service.ConstructionServiceOperations;
import com.solvd.constructionco.models.Customer;
import com.solvd.constructionco.service.ICustomerService;

import java.util.List;

public class CustomerService implements ICustomerService {


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
