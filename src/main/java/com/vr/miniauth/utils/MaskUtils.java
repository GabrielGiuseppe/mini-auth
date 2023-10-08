package com.vr.miniauth.utils;

public abstract class MaskUtils {

    private static final String CARD_NUMBER_MASK = "%s.%s.%s.%s";
    private static final String CARD_NUMBER_REMOVABLE_CHARACTER = ".";
    private static final String CARD_NUMBER_REPLACEMENT_CHARACTER = ".";

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
