package com.example.spotifyplaylistapp.model.dto;

import com.example.spotifyplaylistapp.model.entity.Song;

import java.util.List;

public class SongHomeViewModel {

    private List<SongDto> pop;
    private List<SongDto> rock;
    private List<SongDto> jazz;
    private List<SongDto> myPlaylist;

    private String totalMinutes;

    public SongHomeViewModel() {
    }

    public SongHomeViewModel(List<SongDto> pop, List<SongDto> rock, List<SongDto> jazz, List<SongDto> myPlaylist) {
        this.pop = pop;
        this.rock = rock;
        this.jazz = jazz;
        this.myPlaylist = myPlaylist;
    }


    public List<SongDto> getPop() {
        return pop;
    }

    public void setPop(List<SongDto> pop) {
        this.pop = pop;
    }

    public List<SongDto> getRock() {
        return rock;
    }

    public void setRock(List<SongDto> rock) {
        this.rock = rock;
    }

    public List<SongDto> getJazz() {
        return jazz;
    }

    public void setJazz(List<SongDto> jazz) {
        this.jazz = jazz;
    }

    public List<SongDto> getMyPlaylist() {
        return myPlaylist;
    }

    public void setMyPlaylist(List<SongDto> myPlaylist) {
        this.myPlaylist = myPlaylist;
    }

    public String getTotalMinutes() {
        return totalMinutes;
    }

    public void setTotalMinutes(String totalMinutes) {
        int totalTime = 0;

        for (SongDto s: myPlaylist) {
            totalTime += s.getDuration();
        }
        String totalResult = "";
        int minutes = 0;
        int seconds = 0;
        while (totalTime - 59 > 0) {
            minutes++;
            totalTime = totalTime - 60;
        }
        seconds = totalTime;
        totalResult = minutes + ":" + seconds;
        this.totalMinutes = totalResult;
    }
}
