package com.pluralsight.persistence.module01.demo01;

import java.sql.*;

public class BookDAO {
    /**
     * Gets a database connection
     */
    static {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Connection getConnection() throws SQLException {
        //return DriverManager.getConnection("jdbc:h2:mem:cdbookstoreDB");
        return DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "sa");
    }

    /**
     * Persists the book to the database
     */
    public static void persistBook(Book book) throws SQLException {

        String query = "INSERT INTO BOOK (ID, TITLE, DESCRIPTION, UNITCOST, ISBN, NBOFPAGE) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {

            stmt.setLong(1, book.getId());
            stmt.setString(2, book.getTitle());
            stmt.setString(3, book.getDescription());
            stmt.setFloat(4, book.getUnitCost());
            stmt.setString(5, book.getIsbn());
            stmt.setInt(6, book.getNbOfPage());

            stmt.executeUpdate();
        }
    }

    /**
     * Finds the book from the database
     */
    public static Book findBook(Long id) throws SQLException {

        Book book = new Book(id);
        String query = "SELECT ID, TITLE, DESCRIPTION, UNITCOST, ISBN, NBOFPAGE FROM BOOK WHERE ID = ?";

        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {

            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                book.setTitle(rs.getString("TITLE"));
                book.setDescription(rs.getString("DESCRIPTION"));
                book.setUnitCost(rs.getFloat("UNITCOST"));
                book.setIsbn(rs.getString("ISBN"));
                book.setNbOfPage(rs.getInt("NBOFPAGE"));
            }
        }
        return book;
    }
}
