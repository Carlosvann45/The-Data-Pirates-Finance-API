package io.thedatapirates.financeapi.domains.prioritylevels;

import io.thedatapirates.financeapi.constants.StringConstants;
import io.thedatapirates.financeapi.exceptions.NotFound;
import io.thedatapirates.financeapi.exceptions.ServerUnavailable;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 * A class to implement all methods from the priority level service interface
 */
@Service
public class PriorityLevelServiceImpl implements PriorityLevelService {

  private final Logger logger = LogManager.getLogger(PriorityLevelServiceImpl.class);

  @Autowired
  private PriorityLevelRepository priorityLevelRepository;

  /**
   * Fetches all priority levels from the repository
   *
   * @return all priority levels
   */
  @Override
  public List<PriorityLevel> getPriorityLevels() {
    try {
      return priorityLevelRepository.findAll();
    } catch (DataAccessException e) {
      logger.error(e.getMessage());

      throw new ServerUnavailable(e.getMessage());
    }
  }

  /**
   * Finds a given priority level with an id if it exists
   *
   * @param priorityLevelId priority level id to search for
   * @return priority level with given id
   */
  @Override
  public PriorityLevel priorityLevelService(Long priorityLevelId) {
    PriorityLevel existingPriorityLevel;

    try {
      existingPriorityLevel = priorityLevelRepository.findPriorityLevelById(priorityLevelId);
    } catch (DataAccessException e) {
      logger.error(e.getMessage());

      throw new ServerUnavailable(e.getMessage());
    }

    if (existingPriorityLevel == null) {
      throw new NotFound(StringConstants.PRIORITY_LEVEL_NOT_FOUND);
    }

    return existingPriorityLevel;
  }
}
