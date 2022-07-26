package io.thedatapirates.financeapi.domains.entity;

import java.util.Date;
import java.util.Objects;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * An abstract class to represent a base entity in the database
 */
@MappedSuperclass
public abstract class BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Date dateCreated;

  private Date dateUpdated;

  public BaseEntity() {

  }

  public BaseEntity(Date dateCreated, Date dateUpdated) {
    this.dateCreated = dateCreated;
    this.dateUpdated = dateUpdated;
  }

  public BaseEntity(Long id, Date dateCreated, Date dateUpdated) {
    this.id = id;
    this.dateCreated = dateCreated;
    this.dateUpdated = dateUpdated;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Date getDateCreated() {
    return dateCreated;
  }

  public void setDateCreated(Date dateCreated) {
    this.dateCreated = dateCreated;
  }

  public Date getDateUpdated() {
    return dateUpdated;
  }

  public void setDateUpdated(Date dateUpdated) {
    this.dateUpdated = dateUpdated;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BaseEntity that = (BaseEntity) o;
    return Objects.equals(id, that.id) && Objects.equals(dateCreated, that.dateCreated)
        && Objects.equals(dateUpdated, that.dateUpdated);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, dateCreated, dateUpdated);
  }

  @Override
  public String toString() {
    return "BaseEntity{" +
        "id=" + id +
        ", dateCreated=" + dateCreated +
        ", dateUpdated=" + dateUpdated +
        '}';
  }
}
