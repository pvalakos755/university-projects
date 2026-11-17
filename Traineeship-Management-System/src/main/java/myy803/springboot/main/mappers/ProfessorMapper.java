package myy803.springboot.main.mappers;

import org.springframework.data.jpa.repository.JpaRepository;

import myy803.springboot.main.domainmodel.Professor;

public interface ProfessorMapper extends JpaRepository<Professor, Integer> {
	public Professor findByUsername(String username);
}
