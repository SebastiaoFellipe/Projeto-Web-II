package com.bti.projetoweb2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.bti.projetoweb2.users.User;


public interface UserRepository extends JpaRepository<User, String> {
    
    UserDetails findByLogin(String login);
}
