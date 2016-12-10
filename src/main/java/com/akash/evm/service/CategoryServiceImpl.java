package com.akash.evm.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akash.evm.enums.CategorytStatusEnum;
import com.akash.evm.exception.BaseException;
import com.akash.evm.model.Category;
import com.akash.evm.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	private static final Logger LOGGER = LogManager.getLogger(CategoryServiceImpl.class);

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Category saveUpdate(Category category) throws BaseException {
		try {
			category = categoryRepository.save(category);
		} catch (Exception exception) {
			LOGGER.error("An exception occured in saving the category >>" + category.getCategoryName(), exception);
			String rootCuase = (null != exception.getCause() ? exception.getCause().getMessage()
					: "Unknown error occured");
			throw new BaseException("An exception occured in saving the category >>" + category.getCategoryName(),
					rootCuase, "Please correct the input data and retry.", "", exception);
		}
		return category;
	}

	@Override
	public Category findById(Long categoryId) throws BaseException {
		Category category;
		try {
			category = categoryRepository.findOne(categoryId);
		} catch (Exception exception) {
			LOGGER.error("An exception occured in finding the category with id " + categoryId, exception);
			String rootCuase = (null != exception.getCause() ? exception.getCause().getMessage()
					: "Unknown error occured");
			throw new BaseException("An exception occured in finding the category with id " + categoryId, rootCuase, "",
					"Please provide a valid Id", exception);
		}
		return category;
	}

	@Override
	public Category findByName(String name) throws BaseException {
		Category category;
		try {
			category = categoryRepository.findByCategoryNameIgnoreCase(name);
		} catch (Exception exception) {
			LOGGER.error("An exception occured in finding the category with name " + name, exception);
			String rootCuase = (null != exception.getCause() ? exception.getCause().getMessage()
					: "Unknown error occured");
			throw new BaseException("An exception occured in finding the category with name " + name, rootCuase, "",
					"Please provide a valid name", exception);
		}
		return category;
	}

	@Override
	public List<Category> findByStatus(CategorytStatusEnum status) throws BaseException {
		List<Category> categorys;
		try {
			categorys = categoryRepository.findByStatus(status);
		} catch (Exception exception) {
			LOGGER.error("An exception occured in finding the category with status " + status.getStatus(), exception);
			String rootCuase = (null != exception.getCause() ? exception.getCause().getMessage()
					: "Unknown error occured");
			throw new BaseException("An exception occured in finding the category with status " + status.getStatus(),
					rootCuase, "", "Please correct the input data and retry.", exception);
		}
		return categorys;
	}

	@Override
	public List<Category> findAll() throws BaseException {
		List<Category> categorys;
		try {
			categorys = categoryRepository.findAll();
		} catch (Exception exception) {
			LOGGER.error("An exception occured in finding the category ", exception);
			String rootCuase = (null != exception.getCause() ? exception.getCause().getMessage()
					: "Unknown error occured");
			throw new BaseException("An exception occured in finding the category ", rootCuase, "",
					"Please correct the input data and retry.", exception);
		}
		return categorys;
	}

}
