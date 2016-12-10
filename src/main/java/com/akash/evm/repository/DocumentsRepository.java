package com.akash.evm.repository;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.akash.evm.enums.DocumentStatusEnum;
import com.akash.evm.exception.BaseException;
import com.akash.evm.model.Documents;

@Repository
public interface DocumentsRepository extends BaseRepository<Documents, Long> {
	public List<Documents> findByStartDateBetween(Date startDate, Date endDate) throws BaseException;

	public List<Documents> findByDocumentNameIgnoreCase(String name) throws BaseException;

	public List<Documents> findByStatus(DocumentStatusEnum status) throws BaseException;
}
