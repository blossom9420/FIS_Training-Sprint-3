package com.pluralsight.persistence.module01.demo01;

import java.sql.*;

public class Main {

  public static void main(String[] args) throws SQLException, ClassNotFoundException {

    System.out.println("\n\n>>> Executing : " + Main.class.toString() + " <<<\n");

    BookDAO.persistBook(new Book(5000L, "H2G2", "Best IT Scifi Book", 12.5f, "1234-5678-5678", 247));

    Book book = BookDAO.findBook(5000L);

    System.out.println("# " + book);
  }
}


