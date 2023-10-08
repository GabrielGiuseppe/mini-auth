package com.vr.miniauth.builder;

import com.vr.miniauth.response.CardResponse;

import static com.vr.miniauth.builder.RandomUtils.randomCardNumber;
import static com.vr.miniauth.builder.RandomUtils.randomCardPassword;

public class TestCardResponseBuilder {

    private CardResponse cardResponse;

    public TestCardResponseBuilder(){
        this.cardResponse = new CardResponse();
    }

    public TestCardResponseBuilder withCardNumber(String cardNumber){
        this.cardResponse.setCardNumber(cardNumber);
        return this;
    }
    public TestCardResponseBuilder withPassword(String password){
        this.cardResponse.setPassword(password);
        return this;
    }

    public CardResponse build() {
        cardResponse.setPassword(cardResponse.getPassword() == null ? randomCardPassword(): cardResponse.getPassword());
        cardResponse.setCardNumber(cardResponse.getCardNumber() == null ? randomCardNumber() : cardResponse.getCardNumber());
        return this.cardResponse;
    }
}
