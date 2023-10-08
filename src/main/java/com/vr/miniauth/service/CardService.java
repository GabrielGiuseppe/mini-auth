package com.vr.miniauth.service;

import com.vr.miniauth.common.response.BaseReturn;
import com.vr.miniauth.model.Card;
import com.vr.miniauth.request.CardRequest;
import com.vr.miniauth.response.CardResponse;

import java.math.BigDecimal;

public interface CardService {
    BaseReturn<CardResponse> create(CardRequest request);

    BaseReturn<BigDecimal> getBalance(String cardNumber);

    Card getCard(String cardNumber);

    Card saveCard(Card card);
}
