package com.pluralsight.persistence.module05.demo02;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

  public static void main(String[] args) {

    System.out.println("\n\n>>> Executing : " + Main.class.toString() + " <<<\n");

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("module05-persistence-unit");
    EntityManager em = emf.createEntityManager();

    QueryService service = new QueryService(em);

    System.out.println("--- Result");
    service.queryBooksPosition(25F, 700);

    em.close();
    emf.close();
  }
}


