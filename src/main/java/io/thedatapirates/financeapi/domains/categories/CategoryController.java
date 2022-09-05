package io.thedatapirates.financeapi.domains.categories;

import io.thedatapirates.financeapi.constants.Paths;
import io.thedatapirates.financeapi.constants.StringConstants;
import io.thedatapirates.financeapi.utility.MapperExtensions;
import lombok.experimental.ExtensionMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.http.HttpHeaders.AUTHORIZATION;

/**
 * Controller for category endpoints
 */
@RestController
@RequestMapping(value = Paths.CATEGORY_PATH)
@ExtensionMethod(MapperExtensions.class)
public class CategoryController {

    private final Logger logger = LogManager.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    /**
     * Gets all categories
     *
     * @return all categories
     */
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getCategoriesByCustomer() {
        logger.info(StringConstants.LOG_GET_CATEGORIES_CUSTOMER);

        List<Category> categories = categoryService.getCategoriesByCustomer();

        List<CategoryDTO> categoryDTOs = categories
                .stream()
                .map(category -> category.mapCategoryToDTO())
                .collect(Collectors.toList());

        return new ResponseEntity<>(categoryDTOs, HttpStatus.OK);
    }
}
