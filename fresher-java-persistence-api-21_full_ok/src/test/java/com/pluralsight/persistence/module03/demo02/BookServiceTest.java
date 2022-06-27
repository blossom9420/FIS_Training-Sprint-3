package com.pluralsight.persistence.module03.demo02;

import com.pluralsight.persistence.module03.model.Book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import javax.persistence.EntityManagerFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BookServiceTest {
  @Autowired
  private ApplicationContext applicationContext;
  @Test
  public void shouldManageBook() {

    BookService service = new BookService(applicationContext);

    // Creates and persists a Book
    Book book = service.createBook(4044L, "H2G2", "Best IT Scifi Book", 12.5f, "1234-5678-5678", 247);
    assertNotNull(book);

    // Finds the book
    book = service.findBook(4044L);
    assertEquals(new Float(12.5f), book.getUnitCost());

    // Raises the price of the book
    book = service.raiseUnitCost(4044L, 12.5F);
    assertEquals(new Float(25.0f), book.getUnitCost());

    // Removes the book
    service.removeBook(4044L);

    // Finds the book
    book = service.findBook(4044L);
    assertNull(book);
  }
}