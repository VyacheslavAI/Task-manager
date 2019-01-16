package ru.ivanov.todoproject.util;

import ru.ivanov.todoproject.controller.Controller;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SerializationUtil {

    private SerializationUtil() {
    }

    public static void serialize(Controller controller) throws IOException {
        try (final ObjectOutput objectOutput = new ObjectOutputStream(Files.newOutputStream(Paths.get("data.bin")))) {
            Domain domain = Domain.createDomain(controller);
            objectOutput.writeObject(domain);
        }
    }

    public static Map<String, List> deserialize() throws IOException, ClassNotFoundException {
        try (final ObjectInput objectInput = new ObjectInputStream(Files.newInputStream(Paths.get("data.bin")))) {
            final Domain domain = (Domain) objectInput.readObject();
            Map<String, List> domainData = new HashMap<>();
            domainData.put("Project", domain.getProjects());
            domainData.put("Task", domain.getTasks());
            return domainData;
        }
    }
}
