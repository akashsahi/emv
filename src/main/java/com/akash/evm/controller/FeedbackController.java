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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import com.akash.evm.enums.FeedbackStatusEnum;
import com.akash.evm.exception.BaseException;
import com.akash.evm.model.Feedback;
import com.akash.evm.service.FeedbackService;

@Controller
public class FeedbackController {

	private static final Logger LOGGER = LogManager.getLogger(FeedbackController.class);

	@Autowired
	FeedbackService feedbackService;

	// -------------------Retrieve All
	@RequestMapping(value = "/feedback/", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<Feedback>> listAll()  {
		List<Feedback> feedbacks = new ArrayList<>();
		ResponseEntity<List<Feedback>> responseEntity = new ResponseEntity<>(HttpStatus.OK);
		try {
			feedbacks = feedbackService.findByStatus(FeedbackStatusEnum.ACTIVE);

			if (feedbacks.isEmpty()) {
				responseEntity = new ResponseEntity<List<Feedback>>(HttpStatus.NO_CONTENT);
			} else {
				responseEntity = new ResponseEntity<List<Feedback>>(feedbacks, HttpStatus.OK);
			}
		} catch (BaseException e) {
			throw e;
		}
		return responseEntity;
	}

	// -------------------Retrieve Single

	@RequestMapping(value = "/feedback/{feedbackId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Feedback> getFeedback(@PathVariable("feedbackId") Long feedbackId) {
		LOGGER.info("Fetching Feedback with id " + feedbackId);
		ResponseEntity<Feedback> responseEntity = new ResponseEntity<>(HttpStatus.OK);
		Feedback feedback = new Feedback();
		try {
			feedback = feedbackService.findById(feedbackId);
			if (feedbackId == null) {
				LOGGER.info("Feedback with id " + feedbackId + " not found");
				responseEntity = new ResponseEntity<Feedback>(HttpStatus.NOT_FOUND);
			} else {
				responseEntity = new ResponseEntity<Feedback>(feedback, HttpStatus.OK);
			}

		} catch (BaseException e) {
			throw e;
		}

		return responseEntity;

	}

	// -------------------Create a Feedback

	@RequestMapping(value = "/feedback/", method = RequestMethod.POST)
	public ResponseEntity<Void> createFeedback(@RequestBody Feedback feedback, UriComponentsBuilder ucBuilder) {
		LOGGER.info("Creating Feedback " + feedback.getFeedback());
		ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.OK);
		try {
			feedback = feedbackService.saveUpdate(feedback);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(
					ucBuilder.path("/feedback/{feedbackId}").buildAndExpand(feedback.getFeedbackId()).toUri());
			responseEntity = new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		} catch (Exception e) {
			throw e;
		}
		return responseEntity;

	}

	// ------------------- Update a Feedback

	@RequestMapping(value = "/feedback/{feedbackId}", method = RequestMethod.PUT)
	public ResponseEntity<Feedback> updateUser(@PathVariable("feedbackId") Long feedbackId,
			@RequestBody Feedback feedback) {
		LOGGER.info("Updating Feedback " + feedback.getFeedback());
		ResponseEntity<Feedback> responseEntity = new ResponseEntity<>(HttpStatus.OK);
		try {
			feedbackService.saveUpdate(feedback);
			responseEntity = new ResponseEntity<Feedback>(feedback, HttpStatus.OK);
		} catch (BaseException e) {
			throw e;
		}
		return responseEntity;
	}

	// ------------------- Delete a User

	@RequestMapping(value = "/feedback/{feedbackId}", method = RequestMethod.DELETE)
	public ResponseEntity<Feedback> deleteUser(@PathVariable("feedbackId") Long feedbackId) {
		LOGGER.info("Fetching & Deleting Feedback with id " + feedbackId);
		ResponseEntity<Feedback> responseEntity = new ResponseEntity<>(HttpStatus.OK);
		Feedback feedback;
		try {
			feedback = feedbackService.findById(feedbackId);
			if (feedback == null) {
				LOGGER.info("Unable to delete. Feedback with id " + feedbackId + " not found");
				responseEntity = new ResponseEntity<Feedback>(HttpStatus.NOT_FOUND);
			} else {
				feedback.setStatus(FeedbackStatusEnum.DELETED);
				feedbackService.saveUpdate(feedback);
				responseEntity = new ResponseEntity<Feedback>(HttpStatus.NO_CONTENT);
			}

		} catch (BaseException e) {
			throw e;
		}
		return responseEntity;
	}

}
