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

import com.akash.evm.enums.DocumentStatusEnum;
import com.akash.evm.exception.BaseException;
import com.akash.evm.model.Documents;
import com.akash.evm.service.DocumentService;

@Controller
public class DocumentController {

	private static final Logger LOGGER = LogManager.getLogger(DocumentController.class);

	@Autowired
	private DocumentService documentService;

	// -------------------Retrieve All
	@RequestMapping(value = "/document/", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<Documents>> listAll() {
		List<Documents> documents = new ArrayList<>();
		try {
			documents = documentService.findByStatus(DocumentStatusEnum.ACTIVE);

			if (documents.isEmpty()) {
				return new ResponseEntity<List<Documents>>(HttpStatus.NO_CONTENT);
			}
		} catch (BaseException e) {
			throw e;
		}
		return new ResponseEntity<List<Documents>>(documents, HttpStatus.OK);
	}

	// -------------------Retrieve Single

	@RequestMapping(value = "/document/{documentId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Documents> getDocuments(@PathVariable("documentId") Long documentId) {
		LOGGER.info("Fetching Documents with id " + documentId);
		ResponseEntity<Documents> responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		Documents document = new Documents();
		try {
			document = documentService.findById(documentId);
			if (documentId == null) {
				LOGGER.info("Documents with id " + documentId + " not found");
				responseEntity = new ResponseEntity<Documents>(HttpStatus.NOT_FOUND);
			} else {
				responseEntity = new ResponseEntity<Documents>(document, HttpStatus.OK);
			}

		} catch (BaseException e) {
			throw e;
		}

		return responseEntity;

	}

	// -------------------Create a Documents

	@RequestMapping(value = "/document/", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Void> createDocuments(@RequestBody Documents document, UriComponentsBuilder ucBuilder) {
		LOGGER.info("Creating Document " + document.getDocumentName());
		ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		try {
			document = documentService.saveUpdate(document);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(
					ucBuilder.path("/document/{documentId}").buildAndExpand(document.getDocumentId()).toUri());
			responseEntity = new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		} catch (Exception e) {
			throw e;
		}
		return responseEntity;

	}

	// ------------------- Update a Documents

	@RequestMapping(value = "/document/{documentId}", method = RequestMethod.PUT)
	public @ResponseBody ResponseEntity<Documents> updateUser(@PathVariable("documentId") Long documentId,
			@RequestBody Documents document) {
		LOGGER.info("Updating Barnd " + document.getDocumentName());
		ResponseEntity<Documents> responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		try {
			documentService.saveUpdate(document);
			responseEntity = new ResponseEntity<Documents>(document, HttpStatus.OK);
		} catch (BaseException e) {
			throw e;
		}
		return responseEntity;
	}

	// ------------------- Delete a User

	@RequestMapping(value = "/document/{documentId}", method = RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<Documents> deleteUser(@PathVariable("documentId") Long documentId) {
		LOGGER.info("Fetching & Deleting Documents with id " + documentId);
		ResponseEntity<Documents> responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		Documents document;
		try {
			document = documentService.findById(documentId);
			if (document == null) {
				LOGGER.info("Unable to delete. Documents with id " + documentId + " not found");
				responseEntity = new ResponseEntity<Documents>(HttpStatus.NOT_FOUND);
			} else {
				document.setStatus(DocumentStatusEnum.DELETED);
				documentService.saveUpdate(document);
				responseEntity = new ResponseEntity<Documents>(HttpStatus.NO_CONTENT);
			}

		} catch (BaseException e) {
			throw e;
		}
		return responseEntity;
	}

}
