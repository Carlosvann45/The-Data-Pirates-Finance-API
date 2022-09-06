package io.thedatapirates.financeapi.domains.cashflows;

import io.thedatapirates.financeapi.domains.deposits.Deposit;

import java.util.List;

/**
 * Interface class provides abstraction layer for cash flow service
 */
public interface CashFlowService {

    List<CashFlow> getCashFlowByCustomer(String token);

    CashFlow createCashFlowForCustomer(String token, Long frequencyId, CashFlow newCashFlowItem);

    CashFlow depositCashFlowForCustomer(String token, Long cashFlowId, Deposit deposit);

    CashFlow updateCashFlowForCustomer(String token, Long frequencyId, Long cashFlowId,
                                       CashFlow updatedCashFlowItem);

    void deleteCashFlowForCustomer(String token, Long cashFlowId);
}
