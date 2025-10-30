package com.example.ticketstracker.controller;

import com.example.ticketstracker.model.*;

import java.util.ArrayList;
import java.util.List;

public class AuthController {

    private static final List<User> users = new ArrayList<>();
    private static final List<AccountRequest> pendingRequests = new ArrayList<>();

    static {
        // --- Default accounts ---
        users.add(new User("admin", "admin", Role.ADMIN));
        users.add(new User("headit", "headit", Role.HEAD_IT));
        users.add(new User("tech1", "tech1", Role.TECHNICIAN));
        users.add(new User("tech2", "tech2", Role.TECHNICIAN));
        users.add(new User("tech3", "tech3", Role.TECHNICIAN));
        users.add(new User("tech4", "tech4", Role.TECHNICIAN));
        users.add(new User("user1", "user1", Role.USER));
        users.add(new User("user2", "user2", Role.USER));
        users.add(new User("user3", "user3", Role.USER));
        users.add(new User("user4", "user4", Role.USER));
    }

    // Login check
    public static User login(String username, String password) {
        for (User u : users) {
            if (u.getUsername().equalsIgnoreCase(username)
                    && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }

    // Account request handling
    public static void addPendingRequest(String username, String password, Role role) {
        pendingRequests.add(new AccountRequest(username, password, role));
    }

    public static List<AccountRequest> getPendingRequests() {
        return pendingRequests;
    }

    public static void approveAccount(AccountRequest request) {
        users.add(new User(request.getUsername(), request.getPassword(), request.getRequestedRole()));
        pendingRequests.remove(request);
    }

    public static void rejectAccount(AccountRequest request) {
        pendingRequests.remove(request);
    }

    // Admin utilities
    public static void addUser(User u) { users.add(u); }
    public static void removePendingRequest(AccountRequest r) { pendingRequests.remove(r); }
    public static List<User> getUsers() { return users; }
}
