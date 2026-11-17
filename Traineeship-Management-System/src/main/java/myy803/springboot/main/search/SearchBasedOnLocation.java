package myy803.springboot.main.search;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import myy803.springboot.main.domainmodel.Skill;
import myy803.springboot.main.domainmodel.Student;
import myy803.springboot.main.domainmodel.TraineeshipPosition;
import myy803.springboot.main.mappers.StudentMapper;
import myy803.springboot.main.mappers.TraineeshipPositionsMapper;

@Component
public class SearchBasedOnLocation implements PositionsSearchStrategy {
	@Autowired
    private TraineeshipPositionsMapper positionsMapper;
    @Autowired
    private StudentMapper studentMapper;
    
    public SearchBasedOnLocation(TraineeshipPositionsMapper positionsMapper,StudentMapper studentMapper) {
    	this.positionsMapper=positionsMapper;
    	this.studentMapper=studentMapper;
    }
    
    public List<TraineeshipPosition> search(String applicantUsername) {
        Student student=studentMapper.findByUsername(applicantUsername);

        String preferredLocation=student.getPreferredLocation().getName();
        List<TraineeshipPosition> positions=positionsMapper.findByIsAssigned(false);
        List<TraineeshipPosition> matchingPositions=new ArrayList<>();

        for(TraineeshipPosition position :positions) {
        	String companyLocation=position.getCompany().getLocation().getName();
        	if (preferredLocation.equals(companyLocation)) {
        		if(hasRequiredSkills(student,position)==true) {
        			matchingPositions.add(position);
            	}
        	}
        }

        return matchingPositions;
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
