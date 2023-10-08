package com.vr.miniauth.builder;

import com.vr.miniauth.request.TransactionRequest;

import java.math.BigDecimal;

public class TestTransactionRequestBuilder {

    private TransactionRequest request;

    public TestTransactionRequestBuilder(){
        this.request = new TransactionRequest();
    }

     public TestTransactionRequestBuilder withCardNumber(String cardNumber){
        this.request.setCardNumber(cardNumber);
         return this;
     }

    public TestTransactionRequestBuilder withPassword(String password){
        this.request.setPassword(password);
        return this;
    }

    public TestTransactionRequestBuilder withValue(BigDecimal value){
        this.request.setValue(value);
        return this;
    }

    public TransactionRequest build() {
        request.setCardNumber(request.getCardNumber() == null ? RandomUtils.randomCardNumber() : request.getCardNumber());
        request.setPassword(request.getPassword() == null ? RandomUtils.randomCardPassword() : request.getPassword());
        request.setValue(request.getValue() == null ? RandomUtils.randomBigDecimalValue() : request.getValue());
        return request;
    }
}
