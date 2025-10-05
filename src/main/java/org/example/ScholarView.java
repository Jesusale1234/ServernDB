package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class that works as the view in the MVC pattern.
 */
public class ScholarView {

    private final ScholarController controller;

    public ScholarView(ScholarController controller) {
        this.controller = controller;
    }

    /**
     * Obtains the author names, and displays the 3 articles for each researcher.
     */
    public void start() {
        // To request the names to the user.

        Scanner scanner = new Scanner(System.in);
        List<String> authorName = new ArrayList<>();

        // "For" condition to include two authors.
        for (int i = 1; i <= 2; i++) {
            System.out.print("Author Name " + i + ": ");
            authorName.add(scanner.nextLine());
        }

        System.out.println("\nSearching " + authorName + " articles...\n");

        // We use the fetchResultsForAuthors function to search. We define 3 as maximum articles per author, just as the activity requested it.
        List<ScholarResult> results = controller.fetchResultsForAuthors(authorName, 3);

        // If there are no results, we display a message letting it known to the user.
        if (results.isEmpty()) {
            System.out.println("No articles found for that author.");
        } else {

            // To display all the information in the console, the same information that will be added into the MySQL database.
            System.out.println("Articles found:\n");
            for (ScholarResult article : results) {
                System.out.println("Title: " + article.getTitle());
                System.out.println("Authors: " + article.getAuthors());
                System.out.println("Date: " + article.getPublicationDate());
                System.out.println("Summary: " + article.getSummary());
                System.out.println("Keywords: " + article.getKeywords());
                System.out.println("URL Link: " + article.getLink());
                System.out.println("Cited by: " + article.getCitedBy());
                System.out.println("--------------------------------------------------");
            }
        }
    }
}
