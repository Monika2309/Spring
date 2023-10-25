package com.plannerapp.init;

import com.plannerapp.model.entity.Priority;
import com.plannerapp.model.entity.PriorityName;
import com.plannerapp.repo.PriorityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class PriorityInit implements CommandLineRunner {

    private final PriorityRepository priorityRepository;

    public PriorityInit(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Priority> priorityList = new ArrayList<>();
        if (priorityRepository.count() == 0) {
            Arrays.stream(PriorityName.values()).forEach(priorityName -> {
                Priority priority = new Priority();
                priority.setName(priorityName);
                priorityList.add(priority);
            });
        }
        priorityRepository.saveAll(priorityList);
    }
}
