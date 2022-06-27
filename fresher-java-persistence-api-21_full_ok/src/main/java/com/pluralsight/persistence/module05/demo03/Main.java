package com.pluralsight.persistence.module05.demo03;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */
public class Main {

  public static void main(String[] args) {

    System.out.println("\n\n>>> Executing : " + Main.class.toString() + " <<<\n");

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("module05-persistence-unit");
    EntityManager em = emf.createEntityManager();

    QueryService service = new QueryService(em);

    System.out.println("--- Result");
    service.queryBooksTyped();

    em.close();
    emf.close();
  }
}


