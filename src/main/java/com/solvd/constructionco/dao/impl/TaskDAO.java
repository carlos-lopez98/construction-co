package com.solvd.constructionco.dao.impl;

import com.solvd.constructionco.dao.ConstructionDAO;
import com.solvd.constructionco.dao.iTaskDAO;
import com.solvd.constructionco.models.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskDAO implements iTaskDAO<Task, Integer> {
    private List<Task> tasks;

    private final String getAllQuery = "SELECT task_id, task_name, status, due_date FROM tasks";
    private final String getByIdQuery = "SELECT task_id, task_name, status, due_date FROM tasks WHERE task_id = ?";
    private final String saveQuery = "INSERT INTO tasks VALUES(?,?,?)";
    private final String deleteQuery = "DELETE FROM tasks WHERE task_id = ?";
    private final String updateQuery = "UPDATE tasks SET task_id = ?, task_name = ?, status = ?,due_date = ? WHERE task_id = ?";

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
