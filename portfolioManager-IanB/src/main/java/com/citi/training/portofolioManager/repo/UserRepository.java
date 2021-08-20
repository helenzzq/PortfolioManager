package com.citi.training.portofolioManager.repo;

import com.citi.training.portofolioManager.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
