package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Inserts the data into the MySQL database, considering the multiple parameters of the table.
 */
public class ArticleDAO {

    /**
     * It adds the data into the MySQL database.
     *
     * @param article The stored data that will be added into the database.
     */
    public static void saveArticle(ScholarResult article) {
        String sql = "INSERT INTO articles (title, authors, publication_date, abstract, link, keywords, cited_by) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        // It connects with the database and assigns the respective elements given the format of the sql statement.
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // It sets the parameters given their title, author, publicationdate, etc. All of which will be added to the database.
            stmt.setString(1, article.getTitle());
            stmt.setString(2, article.getAuthors());
            stmt.setString(3, article.getPublicationDate());
            stmt.setString(4, article.getSummary());
            stmt.setString(5, article.getLink());
            stmt.setString(6, article.getKeywords());
            stmt.setObject(7, article.getCitedBy());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Insertion error in the database: " + e.getMessage());
        }
    }
}
