package com.yhbnb.repository.DataJPA;

import com.yhbnb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<User, Long> {
	
}
