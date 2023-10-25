package com.example.spotifyplaylistapp.model.dto;

import com.example.spotifyplaylistapp.model.entity.Song;

public class SongDto {

    private Long id;

    private String performer;

    private String title;

    private Integer duration;

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

    public static SongDto MapToSongDto(Song song) {
        SongDto songDto = new SongDto();

        songDto.setId(song.getId());
        songDto.setPerformer(song.getPerformer());
        songDto.setTitle(song.getTitle());
        songDto.setDuration(songDto.getDuration());

        return songDto;
    }

    public String getDurationString() {
        int totalTime = this.getDuration();
        String totalResult = "";
        int minutes = 0;
        int seconds = 0;
        while (totalTime - 59 > 0) {
            minutes++;
            totalTime = totalTime - 60;
        }
        seconds = totalTime;
        totalResult = minutes + ":" + seconds;
        return totalResult;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
