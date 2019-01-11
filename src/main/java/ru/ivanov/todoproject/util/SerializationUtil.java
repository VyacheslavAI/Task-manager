package ru.ivanov.todoproject.util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class SerializationUtil {

    private SerializationUtil() {
    }

    public static class Domain implements Serializable {

        private List<List> data;

        public Domain(List<List> data) {
            this.data = data;
        }

        public List<List> getData() {
            return data;
        }

        public void setData(List<List> data) {
            this.data = data;
        }
    }

    public static void serialize(final Domain domain) throws IOException {
        try (final ObjectOutput objectOutput = new ObjectOutputStream(Files.newOutputStream(Paths.get("data.bin")))) {
            objectOutput.writeObject(domain);
        }
    }

    public static List<List> deserialize() throws IOException, ClassNotFoundException {
        try (final ObjectInput objectInput = new ObjectInputStream(Files.newInputStream(Paths.get("data.bin")))) {
            final Domain domain = (Domain) objectInput.readObject();
            return domain.getData();
        }
    }
}
