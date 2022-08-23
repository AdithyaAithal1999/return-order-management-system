package com.roms.microservice.authenticationservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.roms.microservice.authenticationservice.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	 Optional<User> findByEmail(String email);
}
