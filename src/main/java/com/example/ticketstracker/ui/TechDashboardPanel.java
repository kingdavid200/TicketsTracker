package com.example.ticketstracker.ui;

import com.example.ticketstracker.controller.TicketController;
import com.example.ticketstracker.model.Ticket;
import com.example.ticketstracker.model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TechDashboardPanel extends JPanel {
    private JTable table;
    private DefaultTableModel model;
    private final User tech;

    public TechDashboardPanel(User tech) {
        this.tech = tech;
        setLayout(new BorderLayout());
        setOpaque(false);

        JLabel title = new JLabel("Assigned Tickets", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        add(title, BorderLayout.NORTH);

        String[] cols = {"ID", "Title", "Description", "Assigned Date", "Status", "Action"};
        model = new DefaultTableModel(cols, 0);
        table = new JTable(model);
        table.setRowHeight(25);
        add(new JScrollPane(table), BorderLayout.CENTER);

        refreshTable();
    }

    private void refreshTable() {
        model.setRowCount(0);
        List<Ticket> tickets = TicketController.getTicketsByTechnician(tech.getUsername());

        for (Ticket t : tickets) {
            JButton update = new JButton("Mark Fixed");
            update.addActionListener(e -> {
                TicketController.updateTicketStatus(t, "FIXED");
                refreshTable();
            });
            model.addRow(new Object[]{
                    t.getId(),
                    t.getTitle(),
                    t.getDescription(),
                    t.getAssignedDate() == null ? "—" : t.getAssignedDate().toString(),
                    t.getStatus(),
                    update
            });
        }
    }
}
