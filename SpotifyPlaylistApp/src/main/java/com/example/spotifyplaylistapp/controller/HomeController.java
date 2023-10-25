package com.example.spotifyplaylistapp.controller;

import com.example.spotifyplaylistapp.model.dto.SongHomeViewModel;
import com.example.spotifyplaylistapp.service.SongService;
import com.example.spotifyplaylistapp.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    private final SongService songService;
    private final LoggedUser loggedUser;

    public HomeController(SongService songService, LoggedUser loggedUser) {
        this.songService = songService;
        this.loggedUser = loggedUser;
    }

    @GetMapping("/")
    public ModelAndView index() {
        if (loggedUser.isLogged()) {
            return new ModelAndView("redirect:/home");
        }
        return new ModelAndView("index");
    }

    @GetMapping("/home")
    public ModelAndView home() {
        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }
        SongHomeViewModel songHomeViewModel = songService.getHomeViewData(loggedUser);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(loggedUser);

        return new ModelAndView("home", "songs", songHomeViewModel);
    }
}
