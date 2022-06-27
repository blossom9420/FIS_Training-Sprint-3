package com.pluralsight.persistence.module04.demo03;

import com.pluralsight.persistence.module04.model.Book;
import com.pluralsight.persistence.module04.model.CD;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


//TODO convert to spring boot
public class Main {

  public static void main(String[] args) {

    System.out.println("\n\n>>> Executing : " + Main.class.toString() + " <<<\n");

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("module04-persistence-unit");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();

    ItemService service = new ItemService(em);

    // Creates and persists a CD
    tx.begin();
    CD cd = new CD("Sergent Pepper", "Pop/Rock");
    cd = service.createCD(cd);
    tx.commit();

    System.out.println("CD Persisted : " + cd);

    // Creates and persists a Book
    tx.begin();
    Book book = new Book("H2G2", "1234-4567-5678");
    book = service.createBook(book);
    tx.commit();

    System.out.println("Book Persisted : " + book);

    em.close();
    emf.close();
  }
}