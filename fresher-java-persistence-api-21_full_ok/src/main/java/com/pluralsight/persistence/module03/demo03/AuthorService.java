package com.pluralsight.persistence.module03.demo03;

import com.pluralsight.persistence.module03.model.Author;

import javax.persistence.EntityManager;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */
public class AuthorService {

  // ======================================
  // =             Attributes             =
  // ======================================

  private EntityManager em;

  // ======================================
  // =            Constructors            =
  // ======================================

  public AuthorService(EntityManager em) {
    this.em = em;
  }

  // ======================================
  // =           Public Methods           =
  // ======================================

  public Author createAuthor(Author book) {
    em.persist(book);
    return book;
  }

  public void removeAuthor(Author book) {
    em.remove(em.merge(book));
  }

  public Author findAuthor(Long id) {
    return em.find(Author.class, id);
  }
}