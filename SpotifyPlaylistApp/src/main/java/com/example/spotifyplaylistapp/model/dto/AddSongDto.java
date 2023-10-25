package com.example.spotifyplaylistapp.model.dto;

import com.example.spotifyplaylistapp.model.entity.Style;
import com.example.spotifyplaylistapp.model.entity.StyleNameEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class AddSongDto {

    @Size(min = 3, max = 20, message = "Length must be between 3 and 20 characters!")
    @NotNull
    private String performer;
    @Size(min = 2, max = 20, message = "Length must be between 3 and 20 characters!")
    @NotNull
    private String title;
    @Positive
    @NotNull
    private Integer duration;
    @PastOrPresent(message = "Date cannot be in the future!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    @NotNull(message = "You must select a style!")
    private StyleNameEnum style;

    public AddSongDto() {
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

    public StyleNameEnum getStyle() {
        return style;
    }

    public void setStyle(StyleNameEnum style) {
        this.style = style;
    }
}
