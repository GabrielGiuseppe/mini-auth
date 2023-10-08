package com.vr.miniauth.utils;


import com.vr.miniauth.exception.BaseException;
import com.vr.miniauth.model.Card;
import org.springframework.http.HttpStatus;

public abstract class IntegrityUtils {

    private static final int CARD_NUMBER_SIZE = 16;

    public static boolean isNumberRightSize(String number){
        return number.length() == CARD_NUMBER_SIZE || throwError(HttpStatus.BAD_REQUEST, ProjectConstants.CARD_NUMBER_NOT_CORRECT_SIZE);
    }

    private static boolean throwError(HttpStatus status, String message) {
        throw new BaseException(status, message);
    }

    public static Card throwCardException(HttpStatus status, String message) {
        throw new BaseException(status, message);
    }
}
