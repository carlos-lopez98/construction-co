package com.solvd.constructionco.models;

import java.sql.Date;
import java.time.LocalDate;

public class Task {

    private int taskId;
    private String taskName;
    private boolean isClosed;
    private Date dueDate;

    public Task(){

    };

    public Task(String taskName){

    };

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}
