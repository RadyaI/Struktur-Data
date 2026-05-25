package modul_5.TASK2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class AuthManager {

    private HashMap<String, String> users = new HashMap<>();
    private HashMap<String, ArrayList<String>> userDetails = new HashMap<>();
    private String loggedInUser = null;

//    public AuthManager(){
//        users.put("radya@gmail.com", "12345678");
//        ArrayList<String> defaultData = new ArrayList<>();
//        defaultData.add("Muhammad Radya Iftikhar");
//        defaultData.add("Malang, Sawojajar");
//        userDetails.put("radyaiftikhar@gmail.com", defaultData);
//    }

    public boolean isLoggedIn() {
        return loggedInUser != null;
    }

    public String getLoggedInUser() {
        return loggedInUser;
    }

    public String getFullName(String username) {
        return userDetails.get(username).get(0);
    }

    public void register(Scanner sc) {
        System.out.println("\n--- Register ---");
        System.out.print("Username: ");
        String username = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();

        if (users.containsKey(username)) {
            System.out.println("Registration Failed! Username already exists.");
            return;
        }

        if (!username.contains("@")) {
            System.out.println("Registration Failed! Username must contain '@'.");
            return;
        }

        if (password.length() < 8) {
            System.out.println("Registration Failed! Password min 8 characters.");
            return;
        }

        System.out.print("Full Name: ");
        String fullName = sc.nextLine();
        System.out.print("Address: ");
        String address = sc.nextLine();

        ArrayList<String> details = new ArrayList<>();
        details.add(fullName);
        details.add(address);

        users.put(username, password);
        userDetails.put(username, details);
        System.out.println("Registration Successful!");
    }

    public void login(Scanner sc) {
        System.out.println("\n--- Login ---");
        System.out.print("Username: ");
        String username = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();

        if (users.containsKey(username) && users.get(username).equals(password)) {
            loggedInUser = username;
            ArrayList<String> d = userDetails.get(username);
            System.out.println("Login Successful!");
            System.out.printf("Welcome, %s (%s)%n", d.get(0), d.get(1));
        } else {
            System.out.println("Login Failed! Wrong username or password.");
        }
    }

    public void logout() {
        System.out.println("Logged out successfully. See you, " + loggedInUser + "!");
        loggedInUser = null;
    }
}