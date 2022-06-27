package com.pluralsight.persistence.module06.demo01;

import com.pluralsight.persistence.module06.Author;

import javax.persistence.EntityManager;

public class AuthorService {

  // ======================================
  // =             Attributes             =
  // ======================================

  private EntityManager em;

  public AuthorService(EntityManager em) {
    this.em = em;
  }

  public Long createAuthor(Author author) {
    em.persist(author);
    return author.getId();
  }

  public void removeAuthor(Author author) {
    em.remove(em.merge(author));
  }

  public Author findAuthor(Long id) {
    return em.find(Author.class, id);
  }
}