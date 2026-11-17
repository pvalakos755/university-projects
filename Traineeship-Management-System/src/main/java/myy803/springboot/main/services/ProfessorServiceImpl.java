package myy803.springboot.main.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myy803.springboot.main.domainmodel.Evaluation;
import myy803.springboot.main.domainmodel.EvaluationType;
import myy803.springboot.main.domainmodel.Professor;
import myy803.springboot.main.domainmodel.Student;
import myy803.springboot.main.domainmodel.TraineeshipPosition;
import myy803.springboot.main.mappers.ProfessorMapper;
import myy803.springboot.main.mappers.StudentMapper;
import myy803.springboot.main.mappers.TraineeshipPositionsMapper;
import myy803.springboot.main.services.interfaces.ProfessorService;

@Service
public class ProfessorServiceImpl implements ProfessorService {
	@Autowired
	private ProfessorMapper professorMapper;
	@Autowired
	private TraineeshipPositionsMapper traineeshipPositionsMapper;
	@Autowired
	private StudentMapper studentMapper;
	
	@Autowired
	public ProfessorServiceImpl(ProfessorMapper professorMapper,TraineeshipPositionsMapper traineeshipPositionsMapper,StudentMapper studentMapper) {
		this.professorMapper=professorMapper;
		this.traineeshipPositionsMapper=traineeshipPositionsMapper;
		this.studentMapper=studentMapper;
	}
	
	public Professor retrieveProfile(String username) {
		return professorMapper.findByUsername(username);
	}
	
	public void saveProfile(Professor professor) {
		professorMapper.save(professor);
	}
	
	public List<TraineeshipPosition> retrieveAssignedPositions(String username) {
		return professorMapper.findByUsername(username).getSupervisedPositions();
	}
	
	public void saveEvaluation(Integer positionId, Evaluation evaluation) {
		TraineeshipPosition position=traineeshipPositionsMapper.findById(positionId).get();
		List<Evaluation> evaluations=position.getEvaluations();
		evaluation.setPosition(position);
		evaluation.setEvaluationType(EvaluationType.FROM_PROFESSOR);
		evaluations.add(evaluation);
		position.setEvaluations(evaluations);
		position.setEvaluatedByProfessor(true);
		traineeshipPositionsMapper.save(position);
	}
	
	public Student showStudentDetails(String studentUsername) {
		return studentMapper.findByUsername(studentUsername);
	}
}
