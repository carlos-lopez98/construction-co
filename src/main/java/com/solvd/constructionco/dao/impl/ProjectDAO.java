package com.solvd.constructionco.dao.impl;

import com.solvd.constructionco.Main;
import com.solvd.constructionco.dao.IProjectDAO;
import com.solvd.constructionco.models.Customer;
import com.solvd.constructionco.models.Project;
import com.solvd.constructionco.util.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectDAO implements IProjectDAO<Project, Integer> {

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final Logger logger = LogManager.getLogger(Main.class);

    private static final String GET_ALL_QUERY = "SELECT purchase_order_id, purchaseorder_name, budget, status FROM purchase_order";
    private static final String GET_BY_ID_QUERY = "SELECT purchase_order_id, purchaseorder_name, budget, status FROM purchase_order WHERE purchase_order_id = ?";
    private static final String SAVE_QUERY = "INSERT INTO purchase_order VALUES(?,?,?,?)";
    private static final String DELETE_QUERY = "DELETE FROM purchase_order WHERE purchase_order_id = ?";
    private static final String UPDATE_QUERY = "UPDATE purchase_order SET purchase_order_id = ?, purchaseorder_name = ?, budget = ?, description = ?, status = ? WHERE purchase_order_id = ?";


    public ProjectDAO() {
    }

    @Override
    public Project getById(Integer purchaseOrderId) {
        Project purchaseOrder = null;
        //Try with resources auto closes connection
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_ID_QUERY)) {
            statement.setInt(1, purchaseOrderId);

            //Try with resources - auto closes resultSet
            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    purchaseOrder.setPurchaseOrderId(resultSet.getInt("purchase_order_id"));
                    purchaseOrder.setPurchaseOrderName(resultSet.getString("purchaseorder_name"));
                    purchaseOrder.setBudget(resultSet.getInt("budget"));
                    purchaseOrder.setClosed(resultSet.getBoolean("status"));
                }
            }
        } catch (SQLException e) {
            logger.info("SQL Exception Occurred: " + e.getMessage());
        }

        return purchaseOrder;
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
