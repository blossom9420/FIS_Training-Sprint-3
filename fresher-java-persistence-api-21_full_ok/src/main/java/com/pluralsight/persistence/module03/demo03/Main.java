package com.pluralsight.persistence.module03.demo03;

import com.pluralsight.persistence.module03.model.Author;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.SQLException;

import static com.pluralsight.persistence.module03.model.Language.ENGLISH;
import static com.pluralsight.persistence.module03.model.Util.date;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */
public class Main {

  //TODO convert to SpringBoot
  public static void main(String[] args) throws SQLException, ClassNotFoundException {

    System.out.println("\n\n>>> Executing : " + Main.class.toString() + " <<<\n");

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("module03-persistence-unit");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();

    AuthorService service = new AuthorService(em);
    Author author = new Author("Douglas", "Adams", "Adams is best known as the author of H2G2", date("11/03/1952"), 49, ENGLISH);

    // Creates and persists an author
    tx.begin();
    author = service.createAuthor(author);
    tx.commit();
    Long id = author.getId();

    System.out.println("Author Persisted : " + author);

    // Finds the author
    author = service.findAuthor(id);

    System.out.println("Author Found     : " + author);

    // Removes the author
    tx.begin();
    service.removeAuthor(author);
    tx.commit();

    System.out.println("Author Removed");

    // Finds the author
    author = service.findAuthor(id);

    System.out.println("Author Not Found : " + author);

    em.close();
    emf.close();
  }
}


