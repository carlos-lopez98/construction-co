package com.solvd.constructionco.service.mybatisimpl;

import com.solvd.constructionco.Main;
import com.solvd.constructionco.dao.ITaskDAO;
import com.solvd.constructionco.models.Customer;
import com.solvd.constructionco.models.Task;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class TaskService implements ITaskDAO<Task, Integer> {

    private static final Logger logger = LogManager.getLogger(Main.class);
    private static final String BATIS_CONFIG = "src/main/resources/config/mybatis-config.xml";
    private static final String SAVE_TASK = "com.solvd.constructionco.mybatis.impl.taskmapper.save";
    private static final String GET_BY_ID = "com.solvd.constructionco.mybatis.impl.taskmapper.getById";
    private static final String UPDATE_TASK = "com.solvd.constructionco.mybatis.impl.taskmapper.update";
    private static final String DELETE_TASK = "com.solvd.constructionco.mybatis.impl.taskmapper.delete";
    private static final String GET_ALL = "com.solvd.constructionco.mybatis.impl.taskmapper.getAll";

    @Override
    public Task getById(Integer taskId) {
        Properties prop = this.retrieveProperties();
        Task task = null;

        try (InputStream stream = Resources.getResourceAsStream(BATIS_CONFIG);
             SqlSession session = new SqlSessionFactoryBuilder().build(stream, prop).openSession();) {

            task = session.selectOne(GET_BY_ID, taskId);

            return task;
        } catch (IOException e) {
            logger.info("File Not Found");
        }

        return task;
    }

    @Override
    public void save(Task task) {
        Properties properties = this.retrieveProperties();

        try (InputStream stream = Resources.getResourceAsStream(BATIS_CONFIG);
             SqlSession session = new SqlSessionFactoryBuilder().build(stream, properties).openSession();) {
            session.selectOne(SAVE_TASK, task);
            session.commit();
        } catch (IOException e) {
            logger.info("File Not Found");
        }
    }

    @Override
    public void update(Task task) {
        Properties properties = this.retrieveProperties();

        try (InputStream stream = Resources.getResourceAsStream(BATIS_CONFIG);
             SqlSession session = new SqlSessionFactoryBuilder().build(stream, properties).openSession();) {
            session.selectOne(UPDATE_TASK, task);
            session.commit();
        } catch (IOException e) {
            logger.info("File Not Found");
        }
    }

    @Override
    public void delete(Integer taskId) {
        Properties properties = this.retrieveProperties();

        try (InputStream stream = Resources.getResourceAsStream(BATIS_CONFIG);
             SqlSession session = new SqlSessionFactoryBuilder().build(stream, properties).openSession();) {
            session.selectOne(DELETE_TASK, taskId);
            session.commit();
        } catch (IOException e) {
            logger.info("File Not Found");
        }
    }

    @Override
    public List<Task> getAll() {

        Properties properties = this.retrieveProperties();
        List<Task> tasks = null;

        try (InputStream stream = Resources.getResourceAsStream(BATIS_CONFIG);
             SqlSession session = new SqlSessionFactoryBuilder().build(stream, properties).openSession();) {
            tasks = session.selectList(GET_ALL);
            session.commit();

            logger.info("Succesfully retrieved all tasks in database");
        } catch (IOException e) {
            logger.info("File Not Found");
        }

        return tasks;
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
