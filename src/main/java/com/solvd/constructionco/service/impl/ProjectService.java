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

        if(purchaseOrderID == null || purchaseOrderID < 0){
            throw new IllegalArgumentException("PurchaseOrderId provided is not in correct format");
        }

        Project project = projectDAO.getById(purchaseOrderID);

        if (project == null){
            throw new RuntimeException("Project is not in database");
        }

        return project;
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
