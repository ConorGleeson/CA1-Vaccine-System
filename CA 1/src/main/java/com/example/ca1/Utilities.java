package com.example.ca1;

public class Utilities {
    public static boolean max10Chars(String string) {
        return string.length() <= 10;
    }

    public static boolean validPPS(String text) {
        if (text.length() != 9) {
            return false;
        } else {
            return text.substring(0, 7).matches("[0-9]+") && text.substring(7, 9).matches("[A-Za-z]+");
        }
    }

    public static boolean validEmail(String text){
        return text.matches("[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$");
    }

    public static boolean max30Chars(String string) {
        return string.length() <= 30;
    }

    public static boolean validIntNonNegative(int number) {
        return number >= 0;
    }

    public static boolean validDoubleNonNegative(double number) {
        return number >= 0.0D;
    }

    public static boolean validIntRange(int min, int max, int i) {
        return i >= min && i <= max;
    }

    public static boolean validLecturerLevel(int i) {
        return i >= 1 && i <= 3;
    }

    public static double getSalaryForLecturerLevel(int i) {
        return i >= 1 && i <= 3 ? (double) (i * 1000) : -1.0D;
    }

    public static boolean validAdminLevel(int i) {
        return i >= 1 && i <= 7;
    }

    public static double getSalaryForAdminGrade(int i) {
        return i >= 1 && i <= 7 ? (double) (i * 700) : -1.0D;
    }

    public static boolean validManagerLevel(int i) {
        return i >= 1 && i <= 7;
    }
}
