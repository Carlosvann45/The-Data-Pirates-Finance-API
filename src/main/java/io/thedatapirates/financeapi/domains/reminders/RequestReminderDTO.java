package io.thedatapirates.financeapi.domains.reminders;

import com.google.api.client.util.DateTime;
import io.thedatapirates.financeapi.constants.StringConstants;
import io.thedatapirates.financeapi.domains.entity.BaseEntityDTO;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Time;
import java.util.Date;

/**
 * An object to represent a request Data Transfer Object for reminders
 */
public class RequestReminderDTO extends BaseEntityDTO {

    @NotBlank(message = StringConstants.NAME_REQUIRED)
    @Size(min = 3, message = StringConstants.NAME_MIN)
    private String name;

    @NotBlank(message = StringConstants.DESCRIPTION_REQUIRED)
    @Size(min = 3, message = StringConstants.DESCRIPTION_MIN)
    private String description;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date reminderTime;

    @Range(min = 1, max = 4)
    private Long frequencyId;

    public RequestReminderDTO() {
    }

    public RequestReminderDTO(Date dateCreated, Date dateUpdated, String name, String description, Date reminderTime, Long frequencyId) {
        super(dateCreated, dateUpdated);
        this.name = name;
        this.description = description;
        this.reminderTime = reminderTime;
        this.frequencyId = frequencyId;
    }

    public RequestReminderDTO(Long id, Date dateCreated, Date dateUpdated, String name, String description, Date reminderTime, Long frequencyId) {
        super(id, dateCreated, dateUpdated);
        this.name = name;
        this.description = description;
        this.reminderTime = reminderTime;
        this.frequencyId = frequencyId;
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

    public Long getFrequencyId() {
        return frequencyId;
    }

    public void setFrequencyId(Long frequencyId) {
        this.frequencyId = frequencyId;
    }
}
