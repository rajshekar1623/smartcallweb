package com.aakhya.smartcall.application.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.aakhya.smartcall.application.security.entity.User;
import com.aakhya.smartcall.application.security.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optionalUser = userRepository.findById(username);
		if(optionalUser.isPresent()) {
			User user = optionalUser.get();
			System.out.println("19-May-2023 :: User Name :: "+user.getUserId());
			System.out.println("19-May-2023 :: Role Description:: "+user.getRoleDescription());
			return new CustomUserDetails(user);
		}else
			throw new UsernameNotFoundException("User does not exist");
	}

}
