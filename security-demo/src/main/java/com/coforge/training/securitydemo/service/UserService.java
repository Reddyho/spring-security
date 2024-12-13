
package com.coforge.training.securitydemo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.coforge.training.securitydemo.model.User;
import com.coforge.training.securitydemo.repository.userrepository;

/*
*Author:Koppula.Reddy
*Date  :Dec 2, 2024
*Time   :5:29:00 PM
*
*/

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private final userrepository userrepo;
	
	@Autowired
	private final PasswordEncoder passwordEncoder;
	//DI using constructor
	public UserService(userrepository userrepo, PasswordEncoder passwordEncoder) {
		super();
		this.userrepo = userrepo;
		this.passwordEncoder = passwordEncoder;
	}
	
	public void saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userrepo.save(user); //save() pre-defined method in JPA repo
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user=userrepo.findByUsername(username);
		return user.orElseThrow(() -> new UsernameNotFoundException("User Not found"));      
	}
	
	

}
