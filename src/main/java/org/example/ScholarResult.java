package org.example;

import java.util.List;

/**
 * Class that represents Google Scholar search data results
 * It only contains relevant data: Title and URL link
 */
public class ScholarResult {
    private String title;
    private String link;
    private String summary;
    private String publicationInfo;

    // To initialize title and link
    public ScholarResult(String title, String link,  String summary, String publicationInfo) {
        this.title = title;
        this.link = link;
        this.summary = summary;
        this.publicationInfo = publicationInfo;
    }

    // To get the title when doing the search
    public String getTitle() { return title; }

    // To get the URL link when doing the search
    public String getLink() { return link; }

    // To get a brief description of the article
    public String getSummary() { return summary; }

    // To get the authors, date of publication, etc.
    public String getPublicationInfo() { return publicationInfo; }
}
