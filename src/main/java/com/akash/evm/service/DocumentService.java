package com.akash.evm.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.akash.evm.enums.DocumentStatusEnum;
import com.akash.evm.exception.BaseException;
import com.akash.evm.model.Documents;

@Service
public interface DocumentService {

	public Documents saveUpdate(Documents documents) throws BaseException;

	public Documents findById(Long documentsId) throws BaseException;

	public List<Documents> findByPeriod(Date startDate, Date endDate) throws BaseException;
	
	public List<Documents> findByName(String name) throws BaseException;

	public List<Documents> findByStatus(DocumentStatusEnum status) throws BaseException;

	public List<Documents> findAll() throws BaseException;

}
