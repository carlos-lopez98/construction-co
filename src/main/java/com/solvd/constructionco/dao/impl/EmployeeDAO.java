package com.solvd.constructionco.dao.impl;

import com.solvd.constructionco.dao.ConstructionDAO;
import com.solvd.constructionco.models.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO implements ConstructionDAO<Employee, Integer> {

    private List<Employee> employees;

    public EmployeeDAO() {
        employees = new ArrayList<>();
    }

    @Override
    public Employee getById(Integer id) {
        for (Employee employee : employees) {
            if (employee.getEmployeeId() == id) {
                return employee;
            }
        }
        return null;
    }

    @Override
    public void save(Employee entity) {
        employees.add(entity);
    }

    @Override
    public void update(Employee entity) {
        Employee existingEmployee = getById(entity.getEmployeeId());
        if (existingEmployee != null) {
            existingEmployee.setEmployeeName(entity.getEmployeeName());
            existingEmployee.setEmail(entity.getEmail());
            existingEmployee.setPhoneNumber(entity.getPhoneNumber());
            existingEmployee.setHireDate(entity.getHireDate());
            existingEmployee.setPosition(entity.getPosition());
        }
    }

    @Override
    public void delete(Integer id) {
        Employee employeeToRemove = getById(id);
        if (employeeToRemove != null) {
            employees.remove(employeeToRemove);
        }
    }

    @Override
    public List<Employee> getAll() {
        return employees;
    }
}





