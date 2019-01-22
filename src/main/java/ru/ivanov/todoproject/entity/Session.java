package ru.ivanov.todoproject.entity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public final class Session {

    private String signature;

    private static final String secretKey = "secret";

    public Session() {
    }

    private Session(final String signature) {
        this.signature = signature;
    }

    public static Session createSession() {
        try {
            String signature = UUID.randomUUID().toString();
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] hashResult = null;
            for (int i = 0; i < 14; i++) {
                hashResult = messageDigest.digest(signature.getBytes());
                signature = secretKey + new String(hashResult) + secretKey;
            }
            return new Session(new String(hashResult));
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
