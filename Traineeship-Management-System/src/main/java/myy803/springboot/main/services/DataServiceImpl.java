package myy803.springboot.main.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myy803.springboot.main.domainmodel.Interest;
import myy803.springboot.main.domainmodel.Location;
import myy803.springboot.main.domainmodel.Skill;
import myy803.springboot.main.mappers.InterestMapper;
import myy803.springboot.main.mappers.LocationMapper;
import myy803.springboot.main.mappers.SkillMapper;
import myy803.springboot.main.services.interfaces.DataService;

@Service
public class DataServiceImpl implements DataService {
	@Autowired
	private InterestMapper interestMapper;
	@Autowired
	private SkillMapper skillMapper;
	@Autowired
	private LocationMapper locationMapper;
	
	@Autowired
	public DataServiceImpl(InterestMapper interestMapper, SkillMapper skillMapper, LocationMapper locationMapper) {
		this.interestMapper = interestMapper;
		this.skillMapper = skillMapper;
		this.locationMapper = locationMapper;
	}
	
	public List<Interest> findAllInterests() {
		return interestMapper.findAll();
	}
	
	public Interest findByIdInterest(Integer interestId) {
		return interestMapper.findById(interestId).get();
	}
	
	public void saveInterest(Interest interest) {
		interestMapper.save(interest);
	}
	
	public List<Skill> findAllSkills() {
		return skillMapper.findAll();
	}
	
	public Skill findByIdSkill(Integer skillId) {
		return skillMapper.findById(skillId).get();
	}
	
	public void saveSkill(Skill skill) {
		skillMapper.save(skill);
	}
	
	public List<Location> findAllLocations() {
		return locationMapper.findAll();
	}
	
	public Location findByIdLocation(Integer locationId) {
		return locationMapper.findById(locationId).get();
	}
	
	public void saveLocation(Location location) {
		locationMapper.save(location);
	}
}
