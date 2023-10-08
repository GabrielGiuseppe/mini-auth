package com.vr.miniauth.mapper;

import com.vr.miniauth.model.Card;
import com.vr.miniauth.request.CardRequest;
import com.vr.miniauth.response.CardResponse;

import static com.vr.miniauth.utils.CryptUtils.decryptPassword;
import static com.vr.miniauth.utils.CryptUtils.encryptPassword;
import static com.vr.miniauth.utils.MaskUtils.applyCardNumberMask;
import static com.vr.miniauth.utils.MaskUtils.removeCardNumberMask;

public abstract class CardMapper {
    public static Card mapToModel(CardRequest request) {
        return new Card()
                .withNumber(applyCardNumberMask(request.getCardNumber()))
                .withPass(encryptPassword(request.getPassword())
                );
    }

    public static CardResponse mapToResponse(Card model) {
        return new CardResponse()
                .withCardNumber(removeCardNumberMask(model.getNumber()))
                .withPassword(decryptPassword(model.getPassword()));
    }
}
