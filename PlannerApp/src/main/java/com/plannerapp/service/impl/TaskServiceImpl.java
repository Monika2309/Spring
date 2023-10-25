package com.plannerapp.service.impl;

import com.plannerapp.model.dto.TaskAddDto;
import com.plannerapp.model.dto.TaskDto;
import com.plannerapp.model.dto.TaskHomeViewModel;
import com.plannerapp.model.entity.Priority;
import com.plannerapp.model.entity.Task;
import com.plannerapp.model.entity.User;
import com.plannerapp.repo.PriorityRepository;
import com.plannerapp.repo.TaskRepository;
import com.plannerapp.repo.UserRepository;
import com.plannerapp.service.TaskService;
import com.plannerapp.util.LoggedUser;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final PriorityRepository priorityRepository;
    private final UserRepository userRepository;

    public TaskServiceImpl(TaskRepository taskRepository, PriorityRepository priorityRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.priorityRepository = priorityRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void add(TaskAddDto taskAddDto) {
        Priority priority = priorityRepository.findByName(taskAddDto.getPriority());

        if (priority != null) {
            Task task = new Task();
            task.setDescription(taskAddDto.getDescription());
            task.setDueDate(LocalDate.parse(taskAddDto.getDueDate()));
            task.setPriority(priority);

            taskRepository.save(task);
        }
    }

    @Override
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public void assign(Long id, String username) {
        Optional<Task> taskToAssign = taskRepository.findById(id);

        if (taskToAssign.isPresent()) {
            Task task = taskToAssign.get();

            if (username == null) {
                task.setAssignee(null);
            } else {
                User user = userRepository.findByUsername(username);
                task.setAssignee(user);
            }

            taskRepository.save(task);
        }

    }

    @Override
    public TaskHomeViewModel getHomeViewData(LoggedUser loggedUser) {

        User user = userRepository.findByUsername(loggedUser.getUsername());
        List<TaskDto> tasksAssigned = taskRepository.findByAssignee(user)
                .stream().map(TaskDto::CreateFromUser).collect(Collectors.toList());
        List<TaskDto> tasksAvailable = taskRepository.getAllAvailable()
                .stream().map(TaskDto::CreateFromUser).collect(Collectors.toList());

        return new TaskHomeViewModel(tasksAssigned, tasksAvailable);
    }
}
