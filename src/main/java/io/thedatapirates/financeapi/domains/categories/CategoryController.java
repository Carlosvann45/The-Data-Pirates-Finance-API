package io.thedatapirates.financeapi.domains.categories;

import static org.apache.http.HttpHeaders.AUTHORIZATION;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.thedatapirates.financeapi.constants.Paths;
import io.thedatapirates.financeapi.constants.StringConstants;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for category endpoints
 */
@RestController
@RequestMapping(value = Paths.CATEGORY_PATH)
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

        ObjectMapper mapper = new ObjectMapper();

        List<Category> categories = categoryService.getCategoriesByCustomer(token);

        List<CategoryDTO> categoryDTOs = categories
                .stream()
                .map(category -> mapper.convertValue(category, CategoryDTO.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(categoryDTOs, HttpStatus.OK);
    }

    /**
     * Creates a category for a customer from bearer token
     *
     * @param token token to get a customer from
     * @param categoryDTO category to create
     * @return newly created category
     */
    @PostMapping
    public ResponseEntity<CategoryDTO> createCategoryForCustomer(
        @RequestHeader(AUTHORIZATION) String token, @Valid@RequestBody CategoryDTO categoryDTO
    ) {
        logger.info(StringConstants.LOG_CREATE_CATEGORIES_CUSTOMER);

        ObjectMapper mapper = new ObjectMapper();

        Category category = mapper.convertValue(categoryDTO, Category.class);

        Category newCategory = categoryService.createCategoryForCustomer(token, category);

        CategoryDTO newCategoryDTO = mapper.convertValue(newCategory, CategoryDTO.class);

        return new ResponseEntity<>(newCategoryDTO, HttpStatus.CREATED);
    }

    /**
     * Updates an existing category from a customer bearer token
     *
     * @param token token to get customer from
     * @param categoryId category id for category to update
     * @param categoryDTO updated category
     * @return newly updated category
     */
    @PutMapping(Paths.CATEGORY_ID)
    public ResponseEntity<CategoryDTO> updateCategoryForCustomer(
        @RequestHeader(AUTHORIZATION) String token,
        @RequestParam Long categoryId,
        @Valid@RequestBody CategoryDTO categoryDTO
    ) {
        logger.info(StringConstants.LOG_UPDATE_CATEGORIES_CUSTOMER);

        ObjectMapper mapper = new ObjectMapper();

        Category category = mapper.convertValue(categoryDTO, Category.class);

        Category updatedCategory = categoryService.updateCategoryForCustomer(token, categoryId, category);

        CategoryDTO updatedCategoryDTO = mapper.convertValue(updatedCategory, CategoryDTO.class);

        return new ResponseEntity<>(updatedCategoryDTO, HttpStatus.CREATED);

    }
}
