package com.pluralsight.persistence.module05.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.time.LocalDate;

@MappedSuperclass //dont generate table
public class Artist {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  protected Long id;

  @Column(name = "first_name", length = 50)
  protected String firstName;

  @Column(name = "last_name", length = 50)
  protected String lastName;

  @Column(length = 5000)
  protected String bio;

  @Column(name = "date_of_birth")
  protected LocalDate dateOfBirth;

  @Transient
  protected Integer age;

  // ======================================
  // =          Getters & Setters         =
  // ======================================

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getBio() {
    return bio;
  }

  public void setBio(String bio) {
    this.bio = bio;
  }

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Artist artist = (Artist) o;

    if (age != null ? !age.equals(artist.age) : artist.age != null) return false;
    if (bio != null ? !bio.equals(artist.bio) : artist.bio != null) return false;
    if (dateOfBirth != null ? !dateOfBirth.equals(artist.dateOfBirth) : artist.dateOfBirth != null) return false;
    if (!firstName.equals(artist.firstName)) return false;
    if (id != null ? !id.equals(artist.id) : artist.id != null) return false;
    if (!lastName.equals(artist.lastName)) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + firstName.hashCode();
    result = 31 * result + lastName.hashCode();
    result = 31 * result + (bio != null ? bio.hashCode() : 0);
    result = 31 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
    result = 31 * result + (age != null ? age.hashCode() : 0);
    return result;
  }
}