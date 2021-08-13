package com.citi.training.portofolioManagerIanB.repo;

import com.citi.training.portofolioManagerIanB.entities.Investments;
import com.citi.training.portofolioManagerIanB.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
