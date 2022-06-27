package com.pluralsight.persistence.module05.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@NamedQueries({
        @NamedQuery(name = Musician.FIND_ALL, query = "select m from Musician m"),
})
public class Musician extends Artist implements Serializable {

  @Column(name = "preferred_instrument")
  private String preferredInstrument;

  @ManyToMany
  @JoinTable(name = "musician_cd", joinColumns = @JoinColumn(name = "musician_fk"), inverseJoinColumns = @JoinColumn(name = "cd_fk"))
  private Set<CD> cds = new HashSet<>();


  public static final String FIND_ALL = "Musician.findAll";


  public String getPreferredInstrument() {
    return preferredInstrument;
  }

  public void setPreferredInstrument(String preferredInstrument) {
    this.preferredInstrument = preferredInstrument;
  }

  public Set<CD> getCds() {
    return cds;
  }

  public void setCds(Set<CD> cds) {
    this.cds = cds;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;

    Musician musician = (Musician) o;

    if (preferredInstrument != null ? !preferredInstrument.equals(musician.preferredInstrument) : musician.preferredInstrument != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + (preferredInstrument != null ? preferredInstrument.hashCode() : 0);
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