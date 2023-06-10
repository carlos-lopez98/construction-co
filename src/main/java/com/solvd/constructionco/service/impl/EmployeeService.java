package com.solvd.constructionco.service.impl;

import com.solvd.constructionco.dao.impl.EmployeeDAO;
import com.solvd.constructionco.service.ConstructionServiceOperations;
import com.solvd.constructionco.models.Employee;
import com.solvd.constructionco.service.IEmployeeService;

import java.util.List;

public class EmployeeService implements IEmployeeService {

    private EmployeeDAO employeeDAO;

    public EmployeeService(EmployeeDAO employeeDAO){

        this.employeeDAO = employeeDAO;
    }


    @Override
    public Employee getById(Integer employeeID) {
        return employeeDAO.getById(employeeID);
    }

    @Override
    public void save(Employee employee) {
            employeeDAO.save(employee);
    }

    @Override
    public void update(Employee employee) {
        employeeDAO.update(employee);
    }

    @Override
    public void delete(Integer employeeID) {
        employeeDAO.delete(employeeID);
    }

    @Override
    public List getAll() {
        return employeeDAO.getAll();
    }
}
