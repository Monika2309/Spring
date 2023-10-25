package com.plannerapp.service.impl;

import com.plannerapp.repo.PriorityRepository;
import com.plannerapp.service.PriorityService;
import org.springframework.stereotype.Service;

@Service
public class PriorityServiceImpl implements PriorityService {

    private final PriorityRepository priorityRepository;

    public PriorityServiceImpl(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }
}
