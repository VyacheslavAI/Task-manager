package ru.ivanov.todoproject.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class ConsoleHelper {

    private ConsoleHelper() {
    }

    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public static void printMessage(final String message) {
        System.out.println(message);
    }

    public static String readString() {
        String string = null;
        while (string == null || string.isEmpty()) {
            try {
                string = reader.readLine();
            } catch (IOException e) {
                System.out.println("An exception has occurred. Please try again");
            }
        }
        return string;
    }

    public static int readInt() {
        return Integer.parseInt(readString());
    }

    public static int readInt(final int from, final int to) {
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

    public static String formatDate(final Date date) {
        return simpleDateFormat.format(date);
    }

    public static Date parseDate(String date) {
        try {
            return simpleDateFormat.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

}
