package com.akash.evm.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.akash.evm.enums.BrandtStatusEnum;
import com.akash.evm.model.Brand;

@Repository
public interface BrandRepository extends BaseRepository<Brand, Long> {
	public Brand findByBrandNameIgnoreCase(String brandName);

	public List<Brand> findByStatus(BrandtStatusEnum status);
}
