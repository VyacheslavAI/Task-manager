package ru.ivanov.todoproject.security;

import ru.ivanov.todoproject.validator.Validator;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityClientManager {

    private final String passwordHashAlgorithm = "MD5";

    private Validator validator;

    public String getPasswordHash(final String password) throws NoSuchAlgorithmException {
        return getHashByAlgorithm(passwordHashAlgorithm, password);
    }

    private String getHashByAlgorithm(final String algorithm, final String value) throws NoSuchAlgorithmException {
        if (value == null || value.isEmpty()) return "";
        final MessageDigest digest = MessageDigest.getInstance(algorithm);
        final byte[] bytesValue = digest.digest(value.getBytes());
        final BigInteger bigInt = new BigInteger(1, bytesValue);
        return bigInt.toString(16);
    }

    public void setValidator(final Validator validator) {
        this.validator = validator;
    }
}
