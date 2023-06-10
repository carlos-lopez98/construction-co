package com.solvd.constructionco.service.impl;

import com.solvd.constructionco.dao.impl.TaskDAO;
import com.solvd.constructionco.service.ConstructionServiceOperations;
import com.solvd.constructionco.models.Task;

import java.util.List;

public class TaskService implements ConstructionServiceOperations<Task, Integer> {

    private TaskDAO taskDAO;

    public TaskService(TaskDAO taskDAO){
        this.taskDAO = taskDAO;
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
