package myy803.springboot.main.search;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import myy803.springboot.main.domainmodel.TraineeshipPosition;

@Component
public class AllSearch implements PositionsSearchStrategy {
	@Autowired
	private SearchBasedOnInterests searchBasedOnInterests;
	@Autowired
	private SearchBasedOnLocation searchBasedOnLocation;
	
	@Autowired
	public AllSearch(SearchBasedOnInterests searchBasedOnInterests,SearchBasedOnLocation searchBasedOnLocation) {
		this.searchBasedOnInterests=searchBasedOnInterests;
		this.searchBasedOnLocation=searchBasedOnLocation;
	}
	
	public List<TraineeshipPosition> search(String applicantUsername) {
        List<TraineeshipPosition> byLocation=searchBasedOnLocation.search(applicantUsername);
        List<TraineeshipPosition> byInterest=searchBasedOnInterests.search(applicantUsername);

        List<TraineeshipPosition> result=new ArrayList<>();
        
 
        for (TraineeshipPosition location : byLocation) {
        	boolean found= false;
        	for (TraineeshipPosition interest : byInterest) {
            	if(location.getId()== interest.getId()) {
            		found = true;
            	}
            }
        	if(found== true) {
        		result.add(location);
        	}
        	
        }
        
        
        return result;
    }
}
