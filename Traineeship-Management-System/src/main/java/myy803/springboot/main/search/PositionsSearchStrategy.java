package myy803.springboot.main.search;

import java.util.List;

import myy803.springboot.main.domainmodel.TraineeshipPosition;

public interface PositionsSearchStrategy {
	List<TraineeshipPosition> search(String applicantUsername);
}
