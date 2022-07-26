package io.thedatapirates.financeapi.domains.investments;

import java.util.List;

/**
 * Interface class provides abstraction layer for investment service
 */
public interface InvestmentService {

  List<Investment> getInvestmentsByCustomer(String token);

  Investment createInvestmentForCustomer(String token, Investment newInvestment);

  Investment updateInvestmentForCustomer(String token, Long investmentId,
      Investment updatedInvestment);

  void deleteInvestmentForCustomer(String token, Long investmentId);
}
