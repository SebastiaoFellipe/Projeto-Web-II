package com.bti.projetoweb2.repositories;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bti.projetoweb2.users.User;


public interface UserRepository extends JpaRepository<User, String> {
    
    Optional<User> findByLogin(String login);
}
