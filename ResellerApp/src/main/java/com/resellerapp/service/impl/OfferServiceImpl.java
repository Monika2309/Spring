package com.resellerapp.service.impl;

import com.resellerapp.model.dto.AddOfferDto;
import com.resellerapp.model.dto.OfferDto;
import com.resellerapp.model.dto.OfferHomeViewModel;
import com.resellerapp.model.entity.Condition;
import com.resellerapp.model.entity.Offer;
import com.resellerapp.model.entity.User;
import com.resellerapp.repository.ConditionRepository;
import com.resellerapp.repository.OfferRepository;
import com.resellerapp.repository.UserRepository;
import com.resellerapp.service.OfferService;
import com.resellerapp.util.LoggedUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final UserRepository userRepository;
    private final ConditionRepository conditionRepository;
    private final LoggedUser loggedUser;

    public OfferServiceImpl(OfferRepository offerRepository, UserRepository userRepository, ConditionRepository conditionRepository, LoggedUser loggedUser) {
        this.offerRepository = offerRepository;
        this.userRepository = userRepository;
        this.conditionRepository = conditionRepository;
        this.loggedUser = loggedUser;
    }

    @Override
    public void add(AddOfferDto addOfferDto) {
        Condition condition = conditionRepository.findByName(addOfferDto.getCondition());
        User user = userRepository.findByUsername(loggedUser.getUsername());
        if (condition != null) {
            Offer offer = new Offer();
            offer.setDescription(addOfferDto.getDescription());
            offer.setPrice(addOfferDto.getPrice());
            offer.setCondition(condition);
            offer.setUser(user);

            offerRepository.save(offer);
        }
    }

    @Override
    public void delete(Long id) {
        offerRepository.delete(offerRepository.getById(id));
    }

    @Override
    public void buy(Long id, String username) {
        Optional<Offer> offerToBuy = offerRepository.findById(id);

        if (offerToBuy.isPresent()) {
            Offer offer = offerToBuy.get();
            User user = userRepository.findByUsername(username);
            offer.setBoughtBy(user);
            user.getBoughtOffers().add(offer);
            user.getOffersCreatedBy().remove(offer);

            offerRepository.save(offer);
        }

    }

    @Override
    public OfferHomeViewModel getHomeViewData(LoggedUser loggedUser) {
        User user = userRepository.findByUsername(loggedUser.getUsername());
        List<OfferDto> myOffers = offerRepository.findByUser(user)
                .stream().map(OfferDto::CreateFromUser).collect(Collectors.toList());
        List<OfferDto> allOtherOffers = offerRepository.getAllAvailable()
                .stream().map(OfferDto::CreateFromUser).collect(Collectors.toList());
        List<OfferDto> allBoughtOffers = offerRepository.findAllByBoughtBy_Username(loggedUser.getUsername())
                .stream().map(OfferDto::CreateFromUser).collect(Collectors.toList());
        return new OfferHomeViewModel(myOffers, allOtherOffers, allBoughtOffers);
    }
}
