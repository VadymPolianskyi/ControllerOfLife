package com.polyanski.vadym.ua.time;

/**
 * Created by vadym on 28.09.16.
 */
public class StringUtilities {

    public static String pad(int fieldWidth, char padChar, String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = s.length(); i < fieldWidth; i++) {
            sb.append(padChar);
        }
        sb.append(s);

        return sb.toString();
    }
}