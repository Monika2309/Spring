package com.resellerapp.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "conditions")
public class Condition extends BaseEntity{

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private ConditionNameEnum name;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    public ConditionNameEnum getName() {
        return name;
    }

    public void setName(ConditionNameEnum name) {
        this.name = name;
        setDescription(name);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(ConditionNameEnum name) {
        String desc = "";
        switch (name) {
            case EXCELLENT -> desc = "In perfect condition";
            case GOOD -> desc = "Some signs of wear and tear or minor defects";
            case ACCEPTABLE -> desc = "The item is fairly worn but continues to function properly";
        }

        this.description = desc;
    }
}
