package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * MVC pattern controller.
 * It makes the HTTP request to the API
 * and converts the JSON response to obtain relevant data.
 */
public class ScholarController {
    private String apiKey;

    /**
     * It obtains the SerpAPI Key.
     * @param apiKey personal key to authenticate searches
     */
    public ScholarController(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * It searches on Google Scholar by author name with the 'q' parameter.
     * @param author Full name of the author.
     * @return Google Scholar results list including Title, URL link, and Author.
     * @throws Exception If there is an error when doing the HTTP request.
     */
    public List<ScholarResult> fetchResults(String author) throws Exception {

        // Encodes the author name to avoid errors in the string format.
        String encodedAuthor = URLEncoder.encode(author, "UTF-8");

        // Fabricates the URL link to use the API with the 'q' parameter using the author name and the API key.
        String urlStr = "https://serpapi.com/search.json?engine=google_scholar&q=author:"
                + encodedAuthor + "&api_key=" + apiKey;

        // Creates the HTTP connection.
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        // Reads the entire response provided by the search.
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // Parses the response in a JSON format to a string format.
        JSONObject jsonResponse = new JSONObject(response.toString());

        // We use a "for" condition to go through all the elements in the organic results category.
        List<ScholarResult> list = new ArrayList<>();
        if (jsonResponse.has("organic_results")) {
            JSONArray results = jsonResponse.getJSONArray("organic_results");

            for (int i = 0; i < results.length(); i++) {
                JSONObject item = results.getJSONObject(i);

                String title = item.optString("title");
                String link = item.optString("link");
                String summary = item.optString("snippet");

                String publicationInfo = "";
                if (item.has("publication_info")) {
                    JSONObject pubInfo = item.getJSONObject("publication_info");
                    publicationInfo = pubInfo.optString("summary", "Publication Info not found");
                }

                list.add(new ScholarResult(title, link, summary, publicationInfo));
            }
        }
        return list;
    }
}
