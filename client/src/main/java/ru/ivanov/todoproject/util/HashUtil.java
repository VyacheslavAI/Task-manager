package ru.ivanov.todoproject.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public final class HashUtil {

    private static Map<String, MessageDigest> digestMap = new HashMap<>();

    private HashUtil() {
    }

    public static String getPasswordHash(final String password) {
        try {
            return getHashByAlgorithm("MD5", password);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
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
