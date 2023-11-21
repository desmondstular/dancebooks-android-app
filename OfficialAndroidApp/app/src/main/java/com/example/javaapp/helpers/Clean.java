package com.example.javaapp.helpers;

import static java.lang.Character.isAlphabetic;
import static java.lang.Character.isDigit;

public class Clean {
    /* String cleanEmail(String input)
        Accepts a String as input and trims whitespaces. Returns
        the cleaned string.
     */
    public static String cleanEmail(String input) {
        StringBuilder stringBuilder = new StringBuilder();
        input = input.toUpperCase();
        char[] chars = input.toCharArray();
        for (char aChar : chars) {
            if (aChar != ' ') {
                stringBuilder.append(aChar);
            }
        }
        return stringBuilder.toString();
    }

    /* String cleanName(String input)
    Accepts a String as input and cleans out all characters
    that are not alphabetic or a "-" character. Returns
    the cleaned string.
     */
    public static String cleanName(String input) {
        StringBuilder stringBuilder = new StringBuilder();
        input = input.toUpperCase();
        char[] chars = input.toCharArray();
        for (char aChar : chars) {
            if (isAlphabetic(aChar) || aChar == '-') {
                stringBuilder.append(aChar);
            }
        }
        return stringBuilder.toString();
    }

    /* String cleanPhone(String input)
    Accepts a String representing user input and cleans out
    all characters that are numeric or a "-" character. Returns
    the cleaned string.
     */
    public static String cleanPhone(String input) {
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = input.toCharArray();
        for (char aChar : chars) {
            if (isDigit(aChar)) {
                stringBuilder.append(aChar);
            }
        }
        return stringBuilder.toString();
    }

    /* String clearIntInput(String input)
        Accepts a String as input and cleans out all characters
        except for numbers. Returns the cleaned String.
     */
    public static String cleanIntInput(String input) {
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = input.toCharArray();
        for (char aChar : chars) {
            if (isDigit(aChar)) {
                stringBuilder.append(aChar);
            }
        }
        return stringBuilder.toString();
    }
}
