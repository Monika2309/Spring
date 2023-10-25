package com.example.spotifyplaylistapp.service.impl;

import com.example.spotifyplaylistapp.model.dto.AddSongDto;
import com.example.spotifyplaylistapp.model.dto.SongDto;
import com.example.spotifyplaylistapp.model.dto.SongHomeViewModel;
import com.example.spotifyplaylistapp.model.entity.Song;
import com.example.spotifyplaylistapp.model.entity.Style;
import com.example.spotifyplaylistapp.model.entity.StyleNameEnum;
import com.example.spotifyplaylistapp.model.entity.User;
import com.example.spotifyplaylistapp.repository.SongRepository;
import com.example.spotifyplaylistapp.repository.StyleRepository;
import com.example.spotifyplaylistapp.repository.UserRepository;
import com.example.spotifyplaylistapp.service.SongService;
import com.example.spotifyplaylistapp.util.LoggedUser;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SongServiceImpl implements SongService {

    private SongRepository songRepository;
    private UserRepository userRepository;
    private StyleRepository styleRepository;
    private LoggedUser loggedUser;

    public SongServiceImpl(SongRepository songRepository, UserRepository userRepository, StyleRepository styleRepository, LoggedUser loggedUser) {
        this.songRepository = songRepository;
        this.userRepository = userRepository;
        this.styleRepository = styleRepository;
        this.loggedUser = loggedUser;
    }

    @Override
    public SongHomeViewModel getHomeViewData(LoggedUser loggedUser) {
        User user = userRepository.findByUsername(loggedUser.getUsername());
        List<SongDto> pop = songRepository.findByStyle_Name(StyleNameEnum.POP)
                .stream().map(SongDto::MapToSongDto).collect(Collectors.toList());
        List<SongDto> rock = songRepository.findByStyle_Name(StyleNameEnum.ROCK)
                .stream().map(SongDto::MapToSongDto).collect(Collectors.toList());
        List<SongDto> jazz = songRepository.findByStyle_Name(StyleNameEnum.JAZZ)
                .stream().map(SongDto::MapToSongDto).collect(Collectors.toList());
        List<SongDto> myPlaylist = user.getPlaylist()
                .stream().map(SongDto::MapToSongDto).collect(Collectors.toList());
        return new SongHomeViewModel(pop, rock, jazz, myPlaylist);
    }

    @Override
    public void addToDB(AddSongDto addSongDto) {
        Style style = styleRepository.findByName(addSongDto.getStyle());
        if (style != null) {
            Song song = new Song();
            song.setPerformer(addSongDto.getPerformer());
            song.setTitle(addSongDto.getTitle());
            song.setDuration(addSongDto.getDuration());
            song.setStyle(style);
            if (addSongDto.getDate() != null) {
                song.setDate(addSongDto.getDate());
            }

            songRepository.save(song);
        }
    }

    @Override
    public void delete() {
        User user = userRepository.findByUsername(loggedUser.getUsername());
        user.setPlaylist(new HashSet<>());
        userRepository.save(user);
    }

    @Override
    public void add(Long id, LoggedUser loggedUser) {
        Song song = songRepository.getById(id);
        User user = userRepository.findByUsername(loggedUser.getUsername());

        user.getPlaylist().add(song);
        userRepository.save(user);

    }

    @Override
    public String getTime() {
        Integer totalTime = getTotalMin();
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
    @Override
    public Integer getTotalMin() {
        User user = userRepository.findByUsername(loggedUser.getUsername());
        Integer total = 0;
        for (Song song: user.getPlaylist()) {
            total += song.getDuration();
        }

        return total;
    }

}
