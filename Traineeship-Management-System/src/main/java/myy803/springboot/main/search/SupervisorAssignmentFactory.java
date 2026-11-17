package myy803.springboot.main.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SupervisorAssignmentFactory {
	@Autowired
    private AssignmentBasedOnLoad assignmentBasedOnLoad;
    @Autowired
    private AssignmentBasedOnInterests assignmentBasedOnInterests;
    
    @Autowired
    public SupervisorAssignmentFactory(AssignmentBasedOnLoad assignmentBasedOnLoad,AssignmentBasedOnInterests assignmentBasedOnInterests) {
    	this.assignmentBasedOnLoad=assignmentBasedOnLoad;
    	this.assignmentBasedOnInterests=assignmentBasedOnInterests;
    }
    
    public SupervisorAssignmentStrategy create(String strategy) {
        if(strategy.equals("interests")){
            return assignmentBasedOnInterests;
        }else{
            return assignmentBasedOnLoad;
        }
    }
}
