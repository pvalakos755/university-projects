package myy803.springboot.main.services.interfaces;

import myy803.springboot.main.domainmodel.Student;
import myy803.springboot.main.domainmodel.TraineeshipPosition;

public interface StudentService {
	public Student retrieveProfile(String username);
	public void saveProfile(Student student);
	public void saveInterestForPosition(String username);
	public void saveLogbook(TraineeshipPosition position,String username);
}
