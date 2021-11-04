package com.antra.smart_home_v1.service;

import com.antra.smart_home_v1.domain.User;

import java.util.List;
import java.util.Optional;

public interface AdminUserService {
	List<User> getAllUsers(int page);

	Optional<User> getUserById(int userId);

	Boolean addAdminRole(int id);

	int removeAdminRole(int id);

	Boolean modify(User user);
}
