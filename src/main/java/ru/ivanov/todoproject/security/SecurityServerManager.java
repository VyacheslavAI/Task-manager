package ru.ivanov.todoproject.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.entity.Session;
import ru.ivanov.todoproject.validator.Validator;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SecurityServerManager {

    private final String salt = "184S8w076031";

    private final String passwordHashAlgorithm = "MD5";

    private final String signAlgorithm = "MD5";

    private Validator validator;

    public String sign(final Object object) {
        if (object == null) return "";
        try {
            final ObjectWriter jsonMapper = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String signature = jsonMapper.writeValueAsString(object);
            for (int i = 0; i < 1466; i++) {
                signature = salt + getHashByAlgorithm(signAlgorithm, signature) + salt;
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
        clone.setTimestamp(session.getTimestamp());
        clone.setUserId(session.getUserId());
        final String expectedSignature = sign(clone);
        final String transferredSignature = session.getSignature();
        return expectedSignature.equals(transferredSignature);
    }

    private String getHashByAlgorithm(final String algorithm, final String value) throws NoSuchAlgorithmException {
        if (value == null || value.isEmpty()) return "";
        final MessageDigest digest = MessageDigest.getInstance(algorithm);
        final byte[] bytesValue = digest.digest(value.getBytes());
        return new String(bytesValue);
    }

    private List<Project> filterProjectsByUserId(final List<Project> projects, final String userId) {
        if (projects == null || projects.isEmpty()) return Collections.emptyList();
        if (userId == null || userId.isEmpty()) return Collections.emptyList();
        final List<Project> result = new ArrayList<>();
        for (final Project project : projects) {
            if (project.getUserId().equals(userId)) {
                result.add(project);
            }
        }
        return result;
    }

    public void setValidator(final Validator validator) {
        this.validator = validator;
    }
}
