package com.example.ticketstracker.ui;

import com.example.ticketstracker.controller.AuthController;
import com.example.ticketstracker.controller.TicketController;
import com.example.ticketstracker.model.Role;
import com.example.ticketstracker.model.Ticket;
import com.example.ticketstracker.model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class AdminDashboardPanel extends JPanel {
    private JTable table;
    private DefaultTableModel model;

    public AdminDashboardPanel() {
        setLayout(new BorderLayout());
        setOpaque(false);

        JLabel title = new JLabel("All Tickets", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        add(title, BorderLayout.NORTH);

        String[] cols = {"ID", "Title", "Description", "Created By", "Assign To", "Date Created", "Date Assigned", "Status"};
        model = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4; // Only "Assign To" is editable
            }
        };

        table = new JTable(model);
        table.setRowHeight(30);
        JScrollPane scroll = new JScrollPane(table);
        add(scroll, BorderLayout.CENTER);

        loadTickets();

        // Listen for assignment changes
        table.getModel().addTableModelListener(e -> {
            int row = e.getFirstRow();
            int col = e.getColumn();
            if (col == 4 && row >= 0) {
                String ticketId = (String) model.getValueAt(row, 0);
                String assignedTech = (String) model.getValueAt(row, 4);
                assignTicket(ticketId, assignedTech);
            }
        });
    }

    private void loadTickets() {
        model.setRowCount(0);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        List<Ticket> tickets = TicketController.getAllTickets();

        // Build tech dropdown list
        List<String> techs = AuthController.getUsers().stream()
                .filter(u -> u.getRole() == Role.TECHNICIAN)
                .map(User::getUsername)
                .collect(Collectors.toList());

        JComboBox<String> combo = new JComboBox<>(techs.toArray(new String[0]));
        table.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(combo));

        for (Ticket t : tickets) {
            model.addRow(new Object[]{
                    t.getId(),
                    t.getTitle(),
                    t.getDescription(),
                    t.getCreatedBy(),
                    t.getAssignedTo() == null ? "—" : t.getAssignedTo(),
                    t.getCreatedAt().format(fmt),
                    t.getAssignedDate() == null ? "—" : t.getAssignedDate().format(fmt),
                    t.getStatus()
            });
        }
    }

    private void assignTicket(String ticketId, String techName) {
        List<Ticket> all = TicketController.getAllTickets();
        for (Ticket t : all) {
            if (t.getId().equals(ticketId)) {
                TicketController.assignTicket(t, techName);
                loadTickets();
                JOptionPane.showMessageDialog(this, "Ticket " + ticketId + " assigned to " + techName);
                break;
            }
        }
    }
}
