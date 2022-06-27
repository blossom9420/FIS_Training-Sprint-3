package com.pluralsight.persistence.module05.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@NamedQuery(name = Book.FIND_ALL, query = "select b from Book b")
@NamedQuery(name = "ExpensiveBooks", query = "SELECT b FROM Book b WHERE b.unitCost > 29 AND b.nbOfPage < 700")
@NamedQuery(name = "ParamPosBooks", query = "SELECT b FROM Book b WHERE b.unitCost > ?1 AND b.nbOfPage < ?2")
@NamedQuery(name = "ParamNameBooks", query = "SELECT b FROM Book b WHERE b.unitCost > :cost AND b.nbOfPage < :pages")
@NamedQuery(name = "PublishedBooks", query = "SELECT b FROM Book b WHERE b.publicationDate < :pubDate")
public class Book extends Item implements Serializable {
  @Column(length = 15)
  private String isbn;

  @Column(name = "nb_of_pages")
  private Integer nbOfPage;

  @Column(name = "publication_date")
  private LocalDate publicationDate;

  @Enumerated(EnumType.STRING)
  private Language language;

  @OneToMany
  @JoinTable(name = "book_author",
          joinColumns = @JoinColumn(name = "book_fk"),
          inverseJoinColumns = @JoinColumn(name = "author_fk"))
  private Set<Author> authors = new HashSet<>();

  @ManyToOne
  @JoinColumn(name = "publisher_pk")
  private Publisher publisher;

  // ======================================
  // =              Constant              =
  // ======================================

  public static final String FIND_ALL = "Book.findAll";

  // ======================================
  // =          Getters & Setters         =
  // ======================================

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public Integer getNbOfPage() {
    return nbOfPage;
  }

  public void setNbOfPage(Integer nbOfPage) {
    this.nbOfPage = nbOfPage;
  }

  public LocalDate getPublicationDate() {
    return publicationDate;
  }

  public void setPublicationDate(LocalDate publicationDate) {
    this.publicationDate = publicationDate;
  }

  public Language getLanguage() {
    return language;
  }

  public void setLanguage(Language language) {
    this.language = language;
  }

  public Set<Author> getAuthors() {
    return authors;
  }

  public void setAuthors(Set<Author> authors) {
    this.authors = authors;
  }

  public Publisher getPublisher() {
    return publisher;
  }

  public void setPublisher(Publisher publisher) {
    this.publisher = publisher;
  }

  // ======================================
  // =    hashcode, equals & toString     =
  // ======================================

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;

    Book book = (Book) o;

    if (!isbn.equals(book.isbn)) return false;
    if (language != book.language) return false;
    if (nbOfPage != null ? !nbOfPage.equals(book.nbOfPage) : book.nbOfPage != null) return false;
    if (publicationDate != null ? !publicationDate.equals(book.publicationDate) : book.publicationDate != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + isbn.hashCode();
    result = 31 * result + (nbOfPage != null ? nbOfPage.hashCode() : 0);
    result = 31 * result + (publicationDate != null ? publicationDate.hashCode() : 0);
    result = 31 * result + (language != null ? language.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Book{");
    sb.append("title='").append(title).append('\'');
    sb.append(", unitCost=").append(unitCost);
    sb.append(", isbn=").append(isbn);
    sb.append(", nbOfPage=").append(nbOfPage);
    sb.append(", language=").append(language);
    sb.append(", publicationDate=").append(publicationDate);
    sb.append('}');
    return sb.toString();
  }
}