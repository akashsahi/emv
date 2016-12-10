package com.akash.evm.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akash.evm.enums.BrandtStatusEnum;
import com.akash.evm.exception.BaseException;
import com.akash.evm.model.Brand;
import com.akash.evm.model.Category;
import com.akash.evm.repository.BrandRepository;
import com.akash.evm.repository.CategoryRepository;

@Service
public class BrandServiceImpl implements BrandService {

	private static final Logger LOGGER = LogManager.getLogger(BrandServiceImpl.class);

	@Autowired
	private BrandRepository brandRepository;
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Brand saveUpdate(Brand brand) throws BaseException {
		try {
			Category category = categoryRepository.findOne(brand.getCategory().getCategoryId());
			if (null != category) {
				brand.setCategory(category);
				brand = brandRepository.save(brand);
			} else {
				throw new BaseException("An exception occured in saving the brand with name " + brand.getBrandName(),
						"Inavlid category Id", "Please provide a valid category id.", "");
			}

		} catch (Exception exception) {
			LOGGER.error("An exception occured in saving the brand with name " + brand.getBrandName(), exception);
			String rootCuase = (null != exception.getCause() ? exception.getCause().getMessage()
					: "Unknown error occured");
			throw new BaseException("An exception occured in saving the brand with name " + brand.getBrandName(),
					rootCuase, "", "", exception);
		}
		return brand;
	}

	@Override
	public Brand findById(Long brandId) throws BaseException {
		Brand brand;
		try {
			brand = brandRepository.findOne(brandId);
		} catch (Exception exception) {
			LOGGER.error("An exception occured in finding the brand with id " + brandId, exception);
			String rootCuase = (null != exception.getCause() ? exception.getCause().getMessage()
					: "Unknown error occured");
			throw new BaseException("An exception occured in finding the brand with id " + brandId, rootCuase, "", "",
					exception);
		}
		return brand;
	}

	@Override
	public Brand findByBrandName(String brandName) throws BaseException {
		Brand brand;
		try {
			brand = brandRepository.findByBrandNameIgnoreCase(brandName);
		} catch (Exception exception) {
			LOGGER.error("An exception occured in finding the brand with name " + brandName, exception);
			String rootCuase = (null != exception.getCause() ? exception.getCause().getMessage()
					: "Unknown error occured");
			throw new BaseException("An exception occured in finding the brand with name " + brandName, rootCuase, "",
					"", exception);
		}
		return brand;
	}

	@Override
	public List<Brand> findByStatus(BrandtStatusEnum status) throws BaseException {
		List<Brand> brands;
		try {
			brands = brandRepository.findByStatus(status);
		} catch (Exception exception) {
			LOGGER.error("An exception occured in finding the brand with status " + status.getStatus(), exception);
			String rootCuase = (null != exception.getCause() ? exception.getCause().getMessage()
					: "Unknown error occured");
			throw new BaseException("An exception occured in finding the brand with status " + status.getStatus(),
					rootCuase, "", "", exception);
		}
		return brands;
	}

	@Override
	public List<Brand> findAll() throws BaseException {
		List<Brand> brands;
		try {
			brands = brandRepository.findAll();
		} catch (Exception exception) {
			LOGGER.error("An exception occured in finding the brand ", exception);
			String rootCuase = (null != exception.getCause() ? exception.getCause().getMessage()
					: "Unknown error occured");
			throw new BaseException("An exception occured in finding the brand ", rootCuase, "", "", exception);
		}
		return brands;
	}

}
