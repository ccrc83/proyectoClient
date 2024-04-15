package com.app.clients.application.utility.constants;

public class Utilities {
    public static String getUsername(String email) {
        // Split the email string into two parts using "@" as delimiter
        String[] parts = email.split("@");

        // Check if there are at least two parts (username and domain)
        if (parts.length >= 2) {
            // The username is in the first part
            return parts[0];
        } else {
            // If the email doesn't contain the "@" symbol, return the full email
            return email;
        }
    }
}
