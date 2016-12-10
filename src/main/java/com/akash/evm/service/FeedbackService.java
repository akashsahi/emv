package com.akash.evm.service;

import java.util.List;

import com.akash.evm.enums.FeedbackStatusEnum;
import com.akash.evm.exception.BaseException;
import com.akash.evm.model.Feedback;

public interface FeedbackService {

	public Feedback saveUpdate(Feedback feedback) throws BaseException;

	public Feedback findById(Long feedbackId) throws BaseException;

	public List<Feedback> findByCreatedBy(Long createdById) throws BaseException;

	public List<Feedback> findByStatus(FeedbackStatusEnum status) throws BaseException;

	public List<Feedback> findAll() throws BaseException;

}
