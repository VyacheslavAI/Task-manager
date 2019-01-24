package ru.ivanov.todoproject.util;


import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;

import javax.xml.datatype.XMLGregorianCalendar;
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
        final String welcomeString = "Welcome to Task Manager Application! \r\n" +
                "Enter \"help\" show list of available commands";
        printMessage(welcomeString);
    }

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

    public static String formatDate(final XMLGregorianCalendar calendar) {
        return simpleDateFormat.format(calendar);
    }

    public static Date parseDate(final String date) {
        try {
            return simpleDateFormat.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    public static XMLGregorianCalendar convertDateToXMLCalendar(final Date date) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setGregorianChange(date);
        return new XMLGregorianCalendarImpl(gregorianCalendar);
    }

    public static Date readDate() {
        Date date = null;
        while (date == null) {
            try {
                final String stringDate = readString();
                date = simpleDateFormat.parse(stringDate);
            } catch (ParseException e) {
                ConsoleHelper.printMessage("Wrong date format. Please enter the date in the following format 15/04/1990:");
            }
        }
        return date;
    }
}

