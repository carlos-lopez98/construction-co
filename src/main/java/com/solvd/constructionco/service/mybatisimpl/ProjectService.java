package com.solvd.constructionco.service.mybatisimpl;

import com.solvd.constructionco.Main;
import com.solvd.constructionco.dao.ICustomerDAO;
import com.solvd.constructionco.dao.IProjectDAO;
import com.solvd.constructionco.models.Customer;
import com.solvd.constructionco.models.Project;
import com.solvd.constructionco.service.interfaces.IProjectService;
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

public class ProjectService implements IProjectService {

    private static final Logger logger = LogManager.getLogger(Main.class);
    private static final SQLSessionUtil sessionUtil = new SQLSessionUtil();

    @Override
    public Project getById(Integer projectId) {
        if (projectId > 0) {
            Project project = null;

            SqlSession session = sessionUtil.retrieveSqlSession();
            IProjectDAO projectDAO = session.getMapper(IProjectDAO.class);
            project = (Project) projectDAO.getById(projectId);

            //Commits and closes
            //Until session.commit is closed changes are not persisted to the database
            session.commit();
            session.close();
            return project;
        } else {
            throw new RuntimeException("Invalid ProjectId Entered");
        }
    }

    @Override
    public void save(Project project) {
        Properties properties = this.retrieveProperties();

        try (InputStream stream = Resources.getResourceAsStream(BATIS_CONFIG);
             SqlSession session = new SqlSessionFactoryBuilder().build(stream, properties).openSession();) {
            session.selectOne(SAVE_PROJECT, project);
            session.commit();
        } catch (IOException e) {
            logger.info("File Not Found");
        }

    }

    @Override
    public void update(Project project) {

        Properties properties = this.retrieveProperties();

        try (InputStream stream = Resources.getResourceAsStream(BATIS_CONFIG);
             SqlSession session = new SqlSessionFactoryBuilder().build(stream, properties).openSession();) {
            session.selectOne(UPDATE_PROJECT, project);
            session.commit();
        } catch (IOException e) {
            logger.info("File Not Found");
        }

    }

    @Override
    public void delete(Integer projectId) {

        Properties properties = this.retrieveProperties();

        try (InputStream stream = Resources.getResourceAsStream(BATIS_CONFIG);
             SqlSession session = new SqlSessionFactoryBuilder().build(stream, properties).openSession();) {
            session.selectOne(DELETE_PROJECT, projectId);
            session.commit();

            logger.info("Succesfully deleted project with ID:" + projectId);
        } catch (IOException e) {
            logger.info("File Not Found");
        }

    }

    @Override
    public List<Project> getAll() {
        Properties properties = this.retrieveProperties();
        List<Project> projects = null;

        try (InputStream stream = Resources.getResourceAsStream(BATIS_CONFIG);
             SqlSession session = new SqlSessionFactoryBuilder().build(stream, properties).openSession();) {
            projects = session.selectList(GET_ALL);
            session.commit();

            logger.info("Succesfully retrieved all projects in database");
        } catch (IOException e) {
            logger.info("File Not Found");
        }

        return projects;    }


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
