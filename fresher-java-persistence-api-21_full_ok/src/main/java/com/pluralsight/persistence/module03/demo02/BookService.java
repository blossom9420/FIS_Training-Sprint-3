package com.pluralsight.persistence.module03.demo02;

import com.pluralsight.persistence.module03.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class BookService {

  @Autowired
  ApplicationContext applicationContext;
  // ======================================
  // =             Attributes             =
  // ======================================

  private EntityManagerFactory emf;
  private EntityManager em;
  private EntityTransaction tx;

  // ======================================
  // =           Public Methods           =
  // ======================================

  public BookService(ApplicationContext context){
    this.emf = context.getBean(EntityManagerFactory.class);

    this.em = emf.createEntityManager();
    this.tx = em.getTransaction();
  }

  public Book createBook(Long id, String title, String description, Float unitCost, String isbn, Integer nbOfPage) {
    Book book = new Book();
    book.setId(id);
    book.setTitle(title);
    book.setDescription(description);
    book.setUnitCost(unitCost);
    book.setIsbn(isbn);
    book.setNbOfPage(nbOfPage);
    tx.begin();
    em.persist(book);
    tx.commit();
    return book;
  }

  public Book raiseUnitCost(Long id, Float raise) {
    Book book = em.find(Book.class, id);
    if (book != null) {
      tx.begin();
      book.setUnitCost(book.getUnitCost() + raise);
      tx.commit();
    }
    return book;
  }

  public void removeBook(Long id) {
    Book book = em.find(Book.class, id);
    if (book != null) {
      tx.begin();
      em.remove(book);
      tx.commit();
    }
  }

  public Book findBook(Long id) {
    return em.find(Book.class, id);
  }
}