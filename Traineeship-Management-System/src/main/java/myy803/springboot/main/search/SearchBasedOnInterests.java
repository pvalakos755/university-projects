package myy803.springboot.main.search;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import myy803.springboot.main.domainmodel.Interest;
import myy803.springboot.main.domainmodel.Skill;
import myy803.springboot.main.domainmodel.Student;
import myy803.springboot.main.domainmodel.TraineeshipPosition;
import myy803.springboot.main.mappers.StudentMapper;
import myy803.springboot.main.mappers.TraineeshipPositionsMapper;

@Component
public class SearchBasedOnInterests implements PositionsSearchStrategy {
	@Autowired
    private TraineeshipPositionsMapper positionsMapper;
    @Autowired
    private StudentMapper studentMapper;
    
    @Autowired
    public SearchBasedOnInterests(TraineeshipPositionsMapper positionsMapper,StudentMapper studentMapper) {
    	this.positionsMapper=positionsMapper;
    	this.studentMapper=studentMapper;
    }
    
    public List<TraineeshipPosition> search(String applicantUsername) {
        Student student=studentMapper.findByUsername(applicantUsername);
        List<Interest> studentInterests=student.getInterests();

        List<TraineeshipPosition> allPositions=positionsMapper.findByIsAssigned(false);
        List<TraineeshipPosition> matchingPositions=new ArrayList<>();

        for (TraineeshipPosition position :allPositions) {
            if (J(studentInterests,position.getTopics())>0.4) {
            	if(hasRequiredSkills(student,position)==true) {
            		matchingPositions.add(position);
            	}
            }
        }

        return matchingPositions;
    }
    
    private double J(List<Interest> studentInterests,List<Interest> positionTopics) {
        List<String> namesStudentInterests=new ArrayList<>();
        for (Interest i :studentInterests) {
        	namesStudentInterests.add(i.getName());
        }

        List<String> namesPositionTopics=new ArrayList<>();
        for (Interest i :positionTopics) {
        	namesPositionTopics.add(i.getName());
        }

        int intersection=0;
        List<String> union=new ArrayList<>(namesStudentInterests);

        for (String name :namesPositionTopics) {
            if(namesStudentInterests.contains(name)) {
                intersection++;
            }else{
                union.add(name);
            }
        }

        return (double)intersection/union.size();
    }
    
    private boolean hasRequiredSkills(Student student, TraineeshipPosition position) {
        List<String> studentSkills =new ArrayList<>();
        for (Skill skill :student.getSkills()) {
        	studentSkills.add(skill.getName());
        }
        
        for (Skill skill :position.getSkills()) {
            String name=skill.getName();
            if (studentSkills.contains(name)==false) {
                return false;
            }
        }

        return true;
    }
}
