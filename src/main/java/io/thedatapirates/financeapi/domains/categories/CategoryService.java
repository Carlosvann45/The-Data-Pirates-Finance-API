package io.thedatapirates.financeapi.domains.categories;

import java.util.List;

/**
 * Interface class provides abstraction layer for category service
 */
public interface CategoryService {

    List<Category> getCategoriesByCustomer();
}
