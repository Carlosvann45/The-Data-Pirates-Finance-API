package io.thedatapirates.financeapi.domains.prioritylevels;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.thedatapirates.financeapi.constants.Paths;
import io.thedatapirates.financeapi.constants.StringConstants;
import io.thedatapirates.financeapi.utility.MapperExtensions;
import java.util.List;
import java.util.stream.Collectors;
import lombok.experimental.ExtensionMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for priority level endpoints
 */
@RestController
@RequestMapping(value = Paths.PRIORITY_LEVEL_PATH)
@ExtensionMethod(MapperExtensions.class)
public class PriorityLevelController {

  private final Logger logger = LogManager.getLogger(PriorityLevelController.class);

  @Autowired
  private PriorityLevelService priorityLevelService;

  /**
   * Retrieves all priority levels
   *
   * @return a list of priority levels
   */
  @GetMapping
  public ResponseEntity<List<PriorityLevelDTO>> getPriorityLevels() {
    logger.info(StringConstants.LOG_GET_PRIORITY_LEVELS);

    List<PriorityLevel> priorityLevels = priorityLevelService.getPriorityLevels();

    List<PriorityLevelDTO> priorityLevelDTOS = priorityLevels
        .stream()
        .map(priorityLevel -> priorityLevel.mapPriorityLevelToDTO())
        .collect(Collectors.toList());

    return new ResponseEntity<>(priorityLevelDTOS, HttpStatus.OK);
  }

  /**
   * Get a priority level by a given id
   *
   * @param priorityLevelId priority level id to search for
   * @return priority level with given id
   */
  @GetMapping(Paths.PRIORITY_LEVEL_ID)
  public ResponseEntity<PriorityLevelDTO> priorityLevelService(@PathVariable Long priorityLevelId) {
    logger.info(StringConstants.LOG_GET_PRIORItY_LEVEL_ID);

    ObjectMapper mapper = new ObjectMapper();

    PriorityLevel priorityLevel = priorityLevelService.priorityLevelService(priorityLevelId);

    PriorityLevelDTO priorityLevelDTO = priorityLevel.mapPriorityLevelToDTO();

    return new ResponseEntity<>(priorityLevelDTO, HttpStatus.OK);
  }
}
