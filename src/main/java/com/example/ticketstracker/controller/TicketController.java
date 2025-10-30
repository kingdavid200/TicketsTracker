package com.example.ticketstracker.controller;

import com.example.ticketstracker.model.Ticket;
import com.example.ticketstracker.model.User;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class TicketController {
    private static final List<Ticket> tickets = new ArrayList<>();
    private static int nextId = 1;

    public static String generateTicketId() {
        return "TCK-" + (nextId++);
    }

    public static void addTicket(Ticket t) {
        tickets.add(t);
    }

    public static List<Ticket> getAllTickets() {
        return tickets.stream()
                .sorted(Comparator.comparing(Ticket::getCreatedAt).reversed())
                .collect(Collectors.toList());
    }

    public static List<Ticket> getTicketsByUser(User u) {
        return tickets.stream()
                .filter(t -> t.getCreatedBy().equalsIgnoreCase(u.getUsername()))
                .sorted(Comparator.comparing(Ticket::getCreatedAt).reversed())
                .collect(Collectors.toList());
    }

    public static List<Ticket> getTicketsByTechnician(String tech) {
        return tickets.stream()
                .filter(t -> tech.equalsIgnoreCase(t.getAssignedTo()))
                .sorted(Comparator.comparing(Ticket::getAssignedDate).reversed())
                .collect(Collectors.toList());
    }

    public static void assignTicket(Ticket ticket, String tech) {
        ticket.setAssignedTo(tech);
        ticket.setAssignedDate(LocalDateTime.now());
        ticket.setStatus("ASSIGNED");
    }

    public static void updateTicketStatus(Ticket ticket, String status) {
        ticket.setStatus(status);
    }
}
