package com.solvd.constructionco.service.impl;

import com.solvd.constructionco.dao.impl.EmployeeDAO;
import com.solvd.constructionco.models.Employee;
import com.solvd.constructionco.service.interfaces.IEmployeeService;

import java.util.List;

public class EmployeeService implements IEmployeeService {

    private static final EmployeeDAO employeeDAO = new EmployeeDAO();

    public EmployeeService(){

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
