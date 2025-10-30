package com.example.ticketstracker.ui;

import com.example.ticketstracker.model.Role;
import com.example.ticketstracker.model.User;

import javax.swing.*;
import java.awt.*;

public class Sidebar extends JPanel {

    private boolean expanded = true;
    private final int EXPANDED_WIDTH = 200;
    private final int COLLAPSED_WIDTH = 60;
    private final DashboardPanel dashboard;
    private final User currentUser;

    public Sidebar(DashboardPanel dashboard, User user) {
        this.dashboard = dashboard;
        this.currentUser = user;

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(EXPANDED_WIDTH, 0));
        setOpaque(false);

        JPanel menuPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                GlassUtils.drawGlassPanel(g, this, 0);
                super.paintComponent(g);
            }
        };
        menuPanel.setOpaque(false);
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

        JButton toggleBtn = new JButton("☰");
        toggleBtn.setFont(new Font("Segoe UI", Font.BOLD, 20));
        toggleBtn.setFocusPainted(false);
        toggleBtn.setContentAreaFilled(false);
        toggleBtn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        toggleBtn.setForeground(Color.WHITE);
        toggleBtn.addActionListener(e -> toggleSidebar());
        menuPanel.add(toggleBtn);

        Role role = user.getRole();

        // ✅ Fix — only update central content, don’t create new dashboards
        addNavButton(menuPanel, "Dashboard", e -> dashboard.setContent(new UserTicketsPanel(user)));

        if (role == Role.ADMIN) {
            addNavButton(menuPanel, "Account Requests", e -> dashboard.setContent(new AdminDashboardPanel()));
        }
        if (role == Role.HEAD_IT) {
            addNavButton(menuPanel, "Escalated Tickets", e -> dashboard.setContent(new HeadITPanel()));
        }
        if (role == Role.TECHNICIAN) {
            addNavButton(menuPanel, "Assigned Tickets", e -> dashboard.setContent(new TechDashboardPanel(user)));
        }
        if (role == Role.USER) {
            addNavButton(menuPanel, "My Tickets", e -> dashboard.setContent(new UserTicketsPanel(user)));
        }

        addNavButton(menuPanel, "Settings ⚙️", e -> dashboard.setContent(new SettingsPanel()));

        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        JButton logoutBtn = Style.createRoundedButton("Logout", new Color(200, 50, 50), Color.WHITE);
        logoutBtn.addActionListener(e -> logout());
        bottomPanel.add(Box.createVerticalGlue());
        bottomPanel.add(Box.createVerticalStrut(10));
        bottomPanel.add(logoutBtn);

        add(menuPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void toggleSidebar() {
        expanded = !expanded;
        int targetWidth = expanded ? EXPANDED_WIDTH : COLLAPSED_WIDTH;
        setPreferredSize(new Dimension(targetWidth, getHeight()));
        revalidate();
        repaint();
    }

    private void addNavButton(JPanel parent, String label, java.awt.event.ActionListener action) {
        JButton btn = Style.createRoundedButton(label, new Color(255, 255, 255, 120), Color.BLACK);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btn.addActionListener(action);
        parent.add(Box.createVerticalStrut(10));
        parent.add(btn);
    }

    private void logout() {
        // Clear session data
        System.out.println("Logging out: " + currentUser.getUsername());

        // Completely reset the frame content
        JFrame frame = dashboard.getParentFrame();
        frame.getContentPane().removeAll();
        frame.repaint();
        frame.revalidate();

        // Load a fresh login panel (new instance)
        frame.add(new LoginPanel(frame));
        frame.revalidate();
        frame.repaint();
    }

}
