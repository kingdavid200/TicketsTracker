package com.example.ticketstracker.model;

public class SoundManager {
    private static boolean enabled = true;

    public static boolean isEnabled() {
        return enabled;
    }

    public static void setEnabled(boolean e) {
        enabled = e;
    }
}
