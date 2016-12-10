package com.akash.evm.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.akash.evm.enums.UserStatusEnum;
import com.akash.evm.model.User;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {
	public User findByUserNameIgnoreCase(String userName);

	public List<User> findByStatus(UserStatusEnum status);
}
