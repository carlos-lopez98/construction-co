package com.solvd.constructionco.service.impl;

import com.solvd.constructionco.dao.impl.TaskDAO;
import com.solvd.constructionco.models.Task;
import com.solvd.constructionco.service.interfaces.ITaskService;

import java.util.List;

public class TaskService implements ITaskService {

    private static final TaskDAO taskDAO = new TaskDAO();

    public TaskService( ){

    }


    @Override
    public Task getById(Integer taskID) {
        return taskDAO.getById(taskID);
    }

    @Override
    public void save(Task task) {
        taskDAO.save(task);
    }

    @Override
    public void update(Task task) {
        taskDAO.update(task);
    }

    @Override
    public void delete(Integer taskID) {
        taskDAO.delete(taskID);
    }

    @Override
    public List<Task> getAll() {
        return taskDAO.getAll();
    }
}
