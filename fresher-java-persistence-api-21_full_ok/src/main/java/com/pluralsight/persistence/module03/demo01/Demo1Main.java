package com.pluralsight.persistence.module03.demo01;

import com.pluralsight.persistence.module03.model.Book;
import org.springframework.context.ApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.sql.SQLException;

public class Demo1Main {

  private EntityManagerFactory entityManagerFactory;

  public Demo1Main(ApplicationContext context){
    this.entityManagerFactory = context.getBean(EntityManagerFactory.class);
  }

  public void test() throws SQLException, ClassNotFoundException {

    System.out.println("\n\n>>> Executing : " + Demo1Main.class.toString() + " <<<\n");

    //EntityManagerFactory emf = Persistence.createEntityManagerFactory("module03-persistence-unit");
    EntityManager em = entityManagerFactory.createEntityManager();
    EntityTransaction tx = em.getTransaction();

    BookService service = new BookService(em);

    // Creates and persists a Book
    tx.begin();
    Book book = service.createBook(4044L, "H2G2", "Best IT Scifi Book", 12.5f, "1234-5678-5678", 247);
    tx.commit();

    System.out.println("Book Persisted : " + book);

    // Finds the book
    book = service.findBook(4044L);

    System.out.println("Book Found     : " + book);

    // Raises the price of the book
    tx.begin();
    book = service.raiseUnitCost(4044L, 12.5F);
    tx.commit();

    System.out.println("Book Updated   : " + book);

    // Removes the book
    tx.begin();
    service.removeBook(4044L);
    tx.commit();

    System.out.println("Book Removed");

    // Finds the book
    book = service.findBook(4044L);

    System.out.println("Book Not Found : " + book);

    em.close();
    //emf.close();
  }
}