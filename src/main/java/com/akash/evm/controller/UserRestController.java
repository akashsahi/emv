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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.akash.evm.enums.UserStatusEnum;
import com.akash.evm.exception.BaseException;
import com.akash.evm.model.User;
import com.akash.evm.service.UserService;

@RestController
public class UserRestController {

	private static final Logger LOGGER = LogManager.getLogger(UserRestController.class);
	@Autowired
	UserService userService; // Service which will do all data

	// -------------------Retrieve All
	@RequestMapping(value = "/user/", method = RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUsers() {
		List<User> feedbacks = new ArrayList<>();
		ResponseEntity<List<User>> responseEntity = new ResponseEntity<>(HttpStatus.OK);
		try {
			feedbacks = userService.findByStatus(UserStatusEnum.ACTIVE);

			if (feedbacks.isEmpty()) {
				responseEntity = new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
			} else {
				responseEntity = new ResponseEntity<List<User>>(feedbacks, HttpStatus.OK);
			}
		} catch (BaseException e) {
			throw e;
		}
		return responseEntity;
	}

	// -------------------Retrieve Single USer
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUser(@PathVariable("id") long id) {
		LOGGER.info("Fetching User with id " + id);
		User user = userService.findById(id);
		if (user == null) {
			LOGGER.info("User with id " + id + " not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	// -------------------Create a user

	@RequestMapping(value = "/user/", method = RequestMethod.POST)
	public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
		LOGGER.info("Creating User " + user.getUserName());

		if (null != userService.findByUserName(user.getUserName())) {
			LOGGER.info("A Category with name " + user.getUserName() + " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		userService.saveUpdate(user);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getUserId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a User

	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {

		userService.saveUpdate(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	// ------------------- Delete a User

	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable("id") long id) {
		LOGGER.info("Fetching & Deleting User with id " + id);

		User user = userService.findById(id);
		if (user == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		user.setStatus(UserStatusEnum.DELETED);
		userService.saveUpdate(user);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

}