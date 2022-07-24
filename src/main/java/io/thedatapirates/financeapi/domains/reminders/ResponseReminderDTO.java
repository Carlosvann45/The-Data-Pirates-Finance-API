package io.thedatapirates.financeapi.domains.reminders;

import io.thedatapirates.financeapi.domains.entity.BaseEntityDTO;
import io.thedatapirates.financeapi.domains.frequencies.FrequencyDTO;

import java.util.Date;

/**
 * An object to represent a response Data Transfer Object for reminders
 */
public class ResponseReminderDTO extends BaseEntityDTO {

    private String name;

    private String description;

    private Date reminderTime;

    private FrequencyDTO frequency;

    public ResponseReminderDTO() {
    }

    public ResponseReminderDTO(Date dateCreated, Date dateUpdated, String name, String description, Date reminderTime, FrequencyDTO frequency) {
        super(dateCreated, dateUpdated);
        this.name = name;
        this.description = description;
        this.reminderTime = reminderTime;
        this.frequency = frequency;
    }

    public ResponseReminderDTO(Long id, Date dateCreated, Date dateUpdated, String name, String description, Date reminderTime, FrequencyDTO frequency) {
        super(id, dateCreated, dateUpdated);
        this.name = name;
        this.description = description;
        this.reminderTime = reminderTime;
        this.frequency = frequency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(Date reminderTime) {
        this.reminderTime = reminderTime;
    }

    public FrequencyDTO getFrequency() {
        return frequency;
    }

    public void setFrequency(FrequencyDTO frequency) {
        this.frequency = frequency;
    }
}
