package com.akash.evm.service;

import java.util.List;

import com.akash.evm.enums.CategorytStatusEnum;
import com.akash.evm.exception.BaseException;
import com.akash.evm.model.Category;

public interface CategoryService {

	public Category saveUpdate(Category category) throws BaseException;

	public Category findById(Long categoryId) throws BaseException;

	public Category findByName(String name) throws BaseException;

	public List<Category> findByStatus(CategorytStatusEnum status) throws BaseException;

	public List<Category> findAll() throws BaseException;

}
