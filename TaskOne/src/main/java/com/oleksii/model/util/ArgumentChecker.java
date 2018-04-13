package com.oleksii.model.util;

public class ArgumentChecker {

    public static void checkArgs(boolean pred, String message) {
        if (!pred) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void checkArgs(boolean pred) {
        checkArgs(pred, "");
    }
}
