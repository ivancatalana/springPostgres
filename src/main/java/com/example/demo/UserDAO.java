package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<Usuario,Integer> {

}

