package com.akash.evm.service;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akash.evm.enums.DocumentStatusEnum;
import com.akash.evm.exception.BaseException;
import com.akash.evm.model.Documents;
import com.akash.evm.repository.DocumentsRepository;

@Service
public class DocumentServiceImpl implements DocumentService {

	private static final Logger LOGGER = LogManager.getLogger(DocumentServiceImpl.class);

	@Autowired
	private DocumentsRepository documentsRepository;

	@Override
	public Documents saveUpdate(Documents document) throws BaseException {
		try {
			document = documentsRepository.save(document);
		} catch (Exception exception) {
			LOGGER.error("An exception occured in saving the document >>" + document.getDocumentName(), exception);
			String rootCuase = (null != exception.getCause() ? exception.getCause().getMessage()
					: "Unknown error occured");
			throw new BaseException("An exception occured in saving the document >>" + document.getDocumentName(),
					rootCuase, "Please correct the input data and retry.", "", exception);
		}
		return document;
	}

	@Override
	public Documents findById(Long documentsId) throws BaseException {
		Documents document;
		try {
			document = documentsRepository.findOne(documentsId);
		} catch (Exception exception) {
			LOGGER.error("An exception occured in finding the document with id " + documentsId, exception);
			String rootCuase = (null != exception.getCause() ? exception.getCause().getMessage()
					: "Unknown error occured");
			throw new BaseException("An exception occured in finding the document with id " + documentsId, rootCuase,
					"", "Please provide a valid Id", exception);
		}
		return document;
	}

	@Override
	public List<Documents> findByPeriod(Date startDate, Date endDate) throws BaseException {
		List<Documents> documents;
		try {
			documents = documentsRepository.findByStartDateBetween(startDate, endDate);
		} catch (Exception exception) {
			LOGGER.error(
					"An exception occured in finding the document with in the limit " + startDate + " and " + endDate,
					exception);
			String rootCuase = (null != exception.getCause() ? exception.getCause().getMessage()
					: "Unknown error occured");
			throw new BaseException(
					"An exception occured in finding the document with in the limit " + startDate + " and " + endDate,
					rootCuase, "", "Please provide a valid name", exception);
		}
		return documents;
	}

	@Override
	public List<Documents> findByName(String name) throws BaseException {
		List<Documents> documents;
		try {
			documents = documentsRepository.findByDocumentNameIgnoreCase(name);
		} catch (Exception exception) {
			LOGGER.error("An exception occured in finding the document with name " + name, exception);
			String rootCuase = (null != exception.getCause() ? exception.getCause().getMessage()
					: "Unknown error occured");
			throw new BaseException("An exception occured in finding the document with name " + name, rootCuase, "",
					"Please provide a valid name", exception);
		}
		return documents;
	}

	@Override
	public List<Documents> findByStatus(DocumentStatusEnum status) throws BaseException {
		List<Documents> documents;
		try {
			documents = documentsRepository.findByStatus(status);
		} catch (Exception exception) {
			LOGGER.error("An exception occured in finding the document with status " + status.getStatus(), exception);
			String rootCuase = (null != exception.getCause() ? exception.getCause().getMessage()
					: "Unknown error occured");
			throw new BaseException("An exception occured in finding the document with status " + status.getStatus(),
					rootCuase, "", "Please correct the input data and retry.", exception);
		}
		return documents;
	}

	@Override
	public List<Documents> findAll() throws BaseException {
		List<Documents> documents;
		try {
			documents = documentsRepository.findAll();
		} catch (Exception exception) {
			LOGGER.error("An exception occured in finding the document ", exception);
			String rootCuase = (null != exception.getCause() ? exception.getCause().getMessage()
					: "Unknown error occured");
			throw new BaseException("An exception occured in finding the document ", rootCuase, "",
					"Please correct the input data and retry.", exception);
		}
		return documents;
	}

}
