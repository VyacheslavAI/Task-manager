package ru.ivanov.todoproject.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class HashUtil {

    private HashUtil() {
    }

    public static String getPasswordHash(final String password) {

    }

    public static String getHashByAlgorithm(final String algorithm, final String value) throws NoSuchAlgorithmException {
        if (algorithm == null || algorithm.isEmpty()) throw new IllegalArgumentException();
        if (value == null || value.isEmpty()) throw new IllegalArgumentException();
        MessageDigest digest = digestMap.get(algorithm);
        if (digest == null) {
            digest = MessageDigest.getInstance(algorithm);
            digestMap.put(algorithm, MessageDigest.getInstance(algorithm));
        }
        final byte[] bytesValue = digest.digest(value.getBytes());
        return new String(bytesValue);
    }
}
