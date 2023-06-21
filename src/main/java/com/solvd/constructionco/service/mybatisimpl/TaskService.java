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
        if (taskId > 0) {
            SqlSession session = sessionUtil.retrieveSqlSession();
            ITaskDAO taskDAO = session.getMapper(ITaskDAO.class);
            Task task = (Task) taskDAO.getById(taskId);
            if (task != null) {
                taskDAO.delete(taskId);
                session.commit();
                session.close();
                logger.info("Succesfully deleted task from database");
            } else {
                throw new RuntimeException("taskId is not in database");
            }
        } else {
            throw new RuntimeException("TaskId given is not a valid ID");
        }
    }

    @Override
    public List<Task> getAll() {

        SqlSession session = sessionUtil.retrieveSqlSession();
        ITaskDAO taskDAO = session.getMapper(ITaskDAO.class);

        List<Task> tasks = taskDAO.getAll();

        if (tasks.isEmpty()) {
            throw new RuntimeException("No tasks in Database");
        } else {
            session.commit();
            session.close();
            return tasks;
        }
    }
}
