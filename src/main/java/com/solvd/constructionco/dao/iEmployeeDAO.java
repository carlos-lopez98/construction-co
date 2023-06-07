package com.solvd.constructionco.dao;

import com.solvd.constructionco.models.Employee;

import java.util.List;

public interface iEmployeeDAO extends ConstructionDAO<Employee, Integer>{

    @Override
    Employee getById(Integer employeeID);

    @Override
    void save(Employee employee);

    @Override
    void update(Employee employee);

    @Override
    void delete(Integer employeeID);

    @Override
    List<Employee> getAll();
}