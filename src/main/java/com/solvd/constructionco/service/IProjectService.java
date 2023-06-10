package com.solvd.constructionco.service;

import com.solvd.constructionco.models.Project;

import java.util.List;

public interface IProjectService extends ConstructionServiceOperations<Project, Integer>{

    @Override
    <T> T getById(Integer projectID);

    @Override
    void save(Project project);

    @Override
    void update(Project project);

    @Override
    void delete(Integer projectID);

    @Override
    List<Project> getAll();
}
