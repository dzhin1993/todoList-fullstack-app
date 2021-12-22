package com.zhinkoilya1993.backend.repository;

import com.zhinkoilya1993.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);
}
