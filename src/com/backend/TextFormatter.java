package com.backend;

public class TextFormatter {
    public static String format(String text){
        text.replace("*", "<br>");
        return "<html>" + text + "</html>";
    }
}
