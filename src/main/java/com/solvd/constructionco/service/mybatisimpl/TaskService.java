package com.solvd.constructionco.service.mybatisimpl;

import com.solvd.constructionco.Main;
import com.solvd.constructionco.dao.IProjectDAO;
import com.solvd.constructionco.dao.ITaskDAO;
import com.solvd.constructionco.models.Customer;
import com.solvd.constructionco.models.Project;
import com.solvd.constructionco.models.Task;
import com.solvd.constructionco.util.SQLSessionUtil;
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
    private static final SQLSessionUtil sessionUtil = new SQLSessionUtil();

    @Override
    public Task getById(Integer taskId) {
        if (taskId > 0) {
            Task task = null;

            SqlSession session = sessionUtil.retrieveSqlSession();
            ITaskDAO taskDAO = session.getMapper(ITaskDAO.class);
            task = (Task) taskDAO.getById(taskId);

            //Commits and closes
            //Until session.commit is closed changes are not persisted to the database
            session.commit();
            session.close();
            return task;
        } else {
            throw new RuntimeException("Invalid TaskId entered");
        }
    }

    @Override
    public void save(Task task) {
        if (task != null) {
            SqlSession session = sessionUtil.retrieveSqlSession();
            ITaskDAO taskDAO = session.getMapper(ITaskDAO.class);

            taskDAO.save(task);

            session.commit();
            session.close();

            logger.info("Succesfully saved task to database");
        } else {
            throw new NullPointerException("Task is null");
        }
    }

    @Override
    public void update(Task task) {
        if (task != null) {
            SqlSession session = sessionUtil.retrieveSqlSession();
            ITaskDAO taskDAO = session.getMapper(ITaskDAO.class);

            taskDAO.update(task);

            session.commit();
            session.close();

            logger.info("Succesfully updated task in database");
        } else {
            throw new NullPointerException("Task is null");
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
