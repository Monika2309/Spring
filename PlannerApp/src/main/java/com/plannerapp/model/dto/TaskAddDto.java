package com.plannerapp.model.dto;


import com.plannerapp.model.entity.PriorityName;
import com.plannerapp.vallidation.annotation.StringDateInFuture;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class TaskAddDto {

    @Size(min = 2, max = 50, message = "Description must be between 3 and 50 characters!")
    private String description;

    @StringDateInFuture(message = "Due Date must be in future!")
    private String dueDate;

    @NotNull(message = "You must select a priority!")
    private PriorityName priority;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public PriorityName getPriority() {
        return priority;
    }

    public void setPriority(PriorityName priority) {
        this.priority = priority;
    }
}
