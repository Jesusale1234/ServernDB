package org.example;

import java.util.List;

/**
 * MVC pattern view.
 * It shows us the results of the search in the console.
 */
public class ScholarView {

    /**
     * Uses the Google Scholar API by SerpApi to print a list of results in console.
     *
     * @param results ScholarResult list
     */
    public void printResults(List<ScholarResult> results) {
        System.out.println("=== Google Scholar Results ===");

        // With a "for" condition, the list explores each element of the search and adds them with a "Title", "URL link", "Authors",
        // and a column of "---" to improve the visuals in the console.
        for (ScholarResult r : results) {
            System.out.println("Title: " + r.getTitle());
            System.out.println("URL Link: " + r.getLink());
            System.out.println("Description: " + r.getSummary());
            System.out.println("About: " + r.getPublicationInfo());
            System.out.println("-----------------------------");
        }
    }
}
