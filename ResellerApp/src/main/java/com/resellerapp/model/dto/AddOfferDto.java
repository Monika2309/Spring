package com.resellerapp.model.dto;

import com.resellerapp.model.entity.ConditionNameEnum;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class AddOfferDto {

    @NotNull
    @Size(min = 2, max = 50, message = "Description length must be between 2 and 50 characters!")
    private String description;
    @NotNull
    @Positive
    private BigDecimal price;
    @NotNull
    private ConditionNameEnum condition;

    private AddOfferDto() {

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
}
