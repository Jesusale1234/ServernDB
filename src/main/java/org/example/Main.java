package org.example;

/**
 * Main class that executes the program
 */
public class Main {
    public static void main(String[] args) {
        ScholarController controller = new ScholarController();
        ScholarView view = new ScholarView(controller);

        // Begins the user's interaction with the program
        view.start();
    }
}