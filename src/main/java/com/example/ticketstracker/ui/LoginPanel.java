package com.example.ticketstracker.ui;

import com.example.ticketstracker.controller.AuthController;
import com.example.ticketstracker.model.User;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import java.io.InputStream;

public class LoginPanel extends JPanel {

    private final JFrame parentFrame;
    private final JTextField usernameField;
    private final JPasswordField passwordField;
    private Image backgroundImage;

    public LoginPanel(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        setLayout(new GridBagLayout());

        // ✅ Load background image from /resources
        try (InputStream imgStream = getClass().getResourceAsStream("/background.jpg")) {
            if (imgStream != null) {
                backgroundImage = ImageIO.read(imgStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // ✅ Create translucent glass panel (Apple liquid-glass look)
        JPanel glassPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
                g2.setColor(Color.WHITE);
                g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 25, 25));
                g2.dispose();
                super.paintComponent(g);
            }
        };
        glassPanel.setOpaque(false);
        glassPanel.setLayout(new GridBagLayout());
        glassPanel.setPreferredSize(new Dimension(600, 420));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 20, 15, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        JLabel title = new JLabel("Ticket System", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 32));
        title.setForeground(new Color(40, 40, 60));
        glassPanel.add(title, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        glassPanel.add(userLabel, gbc);

        gbc.gridx = 1;
        usernameField = new JTextField(20);
        usernameField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        glassPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        glassPanel.add(passLabel, gbc);

        gbc.gridx = 1;
        passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        glassPanel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton loginButton = Style.createRoundedButton("Login", new Color(100, 140, 255), Color.WHITE);
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        loginButton.addActionListener(e -> handleLogin());
        glassPanel.add(loginButton, gbc);

        gbc.gridy++;
        JButton requestButton = Style.createRoundedButton("Request Account", new Color(220, 220, 220), Color.BLACK);
        requestButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        requestButton.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Request form will be added soon."));
        glassPanel.add(requestButton, gbc);

        add(glassPanel);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            g2d.dispose();
        } else {
            // fallback gradient if no image
            Graphics2D g2 = (Graphics2D) g;
            GradientPaint gp = new GradientPaint(
                    0, 0, new Color(200, 220, 255),
                    getWidth(), getHeight(), new Color(170, 190, 255)
            );
            g2.setPaint(gp);
            g2.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both username and password.");
            return;
        }

        User loggedUser = AuthController.login(username, password);
        if (loggedUser != null) {
            JOptionPane.showMessageDialog(this,
                    "Welcome, " + loggedUser.getUsername() + " (" + loggedUser.getRole() + ")!",
                    "Login Successful", JOptionPane.INFORMATION_MESSAGE);

            parentFrame.getContentPane().removeAll();
            parentFrame.add(new DashboardPanel(parentFrame, loggedUser));
            parentFrame.revalidate();
            parentFrame.repaint();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Invalid username or password.",
                    "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }
}
