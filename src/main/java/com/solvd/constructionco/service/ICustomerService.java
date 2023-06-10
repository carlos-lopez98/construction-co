package com.solvd.constructionco.service;

import com.solvd.constructionco.models.Customer;

import java.util.List;

public interface ICustomerService extends ConstructionServiceOperations<Customer, Integer>{

    @Override
    <T> T getById(Integer customerID);

    @Override
    void save(Customer customer);

    @Override
    void update(Customer customer);

    @Override
    void delete(Integer customerID);

    @Override
    List<Customer> getAll();
}
