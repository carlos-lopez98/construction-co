package com.solvd.constructionco.service.mybatisimpl;

import com.solvd.constructionco.Main;
import com.solvd.constructionco.dao.ICustomerDAO;
import com.solvd.constructionco.models.Customer;
import com.solvd.constructionco.service.interfaces.ICustomerService;
import com.solvd.constructionco.util.SQLSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CustomerService implements ICustomerService {

    private static final Logger logger = LogManager.getLogger(Main.class);
    private static final SQLSessionUtil sessionUtil = new SQLSessionUtil();

    @Override
    public Customer getById(Integer customerId) {
        if (customerId > 0) {
            Customer customer = null;

            SqlSession session = sessionUtil.retrieveSqlSession();
            ICustomerDAO customerDAO = session.getMapper(ICustomerDAO.class);
            customer = (Customer) customerDAO.getById(customerId);

            //Commits and closes
            //Until session.commit is closed changes are not persisted to the database
            session.commit();
            session.close();
            return customer;
        } else {
            throw new RuntimeException("Invalid CustomerId Entered");
        }
    }

    @Override
    public void save(Customer customer) {


        if (customer != null) {
            SqlSession session = sessionUtil.retrieveSqlSession();
            ICustomerDAO customerDAO = session.getMapper(ICustomerDAO.class);

            customerDAO.save(customer);

            session.commit();
            session.close();

            logger.info("Succesfully saved customer to database");
        } else {
            throw new NullPointerException("Customer is null");
        }
    }

    @Override
    public void update(Customer customer) {
        if (customer != null) {
            SqlSession session = sessionUtil.retrieveSqlSession();
            ICustomerDAO customerDAO = session.getMapper(ICustomerDAO.class);

            customerDAO.update(customer);

            session.commit();
            session.close();

            logger.info("Succesfully updated customer to database");
        } else {
            throw new NullPointerException("Customer is null");
        }
    }

    @Override
    public void delete(Integer customerId) {
        if (customerId > 0) {
            SqlSession session = sessionUtil.retrieveSqlSession();
            ICustomerDAO customerDAO = session.getMapper(ICustomerDAO.class);
            Customer customer = (Customer) customerDAO.getById(customerId);
            if (customer != null) {
                customerDAO.delete(customerId);
                session.commit();
                session.close();
                logger.info("Succesfully deleted customer from database");
            } else {
                throw new RuntimeException("CustomerId is not in database");
            }
        } else {
            throw new RuntimeException("CustomerId given is not correct");
        }
    }

    @Override
    public List<Customer> getAll() {
        SqlSession session = sessionUtil.retrieveSqlSession();
        ICustomerDAO customerDAO = session.getMapper(ICustomerDAO.class);

        List<Customer> customers = customerDAO.getAll();

        if (customers.isEmpty()) {
            throw new RuntimeException("No customers in Database");
        } else {
            session.commit();
            session.close();
            return customers;
        }
    }
}
