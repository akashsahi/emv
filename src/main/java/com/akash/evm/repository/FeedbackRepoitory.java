package com.akash.evm.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.akash.evm.enums.FeedbackStatusEnum;
import com.akash.evm.exception.BaseException;
import com.akash.evm.model.Feedback;

@Repository
public interface FeedbackRepoitory extends BaseRepository<Feedback, Long> {
	public List<Feedback> findByCreatedBy(Long createdById) throws BaseException;

	public List<Feedback> findByStatus(FeedbackStatusEnum status) throws BaseException;
}
