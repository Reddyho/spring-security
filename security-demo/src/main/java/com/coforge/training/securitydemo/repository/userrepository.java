
package com.coforge.training.securitydemo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coforge.training.securitydemo.model.User;

/*
*Author:Koppula.Reddy
*Date  :Dec 2, 2024
*Time   :5:22:45 PM
*
*/

public interface userrepository extends JpaRepository<User, Integer>{
	
	Optional<User> findByUsername(String username);

}
