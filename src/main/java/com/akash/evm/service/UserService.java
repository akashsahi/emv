package com.akash.evm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.akash.evm.enums.UserStatusEnum;
import com.akash.evm.exception.BaseException;
import com.akash.evm.model.User;

@Service
public interface UserService {
	public User saveUpdate(User brand) throws BaseException;

	public User findById(Long userId) throws BaseException;

	public User findByUserName(String userName) throws BaseException;

	public List<User> findByStatus(UserStatusEnum status) throws BaseException;

	public List<User> findAll() throws BaseException;
}