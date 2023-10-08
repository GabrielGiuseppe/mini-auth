package com.vr.miniauth.service.impl;

import com.vr.miniauth.common.response.BaseReturn;
import com.vr.miniauth.model.Card;
import com.vr.miniauth.request.TransactionRequest;
import com.vr.miniauth.service.CardService;
import com.vr.miniauth.service.TransactionService;
import com.vr.miniauth.utils.CryptUtils;
import com.vr.miniauth.utils.IntegrityUtils;
import com.vr.miniauth.utils.ProjectConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;

import static com.vr.miniauth.utils.ProjectConstants.INSUFFICIENT_BALANCE;
import static com.vr.miniauth.utils.ProjectConstants.ZERO_BALANCE;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    CardService cardService;

    @Override
    @Transactional
    public BaseReturn<String> approveTransaction(TransactionRequest request) {
        IntegrityUtils.isNumberRightSize(request.getCardNumber());
        var cardModel = cardService.getCard(request.getCardNumber());
        cardModel = verifyPassword(cardModel, request.getPassword());
        final var updatedModel = verifyBalance(cardModel, request.getValue());
        cardService.saveCard(updatedModel);
        return new BaseReturn<>(ProjectConstants.OK_TRANSACTIUON);
    }

    private Card verifyPassword(Card cardModel, String password) {
        return CryptUtils.decryptPassword(cardModel.getPassword()).equals(password)
                ? cardModel
                : IntegrityUtils.throwCardException(HttpStatus.UNPROCESSABLE_ENTITY, ProjectConstants.PASSWORD_NOT_MATCH);
    }

    private Card verifyBalance(Card cardModel, BigDecimal value) {
        final var resultBalance = cardModel.getBalance().subtract(value);
        return resultBalance.compareTo(BigDecimal.ZERO) >= ZERO_BALANCE
                ? cardModel.withBalance(resultBalance)
                : IntegrityUtils.throwCardException(HttpStatus.UNPROCESSABLE_ENTITY, INSUFFICIENT_BALANCE);
    }
}
