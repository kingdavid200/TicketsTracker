package com.example.ticketstracker.ui;

import javax.swing.*;
import java.awt.*;

public class HeadITPanel extends JPanel {
    public HeadITPanel() {
        setLayout(new BorderLayout());
        setOpaque(false);
        JLabel msg = new JLabel("Head IT - Escalations", SwingConstants.CENTER);
        msg.setFont(Style.NORMAL_FONT.deriveFont(Font.BOLD, 18f));
        msg.setForeground(Color.WHITE);
        add(msg, BorderLayout.CENTER);
    }
}
