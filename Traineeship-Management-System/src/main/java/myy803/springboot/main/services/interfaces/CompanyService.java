package myy803.springboot.main.services.interfaces;

import java.util.List;

import myy803.springboot.main.domainmodel.Company;
import myy803.springboot.main.domainmodel.Evaluation;
import myy803.springboot.main.domainmodel.Student;
import myy803.springboot.main.domainmodel.TraineeshipPosition;

public interface CompanyService {
	public Company retrieveProfile(String username);
	public void saveProfile(Company company);
	public void savePosition(TraineeshipPosition position,String username);
	public List<TraineeshipPosition> retrieveAvailablePositions(String username);
	public List<TraineeshipPosition> retrieveAssignedPositions(String username);
	public void deletePosition(Integer id);
	public void saveEvaluation(Integer positionId, Evaluation evaluation);
	public Student showStudentDetails(String studentUsername);
}
