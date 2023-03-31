package com.example.aws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.aws.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
