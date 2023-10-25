package com.example.spotifyplaylistapp.model.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "songs")
public class Song extends BaseEntity{

    @Column(nullable = false)
    private String performer;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private Integer duration;
    @Column
    private LocalDate date;
    @OneToOne(fetch = FetchType.EAGER)
    private Style style;


    public Song(){

    }

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

}
