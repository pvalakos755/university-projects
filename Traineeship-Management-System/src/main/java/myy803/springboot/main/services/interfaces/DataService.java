package myy803.springboot.main.services.interfaces;

import java.util.List;

import myy803.springboot.main.domainmodel.Interest;
import myy803.springboot.main.domainmodel.Location;
import myy803.springboot.main.domainmodel.Skill;

public interface DataService {
	public List<Interest> findAllInterests();
	public Interest findByIdInterest(Integer interestId);
	public void saveInterest(Interest interest);
	
	public List<Skill> findAllSkills();
	public Skill findByIdSkill(Integer skillId);
	public void saveSkill(Skill skill);
	
	public List<Location> findAllLocations();
	public Location findByIdLocation(Integer locationId);
	public void saveLocation(Location location);
}
