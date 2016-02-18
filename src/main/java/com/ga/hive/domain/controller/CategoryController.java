package com.ga.hive.domain.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ga.hive.common.ErrorCodes;
import com.ga.hive.domain.util.JsonUtility;
import com.ga.hive.exception.GAException;
import com.ga.hive.persistence.entity.Category;
import com.ga.hive.service.ICategoryService;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {

    private static final Logger LOGGER = Logger.getLogger(UserController.class);

    @Autowired
    ICategoryService categoryService;

    @RequestMapping(value = "/addcategory", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String addCategory(@RequestBody Category category) throws GAException {
        LOGGER.info("addCAtegory controller");
        Boolean registered = categoryService.addCategory(category);
        if (registered) {
            return JsonUtility.getJson(ErrorCodes.GA_TRANSACTION_OK, registered);
        } else
            return JsonUtility.getJson(ErrorCodes.GA_INTERNAL, false);

    }

    @RequestMapping(value = "/getallcategories", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String getAllCategories() throws GAException {
        LOGGER.info("addCAtegory controller");
        try {
            List<Category> categoryList = categoryService.getAllCategory();
            if (categoryList != null) {
                return JsonUtility.getJson(ErrorCodes.GA_TRANSACTION_OK, categoryList);
            } else {
                return JsonUtility.getJson(ErrorCodes.GA_DATA_NOT_FOUND, categoryList);
            }
        } catch (GAException e) {
            LOGGER.error(" " + e);
            return JsonUtility.getJson(e.getCode(), e.getDescription());
        }

    }

    @RequestMapping(value = "/getcategorybyid", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String getCategoryByID(@RequestParam("categoryID") String categoryID) throws GAException {
        LOGGER.info("get categoryByid controller");
        try {
            Category categoryDetail = categoryService.getCategoryByID(categoryID);
            if (categoryDetail != null) {
                return JsonUtility.getJson(ErrorCodes.GA_TRANSACTION_OK, categoryDetail);
            } else {
                return JsonUtility.getJson(ErrorCodes.GA_DATA_NOT_FOUND, categoryDetail);
            }
        } catch (GAException e) {
            LOGGER.error(" " + e);
            return JsonUtility.getJson(e.getCode(), e.getDescription());
        }

    }

    @RequestMapping(value = "/deletecategory", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String deleteCategoryByID(@RequestParam("categoryID") String categoryID) throws GAException {
        LOGGER.info("get categoryByid controller");
        try {
            boolean result = categoryService.deleteCategoryByID(categoryID);
            if (!result) {
                return JsonUtility.getJson(ErrorCodes.GA_DATA_NOT_FOUND, false);
            } else {
                return JsonUtility.getJson(ErrorCodes.GA_TRANSACTION_OK, true);
            }
        } catch (GAException e) {
            LOGGER.error(" " + e);
            return JsonUtility.getJson(e.getCode(), e.getDescription());
        }

    }

    @RequestMapping(value = "/updatecategory", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String updateCategory(@RequestBody Category category,
            @RequestParam("categoryID") String categoryID) throws GAException {
        LOGGER.info("addCAtegory controller");
        Boolean result = categoryService.updateCategoryID(category,categoryID);
        if (result) {
            return JsonUtility.getJson(ErrorCodes.GA_TRANSACTION_OK, "category updated successfully");
        } else
            return JsonUtility.getJson(ErrorCodes.GA_INTERNAL, false);

    }

}
