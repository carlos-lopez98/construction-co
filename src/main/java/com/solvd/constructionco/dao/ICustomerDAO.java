package com.solvd.constructionco.dao;

import com.solvd.constructionco.models.Customer;

import java.util.List;

public interface ICustomerDAO<Customer, Integer> extends ConstructionDAO<Customer, Integer> {

    @Override
    Customer getById(Integer customerID);

    @Override
    void save(Customer customer);

    @Override
    void update(Customer customer);

    @Override
    void delete(Integer customerID);

    @Override
    List<Customer> getAll();
}
