package myy803.springboot.main.mappers;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import myy803.springboot.main.domainmodel.TraineeshipPosition;

public interface TraineeshipPositionsMapper extends JpaRepository<TraineeshipPosition, Integer> {
	public List<TraineeshipPosition> findByIsAssignedAndCompanyUsername(boolean isAssigned, String companyUsername);
	public List<TraineeshipPosition> findByIsAssignedAndSupervisorIsNull(boolean isAssigned);
	public List<TraineeshipPosition> findByIsAssignedAndSupervisorIsNotNullAndIsCompleted(boolean isAssigned,boolean isCompleted);
	public List<TraineeshipPosition> findByIsAssigned(boolean isAssigned);
}
