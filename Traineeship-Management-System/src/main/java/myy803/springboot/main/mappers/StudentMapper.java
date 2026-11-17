package myy803.springboot.main.mappers;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import myy803.springboot.main.domainmodel.LookingForTraineeshipType;
import myy803.springboot.main.domainmodel.Student;

public interface StudentMapper extends JpaRepository<Student, Integer> {
	public Student findByUsername(String username);
	public List<Student> findByLookingForTraineeship(LookingForTraineeshipType lookingForTraineeshipType);
}
