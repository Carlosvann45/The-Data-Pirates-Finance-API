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
     * Gets all categories related to a customer through a bearer token
     *
     * @param token token to get username of customer from
     * @return all categories of a given customer
     */
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getCategoriesByCustomer(
            @RequestHeader(AUTHORIZATION) String token
    ) {
        logger.info(StringConstants.LOG_GET_CATEGORIES_CUSTOMER);

        List<Category> categories = categoryService.getCategoriesByCustomer(token);

        List<CategoryDTO> categoryDTOs = categories
                .stream()
                .map(category -> category.mapCategoryToDTO())
                .collect(Collectors.toList());

        return new ResponseEntity<>(categoryDTOs, HttpStatus.OK);
    }

    /**
     * Creates a category for a customer from bearer token
     *
     * @param token       token to get a customer from
     * @param categoryDTO category to create
     * @return newly created category
     */
    @PostMapping
    public ResponseEntity<CategoryDTO> createCategoryForCustomer(
            @RequestHeader(AUTHORIZATION) String token, @Valid @RequestBody CategoryDTO categoryDTO
    ) {
        logger.info(StringConstants.LOG_CREATE_CATEGORIES_CUSTOMER);

        Category category = categoryDTO.mapDTOToCategory();

        Category newCategory = categoryService.createCategoryForCustomer(token, category);

        CategoryDTO newCategoryDTO = newCategory.mapCategoryToDTO();

        return new ResponseEntity<>(newCategoryDTO, HttpStatus.CREATED);
    }

    /**
     * Updates an existing category from a customer bearer token
     *
     * @param token       token to get customer from
     * @param categoryId  category id for category to update
     * @param categoryDTO updated category
     * @return newly updated category
     */
    @PutMapping(Paths.CATEGORY_ID)
    public ResponseEntity<CategoryDTO> updateCategoryForCustomer(
            @RequestHeader(AUTHORIZATION) String token,
            @PathVariable Long categoryId,
            @Valid @RequestBody CategoryDTO categoryDTO
    ) {
        logger.info(StringConstants.LOG_UPDATE_CATEGORIES_CUSTOMER);

        Category category = categoryDTO.mapDTOToCategory();

        Category updatedCategory = categoryService.updateCategoryForCustomer(token, categoryId,
                category);

        CategoryDTO updatedCategoryDTO = updatedCategory.mapCategoryToDTO();

        return new ResponseEntity<>(updatedCategoryDTO, HttpStatus.OK);
    }

    /**
     * Deletes a category from a user with a specified id
     *
     * @param token      token to get user from
     * @param categoryId category id to get category
     * @return no content
     */
    @DeleteMapping(Paths.CATEGORY_ID)
    public ResponseEntity<?> deleteCategoryForCustomer(
            @RequestHeader(AUTHORIZATION) String token, @PathVariable Long categoryId
    ) {
        logger.info(StringConstants.LOG_DELETE_CATEGORIES_CUSTOMER);

        categoryService.deleteCategoryForCustomer(token, categoryId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
