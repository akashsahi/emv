package com.akash.evm.service;

import java.util.List;

import com.akash.evm.enums.BrandtStatusEnum;
import com.akash.evm.exception.BaseException;
import com.akash.evm.model.Brand;


public interface BrandService {
	public Brand saveUpdate(Brand brand) throws BaseException;

	public Brand findById(Long brandId) throws BaseException;

	public Brand findByBrandName(String brandName) throws BaseException;

	public List<Brand> findByStatus(BrandtStatusEnum status) throws BaseException;

	public List<Brand> findAll() throws BaseException;
}
