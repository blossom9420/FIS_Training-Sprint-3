package com.javadevjournal.springbooth2;

import com.javadevjournal.springbooth2.model.Book;
import com.javadevjournal.springbooth2.repository.EmployeeRepository;
import com.javadevjournal.springbooth2.service.BookService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javax.persistence.*;
import java.sql.SQLException;
import java.util.Arrays;

@SpringBootApplication
public class BookApplication {
  public static void main(String[] args) throws SQLException, ClassNotFoundException {
    ApplicationContext context = SpringApplication.run(BookApplication.class, args);
  }
}

class BookRepository {

  private EntityManagerFactory entityManagerFactory;

  public BookRepository(ApplicationContext context){
    this.entityManagerFactory = context.getBean(EntityManagerFactory.class);
  }

  public void test(){
    System.out.println("\n\n>>> Executing : " + BookApplication.class.toString() + " <<<\n");

    EntityManager entityManager = entityManagerFactory.createEntityManager();

    EntityTransaction tx = entityManager.getTransaction();

    BookService service = new BookService(entityManager);

    // Creates and persists a Book
    tx.begin();
    Book book = service.createBook(4044L, "H2G2", "Best IT Scifi Book", 12.5f, "1234-5678-5678", 247);
    tx.commit();

    System.out.println("Book Persisted : " + book);

    // Finds the book
    book = service.findBook(4044L);

    System.out.println("Book Found     : " + book);

    // Raises the price of the book
//    tx.begin();
//    book = service.raiseUnitCost(4044L, 12.5F);
//    tx.commit();

    System.out.println("Book Updated   : " + book);

    // Removes the book
//    tx.begin();
//    service.removeBook(4044L);
//    tx.commit();

    System.out.println("Book Removed");

    // Finds the book
    book = service.findBook(4044L);

    System.out.println("Book Not Found : " + book);

//    entityManager.close();
  }
}


