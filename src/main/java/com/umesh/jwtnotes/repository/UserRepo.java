package com.umesh.jwtnotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.umesh.jwtnotes.entity.User;

import java.util.Optional;


public interface UserRepo extends JpaRepository<User, Long> {
    public Optional<User> findByEmail(String email);
}
