package io.thedatapirates.financeapi.domains.entity;

import java.util.Date;

/**
 * An abstract class to represent a Data Transfer Object(DTO) for other DTOs in the API
 */
public abstract class BaseEntityDTO {

    private Long id;

    private Date dateCreated;

    private Date dateUpdated;

    public BaseEntityDTO() {

    }

    public BaseEntityDTO(Date dateCreated, Date dateUpdated) {
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
    }

    public BaseEntityDTO(Long id, Date dateCreated, Date dateUpdated) {
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
}
