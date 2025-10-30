package com.example.ticketstracker;

import com.example.ticketstracker.ui.LoginPanel;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Ticket Tracking System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1100, 700);
            frame.setLocationRelativeTo(null);
            frame.setLayout(new BorderLayout());
            frame.setResizable(true);

            // Start with login
            frame.add(new LoginPanel(frame));

            frame.setVisible(true);
        });
    }
}
