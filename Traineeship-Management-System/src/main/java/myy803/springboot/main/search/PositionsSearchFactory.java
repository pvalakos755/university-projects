package myy803.springboot.main.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PositionsSearchFactory {
	@Autowired
    private SearchBasedOnInterests searchBasedOnInterests;
	@Autowired
    private SearchBasedOnLocation searchBasedOnLocation;
    @Autowired
    private AllSearch allSearch;
    
    @Autowired
    public PositionsSearchFactory(SearchBasedOnInterests searchBasedOnInterests,SearchBasedOnLocation searchBasedOnLocation,AllSearch allSearch) {
    	this.searchBasedOnInterests=searchBasedOnInterests;
    	this.searchBasedOnLocation=searchBasedOnLocation;
    	this.allSearch=allSearch;
    }
    
    public PositionsSearchStrategy create(String strategy) {
        if(strategy.equals("interests")){
            return searchBasedOnInterests;
        }else if(strategy.equals("location")){
            return searchBasedOnLocation;
        }else{
            return allSearch;
        }
    }
}
