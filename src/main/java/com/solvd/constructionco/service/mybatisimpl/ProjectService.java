package com.solvd.constructionco.service.mybatisimpl;

import com.solvd.constructionco.Main;
import com.solvd.constructionco.models.Customer;
import com.solvd.constructionco.models.Project;
import com.solvd.constructionco.service.interfaces.IProjectService;
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

    private static final String BATIS_CONFIG = "src/main/resources/config/mybatis-config.xml";
    private static final String SAVE_PROJECT = "com.solvd.constructionco.mybatis.impl.customermapper.save";
    private static final String GET_BY_ID = "com.solvd.constructionco.mybatis.impl.customermapper.getById";
    private static final String UPDATE_PROJECT = "com.solvd.constructionco.mybatis.impl.customermapper.update";
    private static final String DELETE_PROJECT = "com.solvd.constructionco.mybatis.impl.customermapper.delete";
    private static final String GET_ALL = "com.solvd.constructionco.mybatis.impl.customermapper.getAll";


    @Override
    public Project getById(Integer projectId) {
        Properties prop = this.retrieveProperties();
        Project project = null;

        try (InputStream stream = Resources.getResourceAsStream(BATIS_CONFIG);
             SqlSession session = new SqlSessionFactoryBuilder().build(stream, prop).openSession();) {

            project = session.selectOne(GET_BY_ID, projectId);

            return project;
        } catch (IOException e) {
            logger.info("File Not Found");
        }

        return project;
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
