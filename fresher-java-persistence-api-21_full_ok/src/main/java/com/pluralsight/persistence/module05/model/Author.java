package com.pluralsight.persistence.module05.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NamedQueries({
   @NamedQuery(name = Author.FIND_ALL, query = "select a from Author a"),
})
public class Author extends Artist implements Serializable {
  @Column(name = "preferred_Language")
  @Enumerated
  private Language preferredLanguage;

  public static final String FIND_ALL = "Author.findAll";

  public Language getPreferredLanguage() {
    return preferredLanguage;
  }

  public void setPreferredLanguage(Language preferredLanguage) {
    this.preferredLanguage = preferredLanguage;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;

    Author author = (Author) o;

    if (preferredLanguage != author.preferredLanguage) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + (preferredLanguage != null ? preferredLanguage.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder(firstName);
    sb.append(' ');
    sb.append(lastName);
    return sb.toString();
  }
}