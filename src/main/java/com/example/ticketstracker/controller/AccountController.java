package com.example.ticketstracker.controller;

import com.example.ticketstracker.model.AccountRequest;
import com.example.ticketstracker.controller.AuthController;
import com.example.ticketstracker.model.User;
import com.example.ticketstracker.model.Role;

import java.util.List;

/**
 * Handles admin-side account approvals and user list management.
 */
public class AccountController {

    // Fetch all pending account requests
    public static List<AccountRequest> getPendingRequests() {
        return AuthController.getPendingRequests();
    }

    // Approve a pending account request and add to users list
    public static void approve(AccountRequest request) {
        if (request != null) {
            User newUser = new User(
                    request.getUsername(),
                    request.getPassword(),
                    request.getRequestedRole()
            );
            AuthController.addUser(newUser);
            AuthController.removePendingRequest(request);
        }
    }

    // Reject (delete) a pending request
    public static void reject(AccountRequest request) {
        if (request != null) {
            AuthController.removePendingRequest(request);
        }
    }

    // Create a new account request (for signup)
    public static void requestAccount(String username, String password, Role role) {
        AuthController.addPendingRequest(username, password, role);
    }
}
