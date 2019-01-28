package ru.ivanov.todoproject.util;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public final class ConsoleHelper {

    private ConsoleHelper() {
    }

    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public static void printWelcome() {
        System.out.println("Welcome to Task Manager Application!");
        System.out.println("Enter \"help\" show list of available commands");
    }

    public static void print(final String message) {
        System.out.println(message);
    }

    public static String readString() {
        String string = null;
        while (string == null) {
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

    public static int readInt(final int from, final int to) {
        int number = Integer.parseInt(readString());
        while (number < from || number > to) {
            print(String.format("Value must be more then %d and less then %d", from - 1, to + 1));
            number = Integer.parseInt(readString());
        }
        return number;
    }

    public static void printDelimiter() {
        System.out.println("=================================================");
    }

    public static Date readDate() {
        Date date = null;
        while (date == null) {
            try {
                final String stringDate = readString();
                date = simpleDateFormat.parse(stringDate);
            } catch (ParseException e) {
                print("Wrong date format. Please enter the date in the following format 15/04/1990:");
            }
        }
        return date;
    }

    public static String formatDate(final GregorianCalendar date) {
        return simpleDateFormat.format(date);
    }
}

