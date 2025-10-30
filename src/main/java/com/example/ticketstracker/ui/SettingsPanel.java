package com.example.ticketstracker.ui;

import com.example.ticketstracker.controller.ThemeController;
import com.example.ticketstracker.model.SoundManager;

import javax.swing.*;
import java.awt.*;

public class SettingsPanel extends JPanel {

    private JCheckBox soundCheck;
    private JCheckBox darkModeCheck;

    public SettingsPanel() {
        setLayout(new GridBagLayout());
        setBackground(ThemeController.getBackground());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel title = new JLabel("Settings");
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        title.setForeground(ThemeController.getTextColor());
        add(title, gbc);

        gbc.gridy++;
        soundCheck = new JCheckBox("Enable Sounds");
        soundCheck.setSelected(SoundManager.isEnabled());
        soundCheck.setOpaque(false);
        soundCheck.setForeground(ThemeController.getTextColor());
        soundCheck.addActionListener(e -> SoundManager.setEnabled(soundCheck.isSelected()));
        add(soundCheck, gbc);

        gbc.gridy++;
        darkModeCheck = new JCheckBox("Dark Mode");
        darkModeCheck.setSelected(ThemeController.isDarkMode());
        darkModeCheck.setOpaque(false);
        darkModeCheck.setForeground(ThemeController.getTextColor());
        darkModeCheck.addActionListener(e -> {
            ThemeController.toggleDarkMode(darkModeCheck.isSelected());
            SwingUtilities.getWindowAncestor(this).repaint();
        });
        add(darkModeCheck, gbc);
    }
}
