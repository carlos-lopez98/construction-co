package com.solvd.constructionco.dao;

import com.solvd.constructionco.models.Project;

import java.util.List;

public interface iProjectDAO<Project, Integer> extends ConstructionDAO<Project, Integer>{

    @Override
    Project getById(Integer purchaseOrderID);

    @Override
    void save(Project project);

    @Override
    void update(Project project);

    @Override
    void delete(Integer purchaseOrderID);

    @Override
    List<Project> getAll();
}
