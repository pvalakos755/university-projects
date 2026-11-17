package myy803.springboot.main.services.interfaces;

import java.util.List;

import myy803.springboot.main.domainmodel.Evaluation;
import myy803.springboot.main.domainmodel.Professor;
import myy803.springboot.main.domainmodel.Student;
import myy803.springboot.main.domainmodel.TraineeshipPosition;

public interface ProfessorService {
	public Professor retrieveProfile(String username);
	public void saveProfile(Professor professor);
	public List<TraineeshipPosition> retrieveAssignedPositions(String username);
	public void saveEvaluation(Integer positionId, Evaluation evaluation);
	public Student showStudentDetails(String studentUsername);
}
