package ru.ivanov.todoproject.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import ru.ivanov.todoproject.entity.Session;
import ru.ivanov.todoproject.exception.RequestNotAuthenticatedException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import static ru.ivanov.todoproject.util.ValidationUtil.isSessionValid;

public final class HashUtil {

    private static final String SALT = "184S8w0";

    private static Map<String, MessageDigest> digestMap = new HashMap<>();

    private HashUtil() {
    }

    public static String sign(final Object object) throws JsonProcessingException, NoSuchAlgorithmException {
        if (object == null) return null;
        final ObjectWriter jsonMapper = new ObjectMapper().writer().withDefaultPrettyPrinter();
        final String algorithmName = "MD5";
        String signature = jsonMapper.writeValueAsString(object);
        for (int i = 0; i < 1466; i++) {
            signature = SALT + getHashByAlgorithm(algorithmName, signature) + SALT;
        }
        return signature;
    }

    public static boolean isSessionVerified(final Session session) throws JsonProcessingException, NoSuchAlgorithmException {
        if (!isSessionValid(session)) return false;
        final Session clone = new Session();
        clone.setTimestamp(session.getTimestamp());
        clone.setUserId(session.getUserId());
        final String currentSignature = session.getSignature();
        final String expectedSignature = sign(clone);
        return currentSignature.equals(expectedSignature);
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
