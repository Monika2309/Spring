package com.plannerapp.service;

import com.plannerapp.model.dto.TaskAddDto;
import com.plannerapp.model.dto.TaskHomeViewModel;
import com.plannerapp.util.LoggedUser;

public interface TaskService {

    void add(TaskAddDto taskAddDto);

    void delete(Long id);
    void assign(Long id, String username);

    TaskHomeViewModel getHomeViewData(LoggedUser loggedUser);
}
