package com.vr.miniauth.utils;

public abstract class ProjectConstants {
    public static final String CARD_NOT_FOUND = "Numero de cartão informado não foi encontrado";
    public static final String CARD_NUMBER_ALREADY_REGISTERED = "Numero de cartão informado ja foi cadastrado";
    public static final String CARD_NUMBER_NOT_CORRECT_SIZE = "Numero de cartão informado precisa ter exatos 16 digitos";
    public static final String PASSWORD_NOT_CORRECT_SIZE = "Senha informada precisa ter exatos 4 digitos";
    public static final String PASSWORD_NOT_CORRECT_TYPE = "Senha informada precisa ser numero";

    public static final String PASSWORD_REGEX = "[0-9]+";

    public static final int CARD_NUMBER_SIZE = 16;
    public static final int PASSWORD_SIZE = 4;

    public static final String PASSWORD_NOT_MATCH = "Senha incorreta";
    public static final String INSUFFICIENT_BALANCE = "Saldo insuficiente";
    public static final String OK_TRANSACTIUON = "OK";

    public static final int ZERO_BALANCE = 0;

    public static final String CARD_NUMBER_MASK = "%s.%s.%s.%s";
    public static final String CARD_NUMBER_NO_MASK = "%s%s%s%s";
    public static final String CARD_NUMBER_REMOVABLE_CHARACTER = ".";
    public static final String CARD_NUMBER_REPLACEMENT_CHARACTER = "";


}
