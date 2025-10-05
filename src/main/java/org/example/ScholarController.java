package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller that obtains the data from the Google Scholar API
 * and connects the storage of the data to the ArticleDAO class.
 */
public class ScholarController {

    // Personal API Key and ArticleDAO statement declaration.
    public String apiKey = "4627cfced275509bd890149893b7a5625d67ead959bf189a180bbf998c7ea8f0";
    private final ArticleDAO dao = new ArticleDAO();

    /**
     *
     * @param text Text that will be used a reference to make the keywords.
     * @return Keywords in a string format.
     */
    private String generateKeywords(String text) {
        if (text == null || text.isEmpty()) return "N/A";

        // Common words list that are going to be ignored.
        String[] stopWords = {"the", "and", "of", "in", "on", "for", "with", "a", "an", "to", "by", "from", "or"};

        // To include all the words in lowercase.
        String lowerText = text.toLowerCase();

        // To separate the words with a comma.
        String[] words = lowerText.split("\\W+");

        // Object that accumulates the keywords.
        StringBuilder keywords = new StringBuilder();

        // To go through each word of the summary.
        for (String w : words) {


            boolean isStopWord = false;

            // The "for condition" is to determine if a word is a generic one considering the previous list of stopWords.
            for (String stop : stopWords) {
                if (w.equals(stop)) {
                    isStopWord = true;
                    break;
                }
            }
            // To ignore short words and add the actual keywords into the list for a specific article.
            if (!isStopWord && w.length() > 2) {
                if (keywords.length() > 0) keywords.append(", ");
                keywords.append(w);
            }
        }
        return keywords.toString();
    }

    /**
     * Obtains the results of the authors with the Google Scholar API.
     *
     * @param authors Author name.
     * @return Articles found in the search.
     */
    public List<ScholarResult> fetchResultsForAuthors(List<String> authors, int maxArticlesPerAuthor) {
        List<ScholarResult> articles = new ArrayList<>();

        for (String authorName : authors) {
            List<ScholarResult> authorArticles = new ArrayList<>();
        try {
            // URL to make the search, including the author name and the personal API Key.
            String urlStr = "https://serpapi.com/search.json?engine=google_scholar&q=author:"
                    + authorName.replace(" ", "+")
                    + "&api_key=" + apiKey;

            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // To read the results of the search.
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) response.append(line);
            reader.close();

            // JSON parsing.
            JSONObject jsonResponse = new JSONObject(response.toString());
            JSONArray results = jsonResponse.optJSONArray("organic_results");

            if (results == null) return articles;

            // To check every important element of each individual article of an author.
            int count = 0;
            for (int i = 0; i < results.length() && count < maxArticlesPerAuthor; i++) {
                JSONObject article = results.getJSONObject(i);

                // To search for and specific element and assign it, we use an individual key for each string
                // considering the format used in the API, and if there is no element found, we show messages such as: No title, Unavailable, etc.
                String title = article.optString("title", "No title");
                String link = article.optString("link", "Unavailable");
                String summary = article.optString("snippet", "No abstract");
                String keywords = generateKeywords(summary);

                JSONObject citedByObj = article.optJSONObject("inline_links") != null
                        ? article.getJSONObject("inline_links").optJSONObject("cited_by")
                        : null;
                int citedBy = (citedByObj != null) ? citedByObj.optInt("total", 0) : 0;

                String author = "Unknown";
                String publicationDate = "N/A";

                // To read the information inside the "publication_info"
                JSONObject pubInfo_obj = article.optJSONObject("publication_info");

                // To check if the information inside that element is not null. If it is not, we read the summary.
                String pubInfo = (pubInfo_obj != null)
                        ? pubInfo_obj.optString("summary", "No summary")
                        : "No summary";

                // We obtain the year included inside the summary to add it into the table as "publicationDate".
                if (pubInfo.contains(" - ")) {
                    String[] parts = pubInfo.split(" - ");
                    author = parts[0];
                    if (parts.length > 1) publicationDate = parts[1];
                }

                // We use the ScholarResult function to create the model and use the previously obtained information.
                ScholarResult result = new ScholarResult(
                        title,
                        author,
                        publicationDate,
                        summary,
                        link,
                        keywords,
                        citedBy
                );

                // To add a single article searched into the list.
                authorArticles.add(result);

                // To store in the database.
                dao.saveArticle(result);
                count++;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        // To add all the list of the articles into the list.
        articles.addAll(authorArticles);
        }
        return articles;
    }
}