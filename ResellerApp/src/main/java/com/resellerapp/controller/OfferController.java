package com.resellerapp.controller;

import com.resellerapp.model.dto.AddOfferDto;
import com.resellerapp.service.OfferService;
import com.resellerapp.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class OfferController {

    private final OfferService offerService;
    private final LoggedUser loggedUser;

    public OfferController(OfferService offerService, LoggedUser loggedUser) {
        this.offerService = offerService;
        this.loggedUser = loggedUser;
    }

    @GetMapping("/offers/add")
    public ModelAndView add(@ModelAttribute("addOfferDto") AddOfferDto addOfferDto) {
        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }
        return new ModelAndView("offer-add");
    }

    @PostMapping("/offers/add")
    public ModelAndView add(@ModelAttribute("addOfferDto") @Valid AddOfferDto addOfferDto,
                            BindingResult bindingResult) {
        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }
        if (bindingResult.hasErrors()) {
            return new ModelAndView("task-add");
        }
        offerService.add(addOfferDto);

        return new ModelAndView("redirect:/home");
    }

    @PostMapping("/offers/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) {

        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }
        offerService.delete(id);

        return new ModelAndView("redirect:/home");
    }

    @PostMapping("/offers/buy/{id}")
    public ModelAndView buy(@PathVariable("id") Long id) {

        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/");
        }
        offerService.buy(id, loggedUser.getUsername());

        return new ModelAndView("redirect:/home");
    }

}
