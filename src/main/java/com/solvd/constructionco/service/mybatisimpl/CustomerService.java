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
    private static final String BATISCONFIG = "src/main/resources/config/mybatis-config.xml";
    private static final String SAVECUSTOMER = "com.solvd.constructionco.mybatis.impl.customermapper.save";



    @Override
    public <T> T getById(Integer customerID) {
        return null;
    }

    @Override
    public void save(Customer customer) {
        Properties properties = this.retrieveProperties();

        try (InputStream stream = Resources.getResourceAsStream(BATISCONFIG);
             SqlSession session = new SqlSessionFactoryBuilder().build(stream, properties).openSession();) {
            session.selectOne(SAVECUSTOMER, customer);
            session.commit();
        } catch (IOException e) {
            logger.info("File Not Found");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Customer customer) {

    }

    @Override
    public void delete(Integer customerID) {

    }

    @Override
    public List<Customer> getAll() {
        return null;
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
