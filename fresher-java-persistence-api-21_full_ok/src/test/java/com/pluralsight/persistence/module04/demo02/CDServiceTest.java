package com.pluralsight.persistence.module04.demo02;

import com.pluralsight.persistence.module04.model.CD;
import com.pluralsight.persistence.module04.model.Musician;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


//TODO Convert to SpringBootTest

public class CDServiceTest {

  // ======================================
  // =             Attributes             =
  // ======================================

  private static EntityManagerFactory emf;
  private static EntityManager em;
  private static EntityTransaction tx;

  // ======================================
  // =          Lifecycle Methods         =
  // ======================================

  @BeforeAll
  public void initEntityManager() {
    emf = Persistence.createEntityManagerFactory("module04-persistence-unit");
    em = emf.createEntityManager();
    tx = em.getTransaction();
  }

  @AfterAll
  public void closeEntityManager() {
    if (em != null) em.close();
    if (emf != null) emf.close();
  }

  // ======================================
  // =              Methods               =
  // ======================================

  @Test
  public void shouldManageCD() {

    CDService service = new CDService(em);

    // Creates and persists a CD
    tx.begin();
    Set<Musician> beatles = new HashSet<>();
    beatles.add(new Musician("John", "Lennon"));
    beatles.add(new Musician("Paul", "McCartney"));
    beatles.add(new Musician("Ringo", "Starr"));
    beatles.add(new Musician("Georges", "Harrison"));
    CD sergentPepper = new CD("Sergent Pepper");
    sergentPepper.setMusicians(beatles);
    sergentPepper = service.createCD(sergentPepper);
    tx.commit();
    assertNotNull(sergentPepper);
    Long id = sergentPepper.getId();

    // Finds the CD by primary key
    sergentPepper = service.findCD(id);
    assertNotNull(sergentPepper);
    assertEquals(4, sergentPepper.getMusicians().size());

    // Deletes the CD
    tx.begin();
    service.removeCD(id);
    tx.commit();

    // Finds the book by primary key
    sergentPepper = service.findCD(id);
    assertNull(sergentPepper);
  }
}