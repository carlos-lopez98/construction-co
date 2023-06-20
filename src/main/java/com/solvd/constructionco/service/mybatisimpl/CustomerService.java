package com.solvd.constructionco.service.mybatisimpl;

import com.solvd.constructionco.Main;
import com.solvd.constructionco.models.Customer;
import com.solvd.constructionco.service.interfaces.ICustomerService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.PropertiesUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class CustomerService implements ICustomerService {

    private static final Logger logger = LogManager.getLogger(Main.class);
    private static final String BATIS_CONFIG = "src/main/resources/config/mybatis-config.xml";
    private static final String SAVE_CUSTOMER = "com.solvd.constructionco.mybatis.impl.customermapper.save";
    private static final String GET_BY_ID = "com.solvd.constructionco.mybatis.impl.customermapper.getById";
    private static final String UPDATE_CUSTOMER = "com.solvd.constructionco.mybatis.impl.customermapper.update";
    private static final String DELETE_CUSTOMER = "com.solvd.constructionco.mybatis.impl.customermapper.delete";
    private static final String GET_ALL = "com.solvd.constructionco.mybatis.impl.customermapper.getAll";

    @Override
    public Customer getById(Integer customerId) {

            Properties prop = this.retrieveProperties();
            Customer customer = null;

            try (InputStream stream = Resources.getResourceAsStream(BATIS_CONFIG);
                 SqlSession session = new SqlSessionFactoryBuilder().build(stream, prop).openSession();) {

                customer = session.selectOne(GET_BY_ID, customerId);

                return customer;
            } catch (IOException e) {
                logger.info("File Not Found");
            }

        return customer;
    }

    @Override
    public void save(Customer customer) {
        Properties properties = this.retrieveProperties();

        try (InputStream stream = Resources.getResourceAsStream(BATIS_CONFIG);
             SqlSession session = new SqlSessionFactoryBuilder().build(stream, properties).openSession();) {
            session.selectOne(SAVE_CUSTOMER, customer);
            session.commit();
        } catch (IOException e) {
            logger.info("File Not Found");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Customer customer) {

        Properties properties = this.retrieveProperties();

        try (InputStream stream = Resources.getResourceAsStream(BATIS_CONFIG);
             SqlSession session = new SqlSessionFactoryBuilder().build(stream, properties).openSession();) {
            session.selectOne(UPDATE_CUSTOMER, customer);
            session.commit();

            logger.info("Succesfully updated Customer " + customer.getCustomerName());
        } catch (IOException e) {
            logger.info("File Not Found");
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(Integer customerId) {

        Properties properties = this.retrieveProperties();

        try (InputStream stream = Resources.getResourceAsStream(BATIS_CONFIG);
             SqlSession session = new SqlSessionFactoryBuilder().build(stream, properties).openSession();) {
            session.selectOne(DELETE_CUSTOMER, customerId);
            session.commit();

            logger.info("Succesfully deleted customer with ID:" + customerId);
        } catch (IOException e) {
            logger.info("File Not Found");
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Customer> getAll() {

        Properties properties = this.retrieveProperties();
        List<Customer> customers = null;

        try (InputStream stream = Resources.getResourceAsStream(BATIS_CONFIG);
             SqlSession session = new SqlSessionFactoryBuilder().build(stream, properties).openSession();) {
            customers = session.selectList(GET_ALL);
            session.commit();

            logger.info("Succesfully retrieved all customers in database");
        } catch (IOException e) {
            logger.info("File Not Found");
            throw new RuntimeException(e);
        }

        return customers;
    }


    private Properties retrieveProperties() {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("src/main/resources/database.properties")) {
            properties.load(fis);
            return  properties;
        } catch (FileNotFoundException e) {
            logger.info("File not found" + e);
        } catch (IOException e) {
            logger.info("Input Output Exception Occured" + e);
        }
        return properties;
    }
}
