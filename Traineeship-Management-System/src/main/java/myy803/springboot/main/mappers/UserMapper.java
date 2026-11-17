package myy803.springboot.main.mappers;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import myy803.springboot.main.domainmodel.User;

public interface UserMapper extends JpaRepository<User, Integer> {
	
	Optional<User> findByUsername(String username);
}
