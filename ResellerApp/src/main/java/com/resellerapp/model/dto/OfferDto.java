package com.resellerapp.model.dto;

import com.resellerapp.model.entity.Condition;
import com.resellerapp.model.entity.ConditionNameEnum;
import com.resellerapp.model.entity.Offer;
import com.resellerapp.model.entity.User;

import java.math.BigDecimal;

public class OfferDto {

    private Long id;
    private String description;
    private BigDecimal price;
    private ConditionNameEnum condition;
    private String user;

    public OfferDto(){

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ConditionNameEnum getCondition() {
        return condition;
    }

    public void setCondition(ConditionNameEnum condition) {
        this.condition = condition;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public static OfferDto CreateFromUser(Offer offer) {
        OfferDto offerDto = new OfferDto();

        offerDto.setId(offer.getId());
        offerDto.setDescription(offer.getDescription());
        offerDto.setCondition(offer.getCondition().getName());
        offerDto.setPrice(offer.getPrice());
        offerDto.setUser(offer.getUser().getUsername());

        return offerDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
