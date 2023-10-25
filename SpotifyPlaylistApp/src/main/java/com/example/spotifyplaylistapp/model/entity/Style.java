package com.example.spotifyplaylistapp.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "styles")
public class Style extends BaseEntity{

    @Enumerated(value = EnumType.STRING)
    @Column(unique = true, nullable = false)
    private StyleNameEnum name;
    @Column(columnDefinition = "TEXT")
    private String description;

    public Style() {

    }

    public StyleNameEnum getName() {
        return name;
    }

    public void setName(StyleNameEnum name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
