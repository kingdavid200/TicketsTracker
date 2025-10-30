package com.example.ticketstracker.ui;

import javax.swing.*;
import java.awt.*;

public class Style {

    // 🎨 Theme colors
    public static Color GRADIENT_TOP = new Color(10, 61, 145);
    public static Color GRADIENT_BOTTOM = new Color(78, 141, 247);
    public static Color GLASS_WHITE = new Color(255, 255, 255, 190);
    public static Color GLASS_DARK = new Color(30, 30, 30, 180);
    public static Color TEXT_DARK = new Color(40, 40, 40);
    public static Color TEXT_LIGHT = new Color(240, 240, 240);

    // 🔠 Fonts
    public static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 28);
    public static final Font NORMAL_FONT = new Font("Segoe UI", Font.PLAIN, 16);

    // 🟦 Button styling helper
    public static JButton createRoundedButton(String text, Color background, Color foreground) {
        JButton button = new JButton(text);
        button.setBackground(background);
        button.setForeground(foreground);
        button.setFocusPainted(false);
        button.setFont(NORMAL_FONT);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setOpaque(false);

        // Rounded edges and hover effect
        button.setContentAreaFilled(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(background.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(background);
            }
        });
        return button;
    }
}
