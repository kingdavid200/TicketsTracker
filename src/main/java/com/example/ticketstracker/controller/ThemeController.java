package com.example.ticketstracker.controller;

import java.awt.*;

public class ThemeController {
    private static boolean darkMode = false;

    public static void toggleDarkMode(boolean enabled) {
        darkMode = enabled;
    }

    public static boolean isDarkMode() {
        return darkMode;
    }

    public static Color getBackground() {
        return darkMode ? new Color(25, 25, 25, 180) : new Color(255, 255, 255, 200);
    }

    public static Color getTextColor() {
        return darkMode ? Color.WHITE : Color.BLACK;
    }
}
