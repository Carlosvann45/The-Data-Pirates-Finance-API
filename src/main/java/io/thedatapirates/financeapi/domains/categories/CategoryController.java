package io.thedatapirates.financeapi.domains.categories;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.thedatapirates.financeapi.constants.Paths;
import io.thedatapirates.financeapi.constants.StringConstants;
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
    public ResponseEntity<List<CategoryDTO>> getCategoriesByCustomer(@RequestHeader(AUTHORIZATION) String token) {
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
    public ResponseEntity<CategoryDTO> createCategoryForCustomer(@RequestHeader(AUTHORIZATION) String token, @Valid@RequestBody CategoryDTO categoryDTO) {
        logger.info(StringConstants.LOG_CREATE_CATEGORIES_CUSTOMER);

        ObjectMapper mapper = new ObjectMapper();
        Category category = mapper.convertValue(categoryDTO, Category.class);

        Category newCategory = categoryService.createCategoryForCustomer(token, category);

        CategoryDTO newCategoryDTO = mapper.convertValue(newCategory, CategoryDTO.class);

        return new ResponseEntity<>(newCategoryDTO, HttpStatus.CREATED);
    }
}
