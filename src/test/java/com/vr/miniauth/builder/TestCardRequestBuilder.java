package com.vr.miniauth.builder;

import com.vr.miniauth.request.CardRequest;

import static com.vr.miniauth.builder.RandomUtils.randomCardNumber;
import static com.vr.miniauth.builder.RandomUtils.randomCardPassword;

public class TestCardRequestBuilder {

    private CardRequest cardRequest;

    public TestCardRequestBuilder(){
        this.cardRequest = new CardRequest();
    }

    public TestCardRequestBuilder withCardNumber(String cardNumber){
        this.cardRequest.setCardNumber(cardNumber);
        return this;
    }
    public TestCardRequestBuilder withPassword(String password){
        this.cardRequest.setPassword(password);
        return this;
    }

    public CardRequest build() {
        cardRequest.setPassword(cardRequest.getPassword() == null ? randomCardPassword(): cardRequest.getPassword());
        cardRequest.setCardNumber(cardRequest.getCardNumber() == null ? randomCardNumber() : cardRequest.getCardNumber());
        return this.cardRequest;
    }


}
