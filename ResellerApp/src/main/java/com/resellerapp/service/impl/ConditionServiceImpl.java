package com.resellerapp.service.impl;

import com.resellerapp.repository.ConditionRepository;
import com.resellerapp.service.ConditionService;
import org.springframework.stereotype.Service;

@Service
public class ConditionServiceImpl implements ConditionService {

    private final ConditionRepository conditionRepository;

    public ConditionServiceImpl(ConditionRepository conditionRepository) {
        this.conditionRepository = conditionRepository;
    }
}
