package myy803.springboot.main.search;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import myy803.springboot.main.domainmodel.Professor;
import myy803.springboot.main.domainmodel.TraineeshipPosition;
import myy803.springboot.main.mappers.ProfessorMapper;
import myy803.springboot.main.mappers.TraineeshipPositionsMapper;

@Component
public class AssignmentBasedOnLoad implements SupervisorAssignmentStrategy {
	@Autowired
    private TraineeshipPositionsMapper positionsMapper;
	@Autowired
    private ProfessorMapper professorMapper;
    
	@Autowired
	public AssignmentBasedOnLoad(TraineeshipPositionsMapper positionsMapper,ProfessorMapper professorMapper) {
		this.positionsMapper=positionsMapper;
		this.professorMapper=professorMapper;
	}
	
	public void assign(Integer positionId) {
	    List<Professor> professors=professorMapper.findAll();
	    Professor leastLoaded=null;
	    int minLoad=Integer.MAX_VALUE;

	    for (Professor prof :professors) {
	        int activeSupervisions=0;
	        for (TraineeshipPosition pos :prof.getSupervisedPositions()) {
	            if (pos.isCompleted()==false) {
	                activeSupervisions++;
	            }
	        }

	        if (activeSupervisions<minLoad) {
	            minLoad=activeSupervisions;
	            leastLoaded=prof;
	        }
	    }

	    if (leastLoaded!=null) {
	        TraineeshipPosition position=positionsMapper.findById(positionId).orElseThrow();
	        position.setSupervisor(leastLoaded);
	        positionsMapper.save(position);
	    }
	}
}
