package com.vr.miniauth.builder;

import com.vr.miniauth.utils.CryptUtils;
import com.vr.miniauth.utils.ProjectConstants;

import java.math.BigDecimal;
import java.util.Random;

public abstract class RandomUtils {

    private final static Random rdm = new Random();

    public static BigDecimal randomBalance() {
        return new BigDecimal(rdm.nextDouble());
    }

    public static String randomCardPassword() {
        return Integer.toString(rdm.nextInt(9999));
    }

    public static String randomCardEncryptedPassword(){
        return CryptUtils.encryptPassword(Integer.toString(rdm.nextInt(9999)));
    }
    public static String randomCardNumber() {
        return String.format(ProjectConstants.CARD_NUMBER_NO_MASK,
                rdm.nextInt(9999),
                rdm.nextInt(9999),
                rdm.nextInt(9999),
                rdm.nextInt(9999));
    }

    public static BigDecimal randomBigDecimalValue() {
        return new BigDecimal(rdm.nextInt(5000));
    }

    public static Long randomId() {
        return (long) rdm.nextInt(50);
    }
}
