package com.solvd.constructionco.dao.impl;

import com.solvd.constructionco.dao.IProjectDAO;
import com.solvd.constructionco.models.Project;

import java.util.ArrayList;
import java.util.List;

public class ProjectDAO implements IProjectDAO<Project, Integer> {
    private List<Project> projects;

    private static final String GET_ALL_QUERY = "SELECT purchase_order_id, purchaseorder_name, budget, status FROM purchase_order";
    private static final String GET_BY_ID_QUERY = "SELECT purchase_order_id, purchaseorder_name, budget, status FROM purchase_order WHERE purchase_order_id = ?";
    private static final String SAVE_QUERY = "INSERT INTO purchase_order VALUES(?,?,?,?)";
    private static final String DELETE_QUERY = "DELETE FROM purchase_order WHERE purchase_order_id = ?";
    private static final String UPDATE_QUERY = "UPDATE purchase_order SET purchase_order_id = ?, purchaseorder_name = ?, budget = ?, description = ?, status = ? WHERE purchase_order_id = ?";


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
