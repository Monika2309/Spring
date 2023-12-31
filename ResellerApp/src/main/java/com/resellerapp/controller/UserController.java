package com.resellerapp.controller;



import com.resellerapp.model.dto.UserLoginDto;
import com.resellerapp.model.dto.UserRegisterDto;
import com.resellerapp.service.UserService;
import com.resellerapp.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UserController {

    private final UserService userService;
    private final LoggedUser loggedUser;

    public UserController(UserService userService, LoggedUser loggedUser) {
        this.userService = userService;
        this.loggedUser = loggedUser;
    }

    @GetMapping("/login")
    public ModelAndView login(@ModelAttribute("userLoginDto") UserLoginDto userLoginDto) {
        if (loggedUser.isLogged()) {
            return new ModelAndView("redirect:/home");
        }
        return new ModelAndView("login");
    }

    @PostMapping("/login")
    public ModelAndView login(@ModelAttribute("userLoginDto") @Valid UserLoginDto userLoginDto,
                              BindingResult bindingResult) {

        if (loggedUser.isLogged()) {
            return new ModelAndView("redirect:/home");
        }
        if (bindingResult.hasErrors()) {
            return new ModelAndView("login");
        }
        boolean successfulLogin = userService.login(userLoginDto);
        if (!successfulLogin) {
            ModelAndView modelAndView = new ModelAndView("login");
            modelAndView.addObject("loginError", true);
            return modelAndView;
        }
        return new ModelAndView("redirect:/home");
    }

    @GetMapping("/register")
    public ModelAndView register(@ModelAttribute("userRegisterDto") UserRegisterDto userRegisterDto) {
        if (loggedUser.isLogged()) {
            return new ModelAndView("redirect:/home");
        }
        return new ModelAndView("register");
    }

    @PostMapping("/register")
    public ModelAndView register(@ModelAttribute("userRegisterDto") @Valid UserRegisterDto userRegisterDto,
                                 BindingResult bindingResult) {

        if (loggedUser.isLogged()) {
            return new ModelAndView("redirect:/home");
        }
        if (bindingResult.hasErrors()) {
            return new ModelAndView("register");
        }
        boolean successfulRegistration = userService.register(userRegisterDto);
        if (!successfulRegistration) {
            ModelAndView modelAndView = new ModelAndView("register");
            modelAndView.addObject("registrationError", true);
            return modelAndView;
        }
        return new ModelAndView("redirect:/login");
    }

    @GetMapping("/logout")
    public ModelAndView logout() {
        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/home");
        }
        this.userService.logout();
        return new ModelAndView("redirect:/");
    }

}
