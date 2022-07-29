package io.thedatapirates.financeapi.domains.reminders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface to access and query the database
 */
@Repository
public interface ReminderRepository extends JpaRepository<Reminder, Long> {

    List<Reminder> findAllByCustomerId(Long customerId);

    Reminder findReminderByName(String name);

    Reminder findReminderById(Long id);
}
