package com.akash.evm.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akash.evm.enums.UserStatusEnum;
import com.akash.evm.exception.BaseException;
import com.akash.evm.model.Brand;
import com.akash.evm.model.User;
import com.akash.evm.repository.BrandRepository;
import com.akash.evm.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BrandRepository brandRepository;

	@Override
	public User saveUpdate(User user) throws BaseException {
		try {
			Brand brand = brandRepository.findOne(user.getBrand().getBrandId());
			if (null != brand) {
				user.setBrand(brand);
				user = userRepository.save(user);
			} else {
				throw new BaseException("An exception occured in saving the brand with name " + user.getUserName(),
						"Inavlid category Id", "Please provide a valid category id.", "");
			}

		} catch (Exception exception) {
			LOGGER.error("An exception occured in saving the user with name " + user.getUserName(), exception);
			String rootCuase = (null != exception.getCause() ? exception.getCause().getMessage()
					: "Unknown error occured");
			throw new BaseException("An exception occured in saving the user with name " + user.getUserName(),
					rootCuase, "", "", exception);
		}
		return user;
	}

	@Override
	public User findById(Long userId) throws BaseException {
		User user;
		try {
			user = userRepository.findOne(userId);
		} catch (Exception exception) {
			LOGGER.error("An exception occured in finding the user with id " + userId, exception);
			String rootCuase = (null != exception.getCause() ? exception.getCause().getMessage()
					: "Unknown error occured");
			throw new BaseException("An exception occured in finding the user with id " + userId, rootCuase, "", "",
					exception);
		}
		return user;
	}

	@Override
	public User findByUserName(String userName) throws BaseException {
		User user;
		try {
			user = userRepository.findByUserNameIgnoreCase(userName);
		} catch (Exception exception) {
			LOGGER.error("An exception occured in finding the user with name " + userName, exception);
			String rootCuase = (null != exception.getCause() ? exception.getCause().getMessage()
					: "Unknown error occured");
			throw new BaseException("An exception occured in finding the user with name " + userName, rootCuase, "", "",
					exception);
		}
		return user;
	}

	@Override
	public List<User> findByStatus(UserStatusEnum status) throws BaseException {
		List<User> users;
		try {
			users = userRepository.findByStatus(status);
		} catch (Exception exception) {
			LOGGER.error("An exception occured in finding the user with status " + status.getStatus(), exception);
			String rootCuase = (null != exception.getCause() ? exception.getCause().getMessage()
					: "Unknown error occured");
			throw new BaseException("An exception occured in finding the user with status " + status.getStatus(),
					rootCuase, "", "", exception);
		}
		return users;
	}

	@Override
	public List<User> findAll() throws BaseException {
		List<User> users;
		try {
			users = userRepository.findAll();
		} catch (Exception exception) {
			LOGGER.error("An exception occured in finding the user ", exception);
			String rootCuase = (null != exception.getCause() ? exception.getCause().getMessage()
					: "Unknown error occured");
			throw new BaseException("An exception occured in finding the user ", rootCuase, "", "", exception);
		}
		return users;
	}
}