package io.thedatapirates.financeapi.domains.categories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface to access and query the database
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByCustomerId(Long customerId);

    Category findCategoryByName(String name);

    Category findCategoryById(Long id);
}
