package com.example.ticketstracker.ui;

import com.example.ticketstracker.controller.TicketController;
import com.example.ticketstracker.model.Ticket;
import com.example.ticketstracker.model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class UserTicketsPanel extends JPanel {
    private final User currentUser;
    private JTable table;
    private DefaultTableModel model;

    public UserTicketsPanel(User user) {
        this.currentUser = user;
        setLayout(new BorderLayout());
        setOpaque(false);

        JLabel title = new JLabel("My Tickets", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        add(title, BorderLayout.NORTH);

        String[] cols = {"ID", "Title", "Description", "Status", "Progress", "Created"};
        model = new DefaultTableModel(cols, 0);
        table = new JTable(model);
        table.setRowHeight(25);
        add(new JScrollPane(table), BorderLayout.CENTER);

        loadUserTickets();
    }

    private void loadUserTickets() {
        model.setRowCount(0);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        List<Ticket> tickets = TicketController.getTicketsByUser(currentUser);

        for (Ticket t : tickets) {
            model.addRow(new Object[]{
                    t.getId(),
                    t.getTitle(),
                    t.getDescription(),
                    t.getStatus(),
                    t.getProgress() + "%",
                    t.getCreatedAt().format(fmt)
            });
        }
    }
}
