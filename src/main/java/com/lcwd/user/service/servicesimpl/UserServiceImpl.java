package com.lcwd.user.service.servicesimpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.exception.ResourceNotFoundException;
import com.lcwd.user.service.services.UserService;
import com.lcwd.user.service.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	@Cacheable("Users")
	public List<User> getUsers() {
		return userRepository.findAll();
	}
	
	@Override
	@Cacheable("User")
	public User getUser(Integer userId) {
	User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found : "+userId));
	return user;
	}

	@Override
	public String deleteUser(Integer userId) {
		// TODO Auto-generated method stub
		userRepository.deleteById(userId);
		return "User deleted";
	}

	@Override
	public User updateUser(Integer id, User user) {
		User fetchedUser = getUser(id);
		fetchedUser.setFirstName(user.getFirstName());
		fetchedUser.setLastName(user.getLastName());
		fetchedUser.setEmail(user.getEmail());
		userRepository.save(fetchedUser);
		return fetchedUser;
	}
}

