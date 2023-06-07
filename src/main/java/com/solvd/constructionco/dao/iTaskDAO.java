package com.solvd.constructionco.dao;

import com.solvd.constructionco.models.Task;

import java.util.List;

public interface iTaskDAO<Task, Integer> extends ConstructionDAO<Task, Integer>{

    @Override
    Task getById(Integer taskID);

    @Override
    void save(Task task);

    @Override
    void update(Task task);

    @Override
    void delete(Integer taskID);

    @Override
    List<Task> getAll();
}
