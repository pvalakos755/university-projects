package myy803.springboot.main.search;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import myy803.springboot.main.domainmodel.Interest;
import myy803.springboot.main.domainmodel.Professor;
import myy803.springboot.main.domainmodel.TraineeshipPosition;
import myy803.springboot.main.mappers.ProfessorMapper;
import myy803.springboot.main.mappers.TraineeshipPositionsMapper;

@Component
public class AssignmentBasedOnInterests implements SupervisorAssignmentStrategy {
	@Autowired
    private TraineeshipPositionsMapper positionsMapper;
	@Autowired
    private ProfessorMapper professorMapper;
	
	@Autowired
	public AssignmentBasedOnInterests(TraineeshipPositionsMapper positionsMapper,ProfessorMapper professorMapper) {
		this.positionsMapper=positionsMapper;
		this.professorMapper=professorMapper;
	}
	
	@Override
    public void assign(Integer positionId) {
        TraineeshipPosition position = positionsMapper.findById(positionId).get();

        List<Interest> positionTopics=position.getTopics();
        Professor bestProfessor=null;
        double bestScore=0.0;

        List<Professor> professors=professorMapper.findAll();

        for(Professor prof :professors) {
            double score=J(prof.getInterests(),positionTopics);
            if(score>0.4 && score>bestScore) {
                bestScore=score;
                bestProfessor=prof;
            }
        }

        if(bestProfessor!=null) {
            position.setSupervisor(bestProfessor);
            positionsMapper.save(position);
        }
    }

    private double J(List<Interest> interests, List<Interest> topics) {
        List<String> namesInterests=new ArrayList<>();
        for (Interest i :interests) {
            namesInterests.add(i.getName());
        }

        List<String> namesTopics=new ArrayList<>();
        for(Interest i :topics) {
            namesTopics.add(i.getName());
        }

        int intersection=0;
        List<String> union=new ArrayList<>(namesInterests);

        for(String name :namesTopics) {
            if(namesInterests.contains(name)) {
                intersection++;
            }else {
                union.add(name);
            }
        }

        return (double)intersection/union.size();
    }
}
