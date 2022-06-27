package com.pluralsight.persistence.module05.demo02;

import com.pluralsight.persistence.module05.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

public class QueryService {

  // ======================================
  // =             Attributes             =
  // ======================================

  private EntityManager em;

  // ======================================
  // =            Constructors            =
  // ======================================

  public QueryService(EntityManager em) {
    this.em = em;
  }

  // ======================================
  // =           Public Methods           =
  // ======================================

  public void queryBooks() {

    Query query = em.createQuery("SELECT b FROM Book b WHERE b.unitCost > 29 AND b.nbOfPage < 700");
    List books = query.getResultList();

    for (int i = 0; i < books.size(); i++) {
      Book book = (Book) books.get(i);
      System.out.println(book);
    }
  }

  public void queryBooksTyped() {

    TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b WHERE b.unitCost > 29 AND b.nbOfPage < 700", Book.class);
    List<Book> books = query.getResultList();

    for (Book book : books) {
      System.out.println(book);
    }
  }

  public void queryBooksPosition(Float unitCost, Integer nbOfPage) {

    TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b WHERE b.unitCost > ?1 AND b.nbOfPage < ?2", Book.class);
    query.setParameter(1, unitCost);
    query.setParameter(2, nbOfPage);
    List<Book> books = query.getResultList();

    for (Book book : books) {
      System.out.println(book);
    }
  }

  public void queryBooksName(Float unitCost, Integer nbOfPage) {

    TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b WHERE b.unitCost > :cost AND b.nbOfPage < :pages", Book.class);
    query.setParameter("cost", unitCost);
    query.setParameter("pages", nbOfPage);
    List<Book> books = query.getResultList();

    for (Book book : books) {
      System.out.println(book);
    }
  }

  public void queryFull(Float unitCost, Integer nbOfPage, LocalDate publicationDate) {

    TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b WHERE b.unitCost > :cost AND b.nbOfPage < :pages OR b.publicationDate < :pubDate", Book.class);
    query.setParameter("cost", unitCost);
    query.setParameter("pages", nbOfPage);
    query.setParameter("pubDate", publicationDate);
    List<Book> books = query.getResultList();

    for (Book book : books) {
      System.out.println(book);
    }
  }

  public void queryDynamic(Float unitCost, Integer nbOfPage, LocalDate publicationDate) {
    boolean hasPages = true;
    boolean hasDate = true;

    String statement = "SELECT b FROM Book b WHERE b.unitCost > :cost ";
    if (hasPages)
      statement += "AND b.nbOfPage < :pages ";
    if (hasDate)
      statement += "OR b.publicationDate < :pubDate";

    TypedQuery<Book> query = em.createQuery(statement, Book.class);
    query.setParameter("cost", unitCost);
    query.setParameter("pages", nbOfPage);
    query.setParameter("pubDate", publicationDate);
    List<Book> books = query.getResultList();

    for (Book book : books) {
      System.out.println(book);
    }
  }

  public void queryBooksDate(LocalDate publicationDate) {

    TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b WHERE b.publicationDate < :pubDate", Book.class);
    query.setParameter("pubDate", publicationDate);
    List<Book> books = query.getResultList();

    for (Book book : books) {
      System.out.println(book);
    }
  }

  public void paginateBooks(Float unitCost, Integer nbOfPage) {

    TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b WHERE b.unitCost > :cost AND b.nbOfPage < :pages", Book.class);
    query.setParameter("cost", unitCost);
    query.setParameter("pages", nbOfPage);
    query.setMaxResults(2);
    List<Book> books = query.getResultList();

    for (Book book : books) {
      System.out.println(book);
    }
  }
}