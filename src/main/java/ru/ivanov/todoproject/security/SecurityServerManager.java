package ru.ivanov.todoproject.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import ru.ivanov.todoproject.entity.Session;
import ru.ivanov.todoproject.validator.Validator;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityServerManager {

    private final String salt = "184S8w076031";

    private final String passwordHashAlgorithm = "MD5";

    private final String signHashAlgorithm = "MD5";

    private final int numberOfIterations = 1466;

    private Validator validator;

    public String sign(final Object object) {
        if (object == null) return "";
        try {
            final ObjectWriter jsonMapper = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String signature = jsonMapper.writeValueAsString(object);
            for (int i = 0; i < numberOfIterations; i++) {
                signature = salt + signature + salt;
                signature = getHashByAlgorithm(signHashAlgorithm, signature);
            }
            return signature;
        } catch (JsonProcessingException | NoSuchAlgorithmException e) {
            return "";
        }
    }

    public String getPasswordHash(final String password) throws NoSuchAlgorithmException {
        return getHashByAlgorithm(passwordHashAlgorithm, password);
    }

    public boolean isSessionVerified(final Session session) {
        if (!validator.isSessionValid(session)) return false;
        final Session clone = new Session();
        clone.setId(session.getId());
        clone.setUserId(session.getUserId());
        clone.setTimestamp(session.getTimestamp());
        clone.setCreated(session.getCreated());
        final String expectedSignature = sign(clone);
        final String transferredSignature = session.getSignature();
        return expectedSignature.equals(transferredSignature);
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
