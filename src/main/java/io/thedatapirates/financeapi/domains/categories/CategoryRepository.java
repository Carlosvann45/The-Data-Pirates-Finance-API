package io.thedatapirates.financeapi.domains.categories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface to access and query the database
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByCustomerId(Long customerId);

    Category findCategoryByName(String name);

    Category findCategoryById(Long id);
}
