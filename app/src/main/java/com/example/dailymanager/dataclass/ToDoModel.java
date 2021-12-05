package com.example.dailymanager.dataclass;

public class ToDoModel {

    private String task;

    public ToDoModel(int id, int status, String task) {

        this.task = task;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
}
