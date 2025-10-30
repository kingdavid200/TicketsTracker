package com.example.ticketstracker.ui;

import com.example.ticketstracker.model.User;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class DashboardPanel extends JPanel {
    private final JFrame parentFrame;
    private final User currentUser;
    private Sidebar sidebar;
    private JPanel contentArea;
    private Image backgroundImage;

    public DashboardPanel(JFrame frame, User user) {
        this.parentFrame = frame;
        this.currentUser = user;

        // ✅ Load background image (same as LoginPanel)
        try (InputStream imgStream = getClass().getResourceAsStream("/background.jpg")) {
            if (imgStream != null) {
                backgroundImage = ImageIO.read(imgStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        setLayout(new BorderLayout());
        setOpaque(false);

        // ✅ Sidebar setup
        sidebar = new Sidebar(this, user);
        add(sidebar, BorderLayout.WEST);

        // ✅ Header with glass effect
        JPanel header = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                GlassUtils.drawGlassPanel(g, this, 0);
                super.paintComponent(g);
            }
        };
        header.setPreferredSize(new Dimension(0, 50));
        header.setOpaque(false);
        header.setLayout(new BorderLayout(10, 0));

        JLabel title = new JLabel("Ticket System", SwingConstants.LEFT);
        title.setFont(Style.TITLE_FONT.deriveFont(Font.PLAIN, 20f));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        header.add(title, BorderLayout.WEST);

        JLabel userInfo = new JLabel("Logged in as: " + user.getUsername() + " (" + user.getRole() + ")", SwingConstants.RIGHT);
        userInfo.setForeground(Color.WHITE);
        userInfo.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
        header.add(userInfo, BorderLayout.EAST);

        add(header, BorderLayout.NORTH);

        // ✅ Central content area
        contentArea = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                // ✅ Keep same background for internal panels
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                } else {
                    super.paintComponent(g);
                }
            }
        };
        contentArea.setOpaque(false);
        add(contentArea, BorderLayout.CENTER);

        // ✅ Show default panel depending on role
        switch (user.getRole()) {
            case ADMIN -> setContent(new AdminDashboardPanel());
            case TECHNICIAN -> setContent(new TechDashboardPanel(user));
            case HEAD_IT -> setContent(new HeadITPanel());
            case USER -> setContent(new UserTicketsPanel(user));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // ✅ Full-screen background for dashboard
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        } else {
            // fallback gradient
            Graphics2D g2 = (Graphics2D) g;
            GradientPaint gp = new GradientPaint(
                    0, 0, new Color(200, 220, 255),
                    getWidth(), getHeight(), new Color(170, 190, 255)
            );
            g2.setPaint(gp);
            g2.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    /**
     * ✅ Ensures only one panel is visible at a time.
     */
    public void setContent(JPanel newPanel) {
        contentArea.removeAll();
        contentArea.add(newPanel, BorderLayout.CENTER);
        contentArea.revalidate();
        contentArea.repaint();
    }

    public JFrame getParentFrame() {
        return parentFrame;
    }
}
