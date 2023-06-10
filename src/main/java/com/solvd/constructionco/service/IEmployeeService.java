package com.solvd.constructionco.service;

import com.solvd.constructionco.models.Employee;

import java.util.List;

public interface IEmployeeService extends ConstructionServiceOperations<Employee, Integer>{

    @Override
    <T> T getById(Integer employeeID);

    @Override
    void save(Employee employee);

    @Override
    void update(Employee employee);

    @Override
    void delete(Integer employeeID);

    @Override
    List<Employee> getAll();
}
