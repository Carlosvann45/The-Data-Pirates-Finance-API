package io.thedatapirates.financeapi.domains.categories;

import java.util.List;

/**
 * Interface class provides abstraction layer for category service
 */
public interface CategoryService {
    List<Category> getCategoriesByCustomer(String token);

    Category createCategoryForCustomer(String token, Category newCategory);

    Category updateCategoryForCustomer(String token, Long categoryId, Category updatedCategory);
}
