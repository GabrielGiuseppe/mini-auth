package com.vr.miniauth.utils;


import com.vr.miniauth.exception.BaseException;
import com.vr.miniauth.model.Card;
import org.springframework.http.HttpStatus;

import static com.vr.miniauth.utils.ProjectConstants.CARD_NUMBER_NOT_CORRECT_SIZE;
import static com.vr.miniauth.utils.ProjectConstants.CARD_NUMBER_SIZE;
import static com.vr.miniauth.utils.ProjectConstants.PASSWORD_NOT_CORRECT_SIZE;
import static com.vr.miniauth.utils.ProjectConstants.PASSWORD_NOT_CORRECT_TYPE;
import static com.vr.miniauth.utils.ProjectConstants.PASSWORD_REGEX;
import static com.vr.miniauth.utils.ProjectConstants.PASSWORD_SIZE;

public abstract class IntegrityUtils {

    public static boolean isNumberRightSize(String number){
        return number.length() == CARD_NUMBER_SIZE || throwError(HttpStatus.BAD_REQUEST, CARD_NUMBER_NOT_CORRECT_SIZE);
    }

    public static boolean isPasswordRightSize(String password){
        return password.length() == PASSWORD_SIZE || throwError(HttpStatus.BAD_REQUEST, PASSWORD_NOT_CORRECT_SIZE);
    }

    public static boolean isPasswordOnlyNumbers(String password){
        return password.matches(PASSWORD_REGEX) || throwError(HttpStatus.BAD_REQUEST, PASSWORD_NOT_CORRECT_TYPE);
    }

    private static boolean throwError(HttpStatus status, String message) {
        throw new BaseException(status, message);
    }

    public static Card throwCardException(HttpStatus status, String message) {
        throw new BaseException(status, message);
    }

    public static void isPasswordValid(String password) {
        isPasswordRightSize(password);
        isPasswordOnlyNumbers(password);
    }
}
