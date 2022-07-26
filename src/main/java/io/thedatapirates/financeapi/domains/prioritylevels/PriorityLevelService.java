package io.thedatapirates.financeapi.domains.prioritylevels;

import java.util.List;

/**
 * Interface class provides abstraction layer for priority level service
 */
public interface PriorityLevelService {

  List<PriorityLevel> getPriorityLevels();

  PriorityLevel priorityLevelService(Long priorityLevelId);
}
