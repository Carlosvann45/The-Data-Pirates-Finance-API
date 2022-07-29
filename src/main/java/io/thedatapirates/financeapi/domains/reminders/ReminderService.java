package io.thedatapirates.financeapi.domains.reminders;

import java.util.List;

/**
 * Interface class provides abstraction layer for reminder service
 */
public interface ReminderService {

    List<Reminder> getReminderByCustomer(String token);

    Reminder createReminderForCustomer(String token, Long frequencyId, Long expenseId,
                                       Reminder newReminder);

    Reminder updateReminderForCustomer(String token, Long frequencyId, Long reminderId,
                                       Long expenseId, Reminder updatedReminder);

    void deleteReminderForCustomer(String token, Long reminderId);
}
