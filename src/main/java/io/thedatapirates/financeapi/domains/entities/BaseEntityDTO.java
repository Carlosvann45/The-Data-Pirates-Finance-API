package io.thedatapirates.financeapi.domains.entities;

import java.time.LocalDateTime;

/**
 * An abstract class to represent a Data Transfer Object(DTO) for other DTOs in the API
 */
public abstract class BaseEntityDTO {

    private Long id;

    private LocalDateTime dateCreated;

    private LocalDateTime dateUpdated;

    public BaseEntityDTO() {

    }

    public BaseEntityDTO(LocalDateTime dateCreated, LocalDateTime dateUpdated) {
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
    }

    public BaseEntityDTO(Long id, LocalDateTime dateCreated, LocalDateTime dateUpdated) {
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

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDateTime getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(LocalDateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
    }
}
