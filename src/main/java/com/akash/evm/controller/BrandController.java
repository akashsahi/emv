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

import com.akash.evm.enums.BrandtStatusEnum;
import com.akash.evm.exception.BaseException;
import com.akash.evm.model.Brand;
import com.akash.evm.service.BrandService;

@Controller
public class BrandController {

	private static final Logger LOGGER = LogManager.getLogger(BrandController.class);

	@Autowired
	BrandService brandService;

	// -------------------Retrieve All
	@RequestMapping(value = "/brand/", method = RequestMethod.GET)
	public ResponseEntity<List<Brand>> listAll() {
		List<Brand> brands = new ArrayList<>();
		try {
			brands = brandService.findByStatus(BrandtStatusEnum.ACTIVE);

			if (brands.isEmpty()) {
				return new ResponseEntity<List<Brand>>(HttpStatus.NO_CONTENT);
			}
		} catch (BaseException e) {
			throw e;
		}
		return new ResponseEntity<List<Brand>>(brands, HttpStatus.OK);
	}

	// -------------------Retrieve Single

	@RequestMapping(value = "/brand/{brandId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Brand> getBrand(@PathVariable("brandId") Long brandId) {
		LOGGER.info("Fetching Brand with id " + brandId);
		ResponseEntity<Brand> responseEntity = new ResponseEntity<>(HttpStatus.OK);
		Brand brand = new Brand();
		try {
			brand = brandService.findById(brandId);
			if (brandId == null) {
				LOGGER.info("Brand with id " + brandId + " not found");
				responseEntity = new ResponseEntity<Brand>(HttpStatus.NOT_FOUND);
			} else {
				responseEntity = new ResponseEntity<Brand>(brand, HttpStatus.OK);
			}

		} catch (BaseException e) {
			throw e;
		}

		return responseEntity;

	}

	// -------------------Create a Brand

	@RequestMapping(value = "/brand/", method = RequestMethod.POST)
	public ResponseEntity<Void> createBrand(@RequestBody Brand brand, UriComponentsBuilder ucBuilder) {
		LOGGER.info("Creating Barnd " + brand.getBrandName());
		ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.OK);
		try {
			if (null != brandService.findByBrandName(brand.getBrandName())) {
				LOGGER.info("A Brand with name " + brand.getBrandName() + " already exist");
				return new ResponseEntity<Void>(HttpStatus.CONFLICT);
			}
			brand = brandService.saveUpdate(brand);

			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/brand/{brandId}").buildAndExpand(brand.getBrandId()).toUri());
			responseEntity = new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		} catch (Exception e) {
			throw e;
		}
		return responseEntity;

	}

	// ------------------- Update a Brand

	@RequestMapping(value = "/brand/{brandId}", method = RequestMethod.PUT)
	public ResponseEntity<Brand> updateUser(@PathVariable("brandId") Long brandId, @RequestBody Brand brand) {
		LOGGER.info("Updating Barnd " + brand.getBrandName());
		ResponseEntity<Brand> responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		try {
			brandService.saveUpdate(brand);
			responseEntity = new ResponseEntity<Brand>(brand, HttpStatus.OK);
		} catch (BaseException e) {
			throw e;
		}
		return responseEntity;
	}

	// ------------------- Delete a User

	@RequestMapping(value = "/brand/{brandId}", method = RequestMethod.DELETE)
	public ResponseEntity<Brand> deleteUser(@PathVariable("brandId") Long brandId) {
		LOGGER.info("Fetching & Deleting Brand with id " + brandId);
		ResponseEntity<Brand> responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		Brand brand;
		try {
			brand = brandService.findById(brandId);
			if (brand == null) {
				LOGGER.info("Unable to delete. Brand with id " + brandId + " not found");
				responseEntity = new ResponseEntity<Brand>(HttpStatus.NOT_FOUND);
			} else {
				brand.setStatus(BrandtStatusEnum.DELETED);
				brandService.saveUpdate(brand);
				responseEntity = new ResponseEntity<Brand>(HttpStatus.NO_CONTENT);
			}

		} catch (BaseException e) {
			throw e;
		}
		return responseEntity;
	}

}
