package com.solvd.constructionco.service;

import com.solvd.constructionco.models.Task;

import java.util.List;

public interface ITaskService extends ConstructionServiceOperations<Task, Integer>{

    @Override
    <T> T getById(Integer taskID);

    @Override
    void save(Task task);

    @Override
    void update(Task task);

    @Override
    void delete(Integer taskID);

    @Override
    List<Task> getAll();
}
