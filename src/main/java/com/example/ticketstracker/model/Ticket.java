package com.example.ticketstracker.model;

import java.time.LocalDateTime;

public class Ticket {
    private String id;
    private String title;
    private String description;
    private String createdBy;
    private String assignedTo;
    private LocalDateTime createdAt;
    private LocalDateTime assignedDate;
    private String status;
    private int progress;

    public Ticket(String id, String title, String description, String createdBy) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdBy = createdBy;
        this.createdAt = LocalDateTime.now();
        this.status = "OPEN";
        this.progress = 0;
    }

    // Getters and setters
    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getCreatedBy() { return createdBy; }
    public String getAssignedTo() { return assignedTo; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getAssignedDate() { return assignedDate; }
    public String getStatus() { return status; }
    public int getProgress() { return progress; }

    public void setAssignedTo(String assignedTo) { this.assignedTo = assignedTo; }
    public void setAssignedDate(LocalDateTime assignedDate) { this.assignedDate = assignedDate; }
    public void setStatus(String status) { this.status = status; }
    public void setProgress(int progress) { this.progress = progress; }

    @Override
    public String toString() {
        return "[" + id + "] " + title + " - " + status;
    }
}
