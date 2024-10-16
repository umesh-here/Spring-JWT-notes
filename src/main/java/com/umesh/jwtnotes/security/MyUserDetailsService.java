package com.umesh.jwtnotes.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.umesh.jwtnotes.entity.User;
import com.umesh.jwtnotes.repository.UserRepo;

import java.util.Collections;
import java.util.Optional;

@Component
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> userRes = userRepo.findByEmail(email);
		if (userRes.isEmpty())
			throw new UsernameNotFoundException("Could not findUser with email = " + email);
		// Return a User Details object using the fetched User information
		User user = userRes.get();
		return new org.springframework.security.core.userdetails.User(email, user.getPassword(),
				Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
	}
}
