package com.pluralsight.persistence.module04.demo01;

import com.pluralsight.persistence.module04.model.CD;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

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
    CD cd = service.createCD(new CD("Selling England by the Pound", "5th studio album by the progressive rock band Genesis", 12.5F, 53.39F, "Progressive Rock"));
    tx.commit();
    assertNotNull(cd);
    Long id = cd.getId();

    // Finds the CD by primary key
    cd = service.findCD(id);
    assertNotNull(cd);
    assertEquals(new Float(12.5f), cd.getUnitCost());

    // Deletes the CD
    tx.begin();
    service.removeCD(id);
    tx.commit();

    // Finds the book by primary key
    cd = service.findCD(id);
    assertNull(cd);
  }
}