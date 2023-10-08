package com.vr.miniauth.builder;

import com.vr.miniauth.model.Card;

import java.math.BigDecimal;

import static com.vr.miniauth.builder.RandomUtils.*;

public class TestCardEntityBuilder {

    private Card card;

    public TestCardEntityBuilder(){
        this.card = new Card();
    }

    public Card build() {
        card.setId(card.id == null ? RandomUtils.randomId() : card.getId());
        card.setPassword(card.getPassword() == null ? randomCardEncryptedPassword(): card.getPassword());
        card.setNumber(card.getNumber() == null ? randomCardNumber() : card.getNumber());
        card.setBalance(card.getBalance() == null ? randomBalance() : card.getBalance());
        return this.card;
    }

    public TestCardEntityBuilder withId(Long id){
        card.setId(id);
        return this;
    }
    public TestCardEntityBuilder withPassword(String password){
        card.setPassword(password);
        return this;
    }
    public TestCardEntityBuilder withNumber(String number){
        card.setNumber(number);
        return this;
    }
    public TestCardEntityBuilder withBalance(BigDecimal balance){
        card.setBalance(balance);
        return this;
    }


}
