package com.resellerapp.model.dto;

import java.util.List;

public class OfferHomeViewModel {

    private List<OfferDto> myOffers;
    private List<OfferDto> allOffers;
    private List<OfferDto> boughtItems;

    public OfferHomeViewModel(List<OfferDto> myOffers, List<OfferDto> allOtherOffers, List<OfferDto> allBoughtOffers) {
        this.allOffers = allOtherOffers;
        this.myOffers = myOffers;
        this.boughtItems = allBoughtOffers;
    }

    public List<OfferDto> getMyOffers() {
        return myOffers;
    }

    public void setMyOffers(List<OfferDto> myOffers) {
        this.myOffers = myOffers;
    }

    public List<OfferDto> getAllOffers() {
        return allOffers;
    }

    public void setAllOffers(List<OfferDto> allOffers) {
        this.allOffers = allOffers;
    }

    public List<OfferDto> getBoughtItems() {
        return boughtItems;
    }

    public void setBoughtItems(List<OfferDto> boughtItems) {
        this.boughtItems = boughtItems;
    }
}
