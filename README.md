# ServernDB

Instructions:
1. Download the project
2. Use MySQL Workbench to create the database.
3. Create the database by pasting and executing the following query:

   CREATE DATABASE scholar_db;
    USE scholar_db;

    CREATE TABLE articles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(500),
    authors VARCHAR(500),
    publication_date VARCHAR(50),
    abstract TEXT,
    link VARCHAR(1000),
    keywords VARCHAR(500),
    cited_by INT
  );

4. Open the Java project in an IDE of preference.
5. Execute the main class to start running the program.


Sprint's main functions:

Sprint 3: MySQL database used to store the searches for articles.

Sprint 2: Google Scholar API used to search by author name

Sprint 1: GitHub repository was created

Main purpose of the project: To learn about database creation while applying Java programming and its APIs, such as the Google Scholar API.

Key functionalities:
  - Use the Java programming language.
  - Use Google Scholar API supported by SerpAPI.
  - Connect the code with a relational database (MySQL).
  - Insert specific data to be stored in the database.

Project relevance:
  - Crucial: To learn about relational databases (MySQL, PostgreSQL, SQLite)
  - Intermediate: To apply previous knowledge about Java programming and GitHub version control.

Technical Document for the Google Scholar API: https://docs.google.com/document/d/1SsRPEkoKXqSCGtTPenJ0W3eY4VfFYwA1pZuDjXJKaMk/edit?usp=sharing
