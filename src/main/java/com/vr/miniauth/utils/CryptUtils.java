package com.vr.miniauth.utils;

import org.springframework.security.crypto.encrypt.Encryptors;

public abstract class CryptUtils {
    private static final String ENCRYPT_SALT = "88498e18e36d65a0";
    private static final String KEY_PASS = "vr.mini-auth.keypass";

    public static String encryptPassword(String password) {
        final var encryptor = Encryptors.text(KEY_PASS, ENCRYPT_SALT);
        return encryptor.encrypt(password);
    }

    public static String decryptPassword(String password) {
        final var decryptor = Encryptors.text(KEY_PASS, ENCRYPT_SALT);
        return decryptor.decrypt(password);
    }
}
