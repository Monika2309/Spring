package com.resellerapp.init;

import com.resellerapp.model.entity.Condition;
import com.resellerapp.model.entity.ConditionNameEnum;
import com.resellerapp.repository.ConditionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ConditionInit implements CommandLineRunner {

    private final ConditionRepository conditionRepository;

    public ConditionInit(ConditionRepository conditionRepository) {
        this.conditionRepository = conditionRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Condition> conditionList = new ArrayList<>();
        if (conditionRepository.count() == 0) {
            Arrays.stream(ConditionNameEnum.values()).forEach(c -> {
                Condition condition = new Condition();
                condition.setName(c);
                conditionList.add(condition);
            });
        }
        conditionRepository.saveAll(conditionList);
    }
}
