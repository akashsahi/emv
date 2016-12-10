package com.akash.evm.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.akash.evm.enums.CategorytStatusEnum;
import com.akash.evm.exception.BaseException;
import com.akash.evm.model.Category;

@Repository
public interface CategoryRepository extends BaseRepository<Category, Long> {
	public Category findByCategoryNameIgnoreCase(String name) throws BaseException;

	public List<Category> findByStatus(CategorytStatusEnum status) throws BaseException;
}
