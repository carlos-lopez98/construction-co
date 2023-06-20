package com.solvd.constructionco.dao;

import java.util.List;

public interface ITaskDAO<Task, Integer> extends ConstructionDAO<Task, Integer>{

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
