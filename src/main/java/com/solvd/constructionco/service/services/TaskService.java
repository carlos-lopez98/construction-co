package com.solvd.constructionco.service.services;

import com.solvd.constructionco.dao.TaskDAO;
import com.solvd.constructionco.interfaces.ConstructionServiceOperations;
import com.solvd.constructionco.models.Task;

import java.util.List;

public class TaskService implements ConstructionServiceOperations<Task, Integer> {

    private TaskDAO taskDAO;

    public TaskService(TaskDAO taskDAO){
        this.taskDAO = taskDAO;
    }


    @Override
    public Task getById(Integer integer) {
        return null;
    }

    @Override
    public void save(Task entity) {

    }

    @Override
    public void update(Task entity) {

    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public List<Task> getAll() {
        return null;
    }
}
