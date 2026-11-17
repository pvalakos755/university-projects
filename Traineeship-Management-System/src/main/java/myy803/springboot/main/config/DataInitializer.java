package myy803.springboot.main.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import myy803.springboot.main.domainmodel.Interest;
import myy803.springboot.main.domainmodel.Location;
import myy803.springboot.main.domainmodel.Skill;
import myy803.springboot.main.mappers.InterestMapper;
import myy803.springboot.main.mappers.LocationMapper;
import myy803.springboot.main.mappers.SkillMapper;

@Component
public class DataInitializer implements CommandLineRunner {
	private final InterestMapper interestMapper;
    private final SkillMapper skillMapper;
    private final LocationMapper locationMapper;

    public DataInitializer(InterestMapper interestMapper, SkillMapper skillMapper, LocationMapper locationMapper) {
        this.interestMapper = interestMapper;
        this.skillMapper = skillMapper;
        this.locationMapper = locationMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        if (interestMapper.findAll().isEmpty()) {
        	interestMapper.save(new Interest(null, "Artificial Intelligence"));
        	interestMapper.save(new Interest(null, "Web Development"));
        	interestMapper.save(new Interest(null, "Cybersecurity"));
        	interestMapper.save(new Interest(null, "Mobile Applications"));
        	interestMapper.save(new Interest(null, "Cloud Computing"));
        	interestMapper.save(new Interest(null, "Blockchain"));
        	interestMapper.save(new Interest(null, "Internet of Things"));
        	interestMapper.save(new Interest(null, "Data Science"));
        	interestMapper.save(new Interest(null, "Machine Learning"));
        	interestMapper.save(new Interest(null, "Software Testing"));
        	interestMapper.save(new Interest(null, "Game Development"));
        	interestMapper.save(new Interest(null, "Computer Vision"));
        	interestMapper.save(new Interest(null, "DevOps"));
        	interestMapper.save(new Interest(null, "Bioinformatics"));
        	interestMapper.save(new Interest(null, "Embedded Systems"));
        }
        
        if (skillMapper.findAll().isEmpty()) {
        	skillMapper.save(new Skill(null, "Java"));
        	skillMapper.save(new Skill(null, "Spring Boot"));
        	skillMapper.save(new Skill(null, "Python"));
        	skillMapper.save(new Skill(null, "JavaScript"));
        	skillMapper.save(new Skill(null, "React"));
        	skillMapper.save(new Skill(null, "Angular"));
        	skillMapper.save(new Skill(null, "SQL"));
        	skillMapper.save(new Skill(null, "MongoDB"));
        	skillMapper.save(new Skill(null, "Docker"));
        	skillMapper.save(new Skill(null, "Kubernetes"));
        	skillMapper.save(new Skill(null, "Git"));
        	skillMapper.save(new Skill(null, "HTML/CSS"));
        	skillMapper.save(new Skill(null, "C#"));
        	skillMapper.save(new Skill(null, "Node.js"));
        	skillMapper.save(new Skill(null, "AWS"));
        	skillMapper.save(new Skill(null, "Linux"));
        	skillMapper.save(new Skill(null, "Android Development"));
        	skillMapper.save(new Skill(null, "Unit Testing"));
        	skillMapper.save(new Skill(null, "REST APIs"));
        }
        
        if (locationMapper.findAll().isEmpty()) {
        	locationMapper.save(new Location(null, "Athens"));
        	locationMapper.save(new Location(null, "Thessaloniki"));
        	locationMapper.save(new Location(null, "Patras"));
        	locationMapper.save(new Location(null, "Heraklion"));
        	locationMapper.save(new Location(null, "Volos"));
        	locationMapper.save(new Location(null, "Ioannina"));
        	locationMapper.save(new Location(null, "Larissa"));
        	locationMapper.save(new Location(null, "Rhodes"));
        	locationMapper.save(new Location(null, "Chania"));
        	locationMapper.save(new Location(null, "Kavala"));
        	locationMapper.save(new Location(null, "Corfu"));
        	locationMapper.save(new Location(null, "Trikala"));
        	locationMapper.save(new Location(null, "Serres"));
        	locationMapper.save(new Location(null, "Kozani"));
        	locationMapper.save(new Location(null, "Kalamata"));
        	locationMapper.save(new Location(null, "Xanthi"));
        	locationMapper.save(new Location(null, "Komotini"));
        	locationMapper.save(new Location(null, "Drama"));
        	locationMapper.save(new Location(null, "Agrinio"));
        	locationMapper.save(new Location(null, "Lamia"));
        	locationMapper.save(new Location(null, "Piraeus"));
        	locationMapper.save(new Location(null, "Chalkida"));
        	locationMapper.save(new Location(null, "Mytilene"));
        	locationMapper.save(new Location(null, "Preveza"));
        	locationMapper.save(new Location(null, "Sparti"));
        	locationMapper.save(new Location(null, "Nafplio"));
        }
    }
}
