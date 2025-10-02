package org.example;

import java.util.List;

/**
 * Main class that executes the program, and starts the Google Scholar search with the MVC pattern.
 */
public class Main {
    public static void main(String[] args) {
        try {
            // Personal SerpAPI API Key
            String apiKey = "4627cfced275509bd890149893b7a5625d67ead959bf189a180bbf998c7ea8f0";

            // Creates the controller which makes the HTTP request
            ScholarController controller = new ScholarController(apiKey);

            // Creates the view to print the results in console
            ScholarView view = new ScholarView();

            // Obtains the results of the Scholar search. In the "author" parameter, we can modify the author we want to search
            List<ScholarResult> results = controller.fetchResults("Albert Einstein");

            // Prints the results using the view
            view.printResults(results);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}