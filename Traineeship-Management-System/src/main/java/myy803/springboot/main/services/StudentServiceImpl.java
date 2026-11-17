package myy803.springboot.main.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myy803.springboot.main.domainmodel.LookingForTraineeshipType;
import myy803.springboot.main.domainmodel.Student;
import myy803.springboot.main.domainmodel.TraineeshipPosition;
import myy803.springboot.main.mappers.StudentMapper;
import myy803.springboot.main.services.interfaces.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentMapper studentMapper;
	
	@Autowired
	public StudentServiceImpl(StudentMapper studentMapper) {
		this.studentMapper=studentMapper;
	}
	
	public Student retrieveProfile(String username) {
		return studentMapper.findByUsername(username);
	}

	
	public void saveProfile(Student student) {
		studentMapper.save(student);
	}
	
	public void saveInterestForPosition(String username) {
		Student student=studentMapper.findByUsername(username);
		student.setLookingForTraineeship(LookingForTraineeshipType.LOOKING);
		studentMapper.save(student);
	}
	
	public void saveLogbook(TraineeshipPosition position,String username) {
		Student student=studentMapper.findByUsername(username);
		String logbook=position.getStudentLogbook();
		TraineeshipPosition assignedPosition=student.getAssignedTraineeship();
		assignedPosition.setStudentLogbook(logbook);
		student.setAssignedTraineeship(assignedPosition);
		studentMapper.save(student);
	}	
}
