package ru.ivanov.todoproject.controller;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SerializationUtils {

    private SerializationUtils() {
    }

    public static class Domen implements Serializable {

        List<List> data = new ArrayList<>();

        public Domen(List<List> data) {
            this.data = data;
        }

        public List<List> getData() {
            return data;
        }

        public void setData(List<List> data) {
            this.data = data;
        }
    }

    public static void serialize(Domen domen) throws IOException {
        try (ObjectOutput objectOutput = new ObjectOutputStream(Files.newOutputStream(Paths.get("data.bin")))) {
            objectOutput.writeObject(domen);
        }
    }

    public static List<List> deserialize() throws IOException, ClassNotFoundException {
        try (ObjectInput objectInput = new ObjectInputStream(Files.newInputStream(Paths.get("data.bin")))) {
            Domen domen = (Domen) objectInput.readObject();
            return domen.getData();
        }
    }
}
