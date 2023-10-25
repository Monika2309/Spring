package com.resellerapp.service;

import com.resellerapp.model.dto.AddOfferDto;
import com.resellerapp.model.dto.OfferHomeViewModel;
import com.resellerapp.util.LoggedUser;

public interface OfferService {
    void add(AddOfferDto addOfferDto);

    void delete(Long id);

    void buy(Long id, String username);

    OfferHomeViewModel getHomeViewData(LoggedUser loggedUser);
}
