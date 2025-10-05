package org.example;

/**
 * Represents the articles received from the search. It storages the information to display it in console.
 */
public class ScholarResult {

    // It includes all the elements listed in the database to add them to it after they are stored.
    private String title;
    private String authors;
    private String publicationDate;
    private String Summary;
    private String link;
    private String keywords;
    private int citedBy;

    // The function used to store the elements mentioned in the search.
    public ScholarResult(String title, String authors, String publicationDate,
                         String Summary, String link, String keywords, int citedBy) {
        this.title = title;
        this.authors = authors;
        this.publicationDate = publicationDate;
        this.Summary = Summary;
        this.link = link;
        this.keywords = keywords;
        this.citedBy = citedBy;
    }

    // Getters of each element.
    public String getTitle() {
        return title;
    }

    public String getAuthors() {
        return authors;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public String getSummary() {
        return Summary;
    }

    public String getLink() {
        return link;
    }

    public String getKeywords() {
        return keywords;
    }

    public int getCitedBy() {
        return citedBy;
    }

    // Turns the data into string to include these elements with a good format in the console.
    public String toString() {
        return "ScholarResult{" +
                "title='" + title + '\'' +
                ", authors='" + authors + '\'' +
                ", publicationDate='" + publicationDate + '\'' +
                ", abstractSummary='" + Summary + '\'' +
                ", link='" + link + '\'' +
                ", keywords='" + keywords + '\'' +
                ", citedBy=" + citedBy +
                '}';
    }
}