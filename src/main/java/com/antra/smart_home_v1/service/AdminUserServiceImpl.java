package com.antra.smart_home_v1.service;

import com.antra.smart_home_v1.dao.UserRepository;
import com.antra.smart_home_v1.dao.UserRoleRepository;
import com.antra.smart_home_v1.domain.RoleName;
import com.antra.smart_home_v1.domain.User;
import com.antra.smart_home_v1.domain.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AdminUserServiceImpl implements AdminUserService {
	@Autowired
	UserRepository userRepository;

	@Autowired
	UserRoleRepository roleRepository;

	@Override
	@Transactional(readOnly = true)
	public List<User> getAllUsers(int page) {
		return userRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<User> getUserById(int userId) {
		return userRepository.findById(userId);
	}

	@Override
	@Transactional
	public Boolean addAdminRole(int id) {
		UserRole newRole = new UserRole(RoleName.ROLE_ADMIN);
		newRole.setUser(new User(id));
		return roleRepository.save(newRole) != null;
	}

	@Override
	@Transactional
	public int removeAdminRole(int id) {
		return roleRepository.deleteByRoleAndUserId(RoleName.ROLE_ADMIN.name(), id);
	}

	@Override
	@Transactional
	public Boolean modify(User user){
		return userRepository.save(user)!=null;
	}
}
