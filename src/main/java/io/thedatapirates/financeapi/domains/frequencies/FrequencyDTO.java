package io.thedatapirates.financeapi.domains.frequencies;


import java.util.Date;

/**
 * An object to represent a Data Transfer Object for a frequency
 */
public class FrequencyDTO {

    private Long id;

    private Date dateCreated;

    private Date dateUpdated;

    private String name;

    public FrequencyDTO() {
    }

    public FrequencyDTO(String name) {
        this.name = name;
    }

    public FrequencyDTO(Long id, Date dateCreated, Date dateUpdated, String name) {
        this.id = id;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
