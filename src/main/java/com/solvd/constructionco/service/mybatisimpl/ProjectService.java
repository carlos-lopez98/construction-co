package com.solvd.constructionco.service.mybatisimpl;

import com.solvd.constructionco.Main;
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
        return null;
    }

    @Override
    public void save(Project project) {
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
    public void update(Project project) {

    }

    @Override
    public void delete(Integer projectId) {

    }

    @Override
    public List<Project> getAll() {
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
