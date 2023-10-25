package com.example.spotifyplaylistapp.controller;

import com.example.spotifyplaylistapp.model.dto.AddSongDto;
import com.example.spotifyplaylistapp.service.SongService;
import com.example.spotifyplaylistapp.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class SongController {
    private final SongService songService;
    private final LoggedUser loggedUser;

    public SongController(SongService songService, LoggedUser loggedUser) {
        this.songService = songService;
        this.loggedUser = loggedUser;
    }

    @GetMapping("/songs/add")
    public ModelAndView add(@ModelAttribute("addSongDto") AddSongDto addSongDto) {
        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }
        return new ModelAndView("song-add");
    }

    @PostMapping("/songs/addToDB")
    public ModelAndView addToDB(@ModelAttribute("addSongDto") @Valid AddSongDto addSongDto,
                            BindingResult bindingResult) {
        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }
        if (bindingResult.hasErrors()) {
            return new ModelAndView("song-add");
        }
        songService.addToDB(addSongDto);

        return new ModelAndView("redirect:/home");
    }

    @PostMapping("/songs/delete")
    public ModelAndView delete() {

        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }
        songService.delete();

        return new ModelAndView("redirect:/home");
    }

    @PostMapping("/songs/add/{id}")
    public ModelAndView add(@PathVariable("id") Long id) {

        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }
        songService.add(id, loggedUser);

        return new ModelAndView("redirect:/home");
    }
}
