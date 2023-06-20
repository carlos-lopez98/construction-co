package com.solvd.constructionco.service.impl;

import com.solvd.constructionco.dao.impl.ProjectDAO;
import com.solvd.constructionco.models.Project;
import com.solvd.constructionco.service.interfaces.IProjectService;

import java.util.List;

public class ProjectService implements IProjectService {

    private static final ProjectDAO projectDAO = new ProjectDAO();

    public ProjectService(){

    }

    @Override
    public Project getById(Integer purchaseOrderID) {
        return projectDAO.getById(purchaseOrderID);
    }

    @Override
    public void save(Project project) {
            projectDAO.save(project);
    }

    @Override
    public void update(Project project) {
            projectDAO.update(project);
    }

    @Override
    public void delete(Integer purchaseOrderID) {
        projectDAO.delete(purchaseOrderID);
    }

    @Override
    public List getAll() {
        return projectDAO.getAll();
    }
}
