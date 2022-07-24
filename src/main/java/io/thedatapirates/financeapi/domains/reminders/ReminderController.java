package io.thedatapirates.financeapi.domains.reminders;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.thedatapirates.financeapi.constants.Paths;
import io.thedatapirates.financeapi.constants.StringConstants;
import io.thedatapirates.financeapi.domains.frequencies.Frequency;
import io.thedatapirates.financeapi.domains.frequencies.FrequencyDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.http.HttpHeaders.AUTHORIZATION;

/**
 * Controller for reminder endpoints
 */
@RestController
@RequestMapping(value = Paths.REMINDER_PATH)
public class ReminderController {

    private final Logger logger = LogManager.getLogger(ReminderController.class);

    @Autowired
    private ReminderService reminderService;

    /**
     * Gets all reminder related to a customer through a bearer token
     *
     * @param token token to get username of customer from
     * @return all reminders of a given customer
     */
    @GetMapping
    public ResponseEntity<List<ResponseReminderDTO>> getReminderByCustomer(
            @RequestHeader(AUTHORIZATION) String token
    ) {
        logger.info(StringConstants.LOG_GET_REMINDER_CUSTOMER);

        List<Reminder> reminderList = reminderService.getReminderByCustomer(token);

        List<ResponseReminderDTO> reminderDTOList = reminderList
                .stream()
                .map(this::mapReminderToDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(reminderDTOList, HttpStatus.OK);
    }

    /**
     * Creates a reminder for a customer from bearer token
     *
     * @param token       token to get a customer from
     * @param reminderDTO reminder to create
     * @return newly created reminder
     */
    @PostMapping
    public ResponseEntity<ResponseReminderDTO> createReminderForCustomer(
            @RequestHeader(AUTHORIZATION) String token, @Valid @RequestBody RequestReminderDTO reminderDTO
    ) {
        logger.info(StringConstants.LOG_CREATE_REMINDER_CUSTOMER);

        ObjectMapper mapper = new ObjectMapper();

        Reminder reminder = mapper.convertValue(reminderDTO, Reminder.class);

        Reminder newReminder = reminderService.createReminderForCustomer(token, reminderDTO.getFrequencyId(), reminder);

        ResponseReminderDTO newReminderDTO = mapReminderToDTO(newReminder);

        return new ResponseEntity<>(newReminderDTO, HttpStatus.CREATED);
    }

    /**
     * Updates an existing reminder from a customer bearer token
     *
     * @param token       token to get customer from
     * @param reminderId  reminder id for reminder to update
     * @param reminderDTO updated reminder
     * @return newly updated reminder
     */
    @PutMapping(Paths.REMINDER_ID)
    public ResponseEntity<ResponseReminderDTO> updateReminderForCustomer(
            @RequestHeader(AUTHORIZATION) String token,
            @PathVariable Long reminderId,
            @Valid @RequestBody RequestReminderDTO reminderDTO
    ) {
        logger.info(StringConstants.LOG_UPDATE_REMINDER_CUSTOMER);

        ObjectMapper mapper = new ObjectMapper();

        Reminder reminder = mapper.convertValue(reminderDTO, Reminder.class);

        Reminder updatedReminder = reminderService.updateReminderForCustomer(token, reminderDTO.getFrequencyId(), reminderId, reminder);

        ResponseReminderDTO updatedReminderDTO = mapReminderToDTO(updatedReminder);

        return new ResponseEntity<>(updatedReminderDTO, HttpStatus.OK);
    }

    /**
     * Deletes a reminder from a user with a specified id
     *
     * @param token      token to get user from
     * @param reminderId reminder id to get category
     * @return no content
     */
    @DeleteMapping(Paths.REMINDER_ID)
    public ResponseEntity<?> deleteReminderForCustomer(
            @RequestHeader(AUTHORIZATION) String token, @PathVariable Long reminderId
    ) {
        logger.info(StringConstants.LOG_DELETE_REMINDER_CUSTOMER);

        reminderService.deleteReminderForCustomer(token, reminderId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Maps a reminder object to a response reminder DTO object
     *
     * @param reminder reminder to convert
     * @return newly created response reminder DTO
     */
    private ResponseReminderDTO mapReminderToDTO(Reminder reminder) {
        ObjectMapper mapper = new ObjectMapper();
        ResponseReminderDTO responseReminderDTO = new ResponseReminderDTO();

        responseReminderDTO.setId(reminder.getId());
        responseReminderDTO.setDateCreated(reminder.getDateCreated());
        responseReminderDTO.setDateUpdated(reminder.getDateUpdated());
        responseReminderDTO.setName(reminder.getName());
        responseReminderDTO.setDescription(reminder.getDescription());
        responseReminderDTO.setReminderTime(reminder.getReminderTime());
        responseReminderDTO.setFrequency(mapper.convertValue(reminder.getFrequency(), FrequencyDTO.class));

        return responseReminderDTO;
    }
}
