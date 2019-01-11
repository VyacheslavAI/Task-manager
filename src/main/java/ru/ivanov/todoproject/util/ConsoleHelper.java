package ru.ivanov.todoproject.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {

    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static String readString() {
        String string = null;
        while (string == null || string.isEmpty()) {
            try {
                string = reader.readLine();
            } catch (IOException e) {
                System.out.println("An error has occurred. Please try again");
            }
        }
        return string;
    }

    public static int readInt() {
        return Integer.parseInt(readString());
    }

    public static int readInt(int from, int to) {
        int number = Integer.parseInt(readString());

        while (number < from || number > to) {
            ConsoleHelper.printMessage("Invalid input");
            number = Integer.parseInt(readString());
        }

        return number;
    }

    public static void printDelimiter() {
        System.out.println("=================================================");
    }
}
