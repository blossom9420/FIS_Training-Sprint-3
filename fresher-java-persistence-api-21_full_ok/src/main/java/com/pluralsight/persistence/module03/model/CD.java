package com.pluralsight.persistence.module03.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class CD {

  // ======================================
  // =             Attributes             =
  // ======================================

  @Id
  @GeneratedValue
  private Long id = null;

  private String title;

  private String description;

  private Float unitCost;

  private Float totalDuration;

  private String genre;

  // ======================================
  // =            Constructors            =
  // ======================================
//  @OneToOne
//  @JoinColumn(name = "musician_id")
//  @JoinTable(name = "cd_musician",
//          joinColumns = @JoinColumn(name = "cd_id"),
//          inverseJoinColumns = @JoinColumn(name = "musician_id")
//  )
//  private Musician musician;
//  public Musician getMusician() {
//    return musician;
//  }
//
//  public void setMusician(Musician musician) {
//    this.musician = musician;
//  }

  @ManyToMany (fetch = FetchType.LAZY)
  @JoinTable(name = "cd_musician",
          joinColumns = @JoinColumn(name = "cd_id"),
          inverseJoinColumns = @JoinColumn(name = "musician_id")
  )
  private Set<Musician> musicians;

  public Set<Musician> getMusicians() {
    return musicians;
  }

  public void setMusicians(Set<Musician> musicians) {
    this.musicians = musicians;
  }

  public CD() {
  }

  public CD(String title, String description, Float unitCost, Float totalDuration, String genre) {
    this.title = title;
    this.description = description;
    this.unitCost = unitCost;
    this.totalDuration = totalDuration;
    this.genre = genre;
  }

  // ======================================
  // =          Getters & Setters         =
  // ======================================

  //@Id //@GeneratedValue
  public Long getId() {return id;}

  public void setId(Long id) {
    this.id = id;
  }

  @Column(length = 100)
  public String getTitle() {return title;}

  public void setTitle(String title) {
    this.title = title;
  }

  @Column(length = 3000)
  public String getDescription() {return description;}

  public void setDescription(String description) {
    this.description = description;
  }

  @Column(name = "unit_cost")
  public Float getUnitCost() {return unitCost;}

  public void setUnitCost(Float unitCost) {
    this.unitCost = unitCost;
  }

  @Column(name = "total_duration")
  public Float getTotalDuration() {return totalDuration;}

  public void setTotalDuration(Float totalDuration) {
    this.totalDuration = totalDuration;
  }

  public String getGenre() {return genre;}

  public void setGenre(String genre) {
    this.genre = genre;
  }

  // ======================================
  // =    hashcode, equals & toString     =
  // ======================================

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("CD{");
    sb.append("id=").append(id);
    sb.append(", title='").append(title).append('\'');
    sb.append(", description='").append(description).append('\'');
    sb.append(", unitCost=").append(unitCost);
    sb.append(", totalDuration=").append(totalDuration);
    sb.append(", genre='").append(genre).append('\'');
    sb.append('}');
    return sb.toString();
  }
}