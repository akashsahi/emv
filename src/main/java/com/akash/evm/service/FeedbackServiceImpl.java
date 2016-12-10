package com.akash.evm.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akash.evm.enums.FeedbackStatusEnum;
import com.akash.evm.exception.BaseException;
import com.akash.evm.model.Feedback;
import com.akash.evm.repository.FeedbackRepoitory;

@Service
public class FeedbackServiceImpl implements FeedbackService {

	private static final Logger LOGGER = LogManager.getLogger(FeedbackServiceImpl.class);

	@Autowired
	private FeedbackRepoitory feedbackRepoitory;

	@Override
	public Feedback saveUpdate(Feedback feedback) throws BaseException {
		try {
			feedback = feedbackRepoitory.save(feedback);
		} catch (Exception exception) {
			LOGGER.error("An exception occured in saving the feedback >>" + feedback.getFeedback(), exception);
			String rootCuase = (null != exception.getCause() ? exception.getCause().getMessage()
					: "Unknown error occured");
			throw new BaseException("An exception occured in saving the feedback with name " + feedback.getCreatedBy(),
					rootCuase, "Please correct the input data and retry.", "", exception);
		}
		return feedback;
	}

	@Override
	public Feedback findById(Long feedbackId) throws BaseException {
		Feedback feedback;
		try {
			feedback = feedbackRepoitory.findOne(feedbackId);
		} catch (Exception exception) {
			LOGGER.error("An exception occured in finding the feedback with id " + feedbackId, exception);
			String rootCuase = (null != exception.getCause() ? exception.getCause().getMessage()
					: "Unknown error occured");
			throw new BaseException("An exception occured in finding the feedback with id " + feedbackId, rootCuase, "",
					"Please provide a valid Id", exception);
		}
		return feedback;
	}

	@Override
	public List<Feedback> findByCreatedBy(Long createdById) throws BaseException {
		List<Feedback> feedbacks;
		try {
			feedbacks = feedbackRepoitory.findByCreatedBy(createdById);
		} catch (Exception exception) {
			LOGGER.error("An exception occured in finding the feedback for created by " + createdById, exception);
			String rootCuase = (null != exception.getCause() ? exception.getCause().getMessage()
					: "Unknown error occured");
			throw new BaseException("An exception occured in finding the feedback for created by " + createdById,
					rootCuase, "", "Please provide a valid id and try  again.", exception);
		}
		return feedbacks;
	}

	@Override
	public List<Feedback> findByStatus(FeedbackStatusEnum status) throws BaseException {
		List<Feedback> feedbacks;
		try {
			feedbacks = feedbackRepoitory.findByStatus(status);
		} catch (Exception exception) {
			LOGGER.error("An exception occured in finding the feedback with status " + status.getStatus(), exception);
			String rootCuase = (null != exception.getCause() ? exception.getCause().getMessage()
					: "Unknown error occured");
			throw new BaseException("An exception occured in finding the feedback with status " + status.getStatus(),
					rootCuase, "", "Please correct the input data and retry.", exception);
		}
		return feedbacks;
	}

	@Override
	public List<Feedback> findAll() throws BaseException {
		List<Feedback> feedbacks;
		try {
			feedbacks = feedbackRepoitory.findAll();
		} catch (Exception exception) {
			LOGGER.error("An exception occured in finding the feedback ", exception);
			String rootCuase = (null != exception.getCause() ? exception.getCause().getMessage()
					: "Unknown error occured");
			throw new BaseException("An exception occured in finding the feedback ", rootCuase, "", "", exception);
		}
		return feedbacks;
	}
}
