package com.example.ticketstracker.model;

public class AccountRequest {
    private String username;
    private String password;
    private Role requestedRole;

    public AccountRequest(String username, String password, Role requestedRole) {
        this.username = username;
        this.password = password;
        this.requestedRole = requestedRole;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public Role getRequestedRole() { return requestedRole; }
}
