package ru.ivanov.todoproject.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import ru.ivanov.todoproject.entity.Session;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static ru.ivanov.todoproject.util.ValidationUtil.isSessionValid;

public final class HashUtil {

    private static final String SALT = "184S8w0";

    private HashUtil() {
    }

    public static String sign(final Object object) {
        if (object == null) return null;
        try {
            final ObjectWriter jsonMapper = new ObjectMapper().writer().withDefaultPrettyPrinter();
            final String algorithmName = "MD5";
            String signature = jsonMapper.writeValueAsString(object);
            for (int i = 0; i < 1466; i++) {
                signature = SALT + getHashByAlgorithm(algorithmName, signature) + SALT;
            }
            return signature;
        } catch (JsonProcessingException | NoSuchAlgorithmException e) {
            return null;
        }
    }

    public static boolean isSessionVerified(final Session session) {
        if (!isSessionValid(session)) return false;
        final Session clone = new Session();
        clone.setTimestamp(session.getTimestamp());
        clone.setUserId(session.getUserId());
        final String expectedSignature = session.getSignature();
        final String currentSignature = sign(clone);
        return currentSignature.equals(expectedSignature);
    }

    public static String getPasswordHash(final String password) {
        try {
            return getHashByAlgorithm("MD5", password);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public static String getHashByAlgorithm(final String algorithm, final String value) throws NoSuchAlgorithmException {
        if (value == null || value.isEmpty()) return "";
        final MessageDigest digest = MessageDigest.getInstance(algorithm);
        final byte[] bytesValue = digest.digest(value.getBytes());
        return new String(bytesValue);
    }
}