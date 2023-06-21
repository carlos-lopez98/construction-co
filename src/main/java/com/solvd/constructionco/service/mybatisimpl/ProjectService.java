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
        if (project != null) {
            SqlSession session = sessionUtil.retrieveSqlSession();
            IProjectDAO projectDAO = session.getMapper(IProjectDAO.class);

            projectDAO.save(project);

            session.commit();
            session.close();

            logger.info("Succesfully saved project to database");
        } else {
            throw new NullPointerException("Project is null");
        }

    }

    @Override
    public void update(Project project) {
        if (project != null) {
            SqlSession session = sessionUtil.retrieveSqlSession();
            IProjectDAO projectDAO = session.getMapper(IProjectDAO.class);

            projectDAO.update(project);

            session.commit();
            session.close();

            logger.info("Succesfully updated project in database");
        } else {
            throw new NullPointerException("Project is null");
        }

    }

    @Override
    public void delete(Integer projectId) {

        if (projectId > 0) {
            SqlSession session = sessionUtil.retrieveSqlSession();
            IProjectDAO projectDAO = session.getMapper(IProjectDAO.class);
            Project project = (Project) projectDAO.getById(projectId);
            if (project != null) {
                projectDAO.delete(projectId);
                session.commit();
                session.close();
                logger.info("Succesfully deleted project to database");
            } else {
                throw new RuntimeException("projectId is not in database");
            }
        } else {
            throw new RuntimeException("ProjectId given is not a valid ID");
        }

    }

    @Override
    public List<Project> getAll() {
        SqlSession session = sessionUtil.retrieveSqlSession();
        IProjectDAO projectDAO = session.getMapper(IProjectDAO.class);

        List<Project> projects = projectDAO.getAll();

        if (projects.isEmpty()) {
            throw new RuntimeException("No projects in Database");
        } else {
            session.commit();
            session.close();
            return projects;
        }
    }
}
