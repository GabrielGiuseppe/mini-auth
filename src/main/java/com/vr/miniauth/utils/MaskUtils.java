package com.vr.miniauth.utils;

import static com.vr.miniauth.utils.ProjectConstants.CARD_NUMBER_MASK;
import static com.vr.miniauth.utils.ProjectConstants.CARD_NUMBER_REMOVABLE_CHARACTER;
import static com.vr.miniauth.utils.ProjectConstants.CARD_NUMBER_REPLACEMENT_CHARACTER;

public abstract class MaskUtils {



    public static String applyCardNumberMask(String cardNumber){
        return String.format(CARD_NUMBER_MASK,
                cardNumber.substring(0,4),
                cardNumber.substring(4,8),
                cardNumber.substring(8,12),
                cardNumber.substring(12,16)
        );
    }

    public static String removeCardNumberMask(String cardNumber) {
        return cardNumber.replace(CARD_NUMBER_REMOVABLE_CHARACTER, CARD_NUMBER_REPLACEMENT_CHARACTER);
    }
}
