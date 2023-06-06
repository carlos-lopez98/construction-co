package com.solvd.constructionco.dao;

import com.solvd.constructionco.interfaces.ConstructionDAO;
import com.solvd.constructionco.models.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskDAO implements ConstructionDAO<Task, Integer> {
    private List<Task> tasks;

    public TaskDAO() {
        tasks = new ArrayList<>();
    }

    @Override
    public Task getById(Integer id) {
        for (Task task : tasks) {
            if (task.getTaskId() == id) {
                return task;
            }
        }
        return null;
    }

    @Override
    public void save(Task task) {
        tasks.add(task);
    }

    @Override
    public void update(Task task) {
        int taskId = task.getTaskId();
        for (int i = 0; i < tasks.size(); i++) {
            Task existingTask = tasks.get(i);
            if (existingTask.getTaskId() == taskId) {
                tasks.set(i, task);
                return;
            }
        }
    }

    @Override
    public void delete(Integer id) {
        tasks.removeIf(task -> task.getTaskId() == id);
    }

    @Override
    public List<Task> getAll() {
        return new ArrayList<>(tasks);
    }
}
