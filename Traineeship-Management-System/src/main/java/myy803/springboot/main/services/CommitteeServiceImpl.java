package myy803.springboot.main.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myy803.springboot.main.domainmodel.LookingForTraineeshipType;
import myy803.springboot.main.domainmodel.Student;
import myy803.springboot.main.domainmodel.TraineeshipPosition;
import myy803.springboot.main.mappers.StudentMapper;
import myy803.springboot.main.mappers.TraineeshipPositionsMapper;
import myy803.springboot.main.search.PositionsSearchFactory;
import myy803.springboot.main.search.PositionsSearchStrategy;
import myy803.springboot.main.search.SupervisorAssignmentFactory;
import myy803.springboot.main.search.SupervisorAssignmentStrategy;
import myy803.springboot.main.services.interfaces.CommitteeService;

@Service
public class CommitteeServiceImpl implements CommitteeService {
	@Autowired
	private StudentMapper studentMapper;
	@Autowired
	private TraineeshipPositionsMapper traineeshipPositionsMapper;
	@Autowired
	private PositionsSearchFactory positionsSearchFactory;
	@Autowired
	private SupervisorAssignmentFactory supervisorAssignmentFactory;
	
	@Autowired
	public CommitteeServiceImpl(StudentMapper studentMapper,TraineeshipPositionsMapper traineeshipPositionsMapper,PositionsSearchFactory positionsSearchFactory) {
		this.studentMapper=studentMapper;
		this.traineeshipPositionsMapper=traineeshipPositionsMapper;
		this.positionsSearchFactory=positionsSearchFactory;
	}
	
	public List<Student> retrieveTraineeshipApplications() {
		return studentMapper.findByLookingForTraineeship(LookingForTraineeshipType.LOOKING);
	}
	
	public List<TraineeshipPosition> retrievePositionsForApplicant(String applicantUsername, String strategy) {
		PositionsSearchStrategy positionsStrategy=positionsSearchFactory.create(strategy);
		return positionsStrategy.search(applicantUsername);
	}
	
	public void assignPosition(Integer positionId, String studentUsername) {
		TraineeshipPosition position=traineeshipPositionsMapper.findById(positionId).get();
		Student student=studentMapper.findByUsername(studentUsername);
		position.setAssigned(true);
		student.setLookingForTraineeship(LookingForTraineeshipType.ASSIGNED);
		position.setStudent(student);
		traineeshipPositionsMapper.save(position);
	}
	
	public List<TraineeshipPosition> retrievePositionsForSupervisor() {
		return traineeshipPositionsMapper.findByIsAssignedAndSupervisorIsNull(true);
	}
	
	public void assignSupervisor(Integer positionId, String strategy) {
		SupervisorAssignmentStrategy supervisorStrategy=supervisorAssignmentFactory.create(strategy);
		supervisorStrategy.assign(positionId);
	}
	
	public Student showStudentDetails(String studentUsername) {
		Student student=studentMapper.findByUsername(studentUsername);
		return student;
	}
	
	public List<TraineeshipPosition> listAssignedTraineeships() {
		return traineeshipPositionsMapper.findByIsAssignedAndSupervisorIsNotNullAndIsCompleted(true, false);
	}
	
	public List<TraineeshipPosition> listCompletedTraineeships() {
		return traineeshipPositionsMapper.findByIsAssignedAndSupervisorIsNotNullAndIsCompleted(true, true);
	}
	
	public void completeAssignedTraineeships(Integer positionId, boolean passFailGrade) {
		TraineeshipPosition position=traineeshipPositionsMapper.findById(positionId).get();
		position.setPassFailGrade(passFailGrade);
		position.setCompleted(true);
		traineeshipPositionsMapper.save(position);		
	}
}
