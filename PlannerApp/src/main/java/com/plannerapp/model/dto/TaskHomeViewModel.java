package com.plannerapp.model.dto;

import java.util.ArrayList;
import java.util.List;

public class TaskHomeViewModel {

    private List<TaskDto> myTasks;
    private List<TaskDto> availableTasks;

    public List<TaskDto> getMyTasks() {
        return myTasks;
    }

    public TaskHomeViewModel() {
        this.availableTasks = new ArrayList<>();
        this.myTasks = new ArrayList<>();
    }
    public TaskHomeViewModel(List<TaskDto> myTasks, List<TaskDto> availableTasks) {
        this.availableTasks = availableTasks;
        this.myTasks = myTasks;
    }

    public void setMyTasks(List<TaskDto> myTasks) {
        this.myTasks = myTasks;
    }

    public List<TaskDto> getAvailableTasks() {
        return availableTasks;
    }

    public void setAvailableTasks(List<TaskDto> availableTasks) {
        this.availableTasks = availableTasks;
    }


}
