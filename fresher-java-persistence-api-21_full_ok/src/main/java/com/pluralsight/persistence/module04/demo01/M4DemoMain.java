package com.pluralsight.persistence.module04.demo01;

import com.pluralsight.persistence.module04.model.CD;
import org.springframework.context.ApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.SQLException;

public class M4DemoMain {

  private EntityManager em;

  public M4DemoMain(ApplicationContext context){
    EntityManagerFactory emf = context.getBean(EntityManagerFactory.class);
    em = emf.createEntityManager();
  }

  public void test() throws SQLException, ClassNotFoundException {

    System.out.println("\n\n>>> Executing : " + M4DemoMain.class.toString() + " <<<\n");

//    EntityManagerFactory emf = Persistence.createEntityManagerFactory("module04-persistence-unit");
//    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();

    CDService service = new CDService(em);

    // Creates and persists a CD
    tx.begin();
    CD cd = service.createCD(new CD("Selling England by the Pound", "5th studio album by the progressive rock band Genesis", 12.5F, 53.39F, "Progressive Rock"));
    tx.commit();

    System.out.println("CD Persisted : " + cd);

    // Finds the cd by primary key
    cd = service.findCD(cd.getId());

    System.out.println("CD Found     : " + cd);

    // Deletes the cd
    tx.begin();
    service.removeCD(cd.getId());
    tx.commit();

    System.out.println("CD Removed");

    // Finds the cd  by primary key
    cd = service.findCD(cd.getId());

    System.out.println("CD Not Found : " + cd);

    em.close();
    //emf.close();
  }
}


