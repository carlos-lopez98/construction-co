package com.solvd.constructionco.dao;

import com.solvd.constructionco.interfaces.ConstructionDAO;
import com.solvd.constructionco.models.Project;

import java.util.ArrayList;
import java.util.List;

public class ProjectDAO implements ConstructionDAO<Project, Integer> {
    private List<Project> projects;

    public ProjectDAO() {
        projects = new ArrayList<>();
    }

    @Override
    public Project getById(Integer id) {
        for (Project project : projects) {
            if (project.getPurchaseOrderId() == id) {
                return project;
            }
        }
        return null;
    }

    @Override
    public void save(Project project) {
        projects.add(project);
    }

    @Override
    public void update(Project project) {
        int projectId = project.getPurchaseOrderId();
        for (int i = 0; i < projects.size(); i++) {
            Project existingProject = projects.get(i);
            if (existingProject.getPurchaseOrderId() == projectId) {
                projects.set(i, project);
                return;
            }
        }
    }

    @Override
    public void delete(Integer id) {
        projects.removeIf(project -> project.getPurchaseOrderId() == id);
    }

    @Override
    public List<Project> getAll() {
        return new ArrayList<>(projects);
    }
}
