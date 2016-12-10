package com.akash.evm.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import com.akash.evm.enums.CategorytStatusEnum;
import com.akash.evm.exception.BaseException;
import com.akash.evm.model.Category;
import com.akash.evm.service.CategoryService;

@Controller
public class CategoryController {

	private static final Logger LOGGER = LogManager.getLogger(CategoryController.class);

	@Autowired
	private CategoryService categoryService;

	// -------------------Retrieve All
	@RequestMapping(value = "/category/", method = RequestMethod.GET)
	public ResponseEntity<List<Category>> listAll() {
		List<Category> categories = new ArrayList<>();
		try {
			categories = categoryService.findByStatus(CategorytStatusEnum.ACTIVE);

			if (categories.isEmpty()) {
				return new ResponseEntity<List<Category>>(HttpStatus.NO_CONTENT);
			}
		} catch (BaseException e) {
			throw e;
		}
		return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
	}

	// -------------------Retrieve Single

	@RequestMapping(value = "/category/{categoryId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Category> getCategory(@PathVariable("categoryId") Long categoryId) {
		LOGGER.info("Fetching Category with id " + categoryId);
		ResponseEntity<Category> responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		Category category = new Category();
		try {
			category = categoryService.findById(categoryId);
			if (categoryId == null) {
				LOGGER.info("Category with id " + categoryId + " not found");
				responseEntity = new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
			} else {
				responseEntity = new ResponseEntity<Category>(category, HttpStatus.OK);
			}

		} catch (BaseException e) {
			throw e;
		}

		return responseEntity;

	}

	// -------------------Create a Category

	@RequestMapping(value = "/category/", method = RequestMethod.POST)
	public ResponseEntity<Void> createCategory(@RequestBody Category category, UriComponentsBuilder ucBuilder) {
		LOGGER.info("Creating Barnd " + category.getCategoryName());
		ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		try {
			if (null != categoryService.findByName(category.getCategoryName())) {
				LOGGER.info("A Category with name " + category.getCategoryName() + " already exist");
				return new ResponseEntity<Void>(HttpStatus.CONFLICT);
			}
			category = categoryService.saveUpdate(category);

			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(
					ucBuilder.path("/category/{categoryId}").buildAndExpand(category.getCategoryId()).toUri());
			responseEntity = new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		} catch (Exception e) {
			throw e;
		}
		return responseEntity;

	}

	// ------------------- Update a Category

	@RequestMapping(value = "/category/{categoryId}", method = RequestMethod.PUT)
	public ResponseEntity<Category> updateUser(@PathVariable("categoryId") Long categoryId,
			@RequestBody Category category) {
		LOGGER.info("Updating Barnd " + category.getCategoryName());
		ResponseEntity<Category> responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		try {
			categoryService.saveUpdate(category);
			responseEntity = new ResponseEntity<Category>(category, HttpStatus.OK);
		} catch (BaseException e) {
			throw e;
		}
		return responseEntity;
	}

	// ------------------- Delete a User

	@RequestMapping(value = "/category/{categoryId}", method = RequestMethod.DELETE)
	public ResponseEntity<Category> deleteUser(@PathVariable("categoryId") Long categoryId) {
		LOGGER.info("Fetching & Deleting Category with id " + categoryId);
		ResponseEntity<Category> responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		Category category;
		try {
			category = categoryService.findById(categoryId);
			if (category == null) {
				LOGGER.info("Unable to delete. Category with id " + categoryId + " not found");
				responseEntity = new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
			} else {
				category.setStatus(CategorytStatusEnum.DELETED);
				categoryService.saveUpdate(category);
				responseEntity = new ResponseEntity<Category>(HttpStatus.NO_CONTENT);
			}

		} catch (BaseException e) {
			throw e;
		}
		return responseEntity;
	}

}
