package myy803.springboot.main.services.interfaces;

import java.util.List;

import myy803.springboot.main.domainmodel.Student;
import myy803.springboot.main.domainmodel.TraineeshipPosition;

public interface CommitteeService {
	public List<Student> retrieveTraineeshipApplications();
	public List<TraineeshipPosition> retrievePositionsForApplicant(String applicantUsername,String strategy);
	public void assignPosition(Integer positionId,String studentUsername);
	public List<TraineeshipPosition> retrievePositionsForSupervisor();
	public void assignSupervisor(Integer positionId, String strategy);
	public Student showStudentDetails(String studentUsername);
	public List<TraineeshipPosition> listAssignedTraineeships();
	public List<TraineeshipPosition> listCompletedTraineeships();
	public void completeAssignedTraineeships(Integer positionId, boolean passFailGrade);
}
