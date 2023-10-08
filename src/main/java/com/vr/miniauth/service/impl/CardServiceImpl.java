package com.vr.miniauth.service.impl;


import com.vr.miniauth.common.response.BaseReturn;
import com.vr.miniauth.exception.BaseException;
import com.vr.miniauth.mapper.CardMapper;
import com.vr.miniauth.model.Card;
import com.vr.miniauth.repository.CardRepository;
import com.vr.miniauth.request.CardRequest;
import com.vr.miniauth.response.CardResponse;
import com.vr.miniauth.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;

import static com.vr.miniauth.utils.IntegrityUtils.isNumberRightSize;
import static com.vr.miniauth.utils.MaskUtils.applyCardNumberMask;
import static com.vr.miniauth.utils.ProjectConstants.CARD_NOT_FOUND_ERROR;
import static com.vr.miniauth.utils.ProjectConstants.CARD_NUMBER_ALREADY_REGISTERED;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    CardRepository repository;

    @Override
    public BaseReturn<CardResponse> create(CardRequest request) {
        isNumberRightSize(request.getCardNumber());
        repository.getByNumber(applyCardNumberMask(request.getCardNumber())).ifPresent(model -> {
            throw new BaseException(HttpStatus.UNPROCESSABLE_ENTITY, CARD_NUMBER_ALREADY_REGISTERED);
        });
        var model = CardMapper.mapToModel(request);
        model = repository.save(model);
       final var response = CardMapper.mapToResponse(model);
       return new BaseReturn<>(response);
    }

    @Override
    public BaseReturn<BigDecimal> getBalance(String cardNumber) {
        isNumberRightSize(cardNumber);
        final var result = getCard(cardNumber);
        return new BaseReturn<>(result.getBalance());
    }

    @Override
    public Card getCard(String cardNumber) {
        var wrapper = new Object() {Card card = new Card();};
        repository.getByNumber(applyCardNumberMask(cardNumber))
                .ifPresentOrElse(model -> wrapper.card = model,
                        () -> {throw new BaseException(HttpStatus.NOT_FOUND, CARD_NOT_FOUND_ERROR);});
        return wrapper.card;
    }

    @Override
    public Card saveCard(Card card) {
        return repository.save(card);
    }
}
