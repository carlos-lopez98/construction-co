package com.solvd.constructionco.dao.impl;

import com.solvd.constructionco.dao.IEmployeeDAO;
import com.solvd.constructionco.models.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO implements IEmployeeDAO<Employee, Integer> {

    private List<Employee> employees;

    private static final String GET_ALL_QUERY = "SELECT employee_id, first_name, last_name, email, phone_number, hire_date, position FROM employees";
    private static final String GET_BY_ID_QUERY = "SELECT employee_id, first_name, last_name, email, phone_number, hire_date, position FROM employees WHERE employee_id = ?";
    private static final String SAVE_QUERY = "INSERT INTO employees VALUES(?,?,?,?,?,?,?)";
    private static final String DELETE_QUERY = "DELETE FROM employees WHERE employee_id = ?";
    private static final String UPDATE_QUERY = "UPDATE employees SET first_name = ?, last_name = ?, email = ?, phone_number = ?, hire_date = ?, position = ? WHERE employee_id = ?";

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





