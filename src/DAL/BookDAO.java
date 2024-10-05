package DAL;

//BookDAO.java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.Book;

public class BookDAO implements IBookDAO{
 private static final String URL = "jdbc:mysql://localhost:3306/bookdb";
 private static final String USER = "root";
 private static final String PASSWORD = "";

 public List<Book> searchBooksByTitle(String title) {
     List<Book> books = new ArrayList<>();
     String query = "SELECT * FROM books WHERE title LIKE ?";
     
     try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
          PreparedStatement stmt = conn.prepareStatement(query)) {
         
         stmt.setString(1, "%" + title + "%");
         ResultSet rs = stmt.executeQuery();
         
         while (rs.next()) {
             String bookTitle = rs.getString("title");
             String author = rs.getString("author");
             int year = rs.getInt("year");
             books.add(new Book(bookTitle, author, year));
         }
     } catch (SQLException e) {
         e.printStackTrace();
     }
     
     return books;
 }
}
