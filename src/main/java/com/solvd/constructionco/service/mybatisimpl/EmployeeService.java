package com.solvd.constructionco.service.mybatisimpl;

import com.solvd.constructionco.Main;
import com.solvd.constructionco.dao.IEmployeeDAO;
import com.solvd.constructionco.dao.ISupplierDAO;
import com.solvd.constructionco.models.Employee;
import com.solvd.constructionco.models.Supplier;
import com.solvd.constructionco.service.interfaces.IEmployeeService;
import com.solvd.constructionco.util.SQLSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class EmployeeService implements IEmployeeService {

    private static final Logger logger = LogManager.getLogger(Main.class);
    private static final SQLSessionUtil sessionUtil = new SQLSessionUtil();

    @Override
    public Employee getById(Integer employeeID) {
        if (employeeID > 0) {
            Employee employee = null;

            SqlSession session = sessionUtil.retrieveSqlSession();
            IEmployeeDAO employeeDAO = session.getMapper(IEmployeeDAO.class);
            employee = (Employee) employeeDAO.getById(employeeID);

            //Commits and closes
            session.commit();
            session.close();
            return employee;
        } else {
            throw new RuntimeException("Invalid EmployeeID Entered");
        }
    }

    @Override
    public void save(Employee employee) {
        if (employee != null) {
            SqlSession session = sessionUtil.retrieveSqlSession();
            IEmployeeDAO employeeDAO = session.getMapper(IEmployeeDAO.class);

            employeeDAO.save(employee);

            session.commit();
            session.close();

            logger.info("Succesfully saved employee to database");
        } else {
            throw new NullPointerException("Employee Entered is null");
        }
    }

    @Override
    public void update(Employee employee) {
        if (employee != null) {
            SqlSession session = sessionUtil.retrieveSqlSession();
            IEmployeeDAO employeeDAO = session.getMapper(IEmployeeDAO.class);

            employeeDAO.update(employee);

            session.commit();
            session.close();

            logger.info("Succesfully updated employee to database");
        } else {
            throw new NullPointerException("Employee Entered is null");
        }
    }

    @Override
    public void delete(Integer employeeID) {
        if (employeeID > 0) {
            SqlSession session = sessionUtil.retrieveSqlSession();
            IEmployeeDAO employeeDAO = session.getMapper(IEmployeeDAO.class);
            Employee employee = (Employee) employeeDAO.getById(employeeID);
            if (employee != null) {
                employeeDAO.delete(employeeID);
                session.commit();
                session.close();
                logger.info("Succesfully deleted employee from database");
            } else {
                throw new RuntimeException("EmployeeID is not in database");
            }
        } else {
            throw new RuntimeException("EmployeeID given is not valid");
        }
    }

    @Override
    public List<Employee> getAll() {
        SqlSession session = sessionUtil.retrieveSqlSession();
        IEmployeeDAO employeeDAO = session.getMapper(IEmployeeDAO.class);

        List<Employee> employees = employeeDAO.getAll();

        if (employees.isEmpty()) {
            throw new RuntimeException("No Employees in Database");
        } else {
            session.commit();
            session.close();
            return employees;
        }    }
}
