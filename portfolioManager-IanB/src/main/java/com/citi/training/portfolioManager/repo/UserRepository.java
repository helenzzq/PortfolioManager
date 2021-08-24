package com.citi.training.portfolioManager.repo;

import com.citi.training.portfolioManager.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
