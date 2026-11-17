package myy803.springboot.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import myy803.springboot.main.domainmodel.Interest;
import myy803.springboot.main.domainmodel.Location;
import myy803.springboot.main.domainmodel.Skill;
import myy803.springboot.main.services.interfaces.CommitteeService;
import myy803.springboot.main.services.interfaces.DataService;

@Controller
@RequestMapping("/committee")
public class CommitteeController {
	@Autowired
	private CommitteeService committeeService;
	@Autowired
	private DataService dataService;
	
	@Autowired
	public CommitteeController(CommitteeService committeeService,DataService dataService) {
		this.committeeService=committeeService;
		this.dataService=dataService;
	}
	
	@GetMapping("/dashboard")
	public String getCommitteeDashboard() {
		return "committee/dashboard";
	}
	
	@GetMapping("/listTraineeshipApplications")
	public String listTraineeshipApplications(Model model) {
		model.addAttribute("students",committeeService.retrieveTraineeshipApplications());
		return "committee/listStudents";
	}
	
	@GetMapping("/findPositions")
	public String findPositions(@RequestParam("studentUsername") String username,@RequestParam("strategy") String strategy,Model model) {
		model.addAttribute("positions",committeeService.retrievePositionsForApplicant(username,strategy));
		model.addAttribute("studentUsername",username);	
		return "committee/assignPosition";
	}
	
	@GetMapping("/assignPosition")
	public String assignPosition(@RequestParam("positionId") Integer positionId,@RequestParam("studentUsername") String studentUsername) {
		committeeService.assignPosition(positionId,studentUsername);
		return "redirect:/committee/dashboard";
	}
	
	@GetMapping("/listAssignedTraineeshipsForSupervisor")
	public String listAssignedTraineeshipsForSupervisor(Model model) {
		model.addAttribute("positions",committeeService.retrievePositionsForSupervisor());
		return "committee/listPositionsForSupervisor";
	}
	
	@GetMapping("/assignSupervisor")
	public String assignSupervisor(@RequestParam("positionId") Integer positionId,@RequestParam("strategy") String strategy) {
	    committeeService.assignSupervisor(positionId, strategy);
	    return "redirect:/committee/dashboard";
	}
	
	@GetMapping("/showStudent")
	public String showStudent(@RequestParam("studentUsername") String studentUsername,@RequestParam("backUrl") String backUrl,Model model) {
	    model.addAttribute("student",committeeService.showStudentDetails(studentUsername));
	    model.addAttribute("backUrl",backUrl);
	    return "committee/viewProfileStudent";
	}
	
	@GetMapping("/listAssignedTraineeships")
	public String listAssignedTraineeships(Model model) {
		model.addAttribute("positions",committeeService.listAssignedTraineeships());
		model.addAttribute("backUrl","/committee/listAssignedTraineeships");
		return "committee/viewPositions";
	}
	
	@GetMapping("/listCompletedTraineeships")
	public String listCompletedTraineeships(Model model) {
		model.addAttribute("positions",committeeService.listCompletedTraineeships());
		model.addAttribute("backUrl","/committee/listCompletedTraineeships");
		return "committee/viewPositions";
	}
	
	@GetMapping("/completeAssignedTraineeships")
	public String completeAssignedTraineeships(Model model,@RequestParam("positionId") Integer positionId, @RequestParam("passFailGrade") boolean passFailGrade) {
		committeeService.completeAssignedTraineeships(positionId, passFailGrade);
		return "redirect:/committee/listAssignedTraineeships";
	}
	
	@GetMapping("/interests")
    public String findAllInterests(Model model) {
		model.addAttribute("interests",dataService.findAllInterests());
		return "committee/listInterests";
    }

    @GetMapping("/interestShowFormForUpdate")
    public String findInterestById(Model model,@RequestParam("id") Integer id) {
    	model.addAttribute("interest",dataService.findByIdInterest(id));
    	return "committee/interestForm";
    }
    
    @GetMapping("/interestShowFormForAdd")
    public String interestShowFormForAdd(Model model) {
    	model.addAttribute("interest", new Interest());
    	return "committee/interestForm";
    }

    @PostMapping("/interests")
    public String saveInterest(Interest interest) {
        dataService.saveInterest(interest);
        return "redirect:/committee/interests";
    }
    
    @GetMapping("/skills")
    public String findAllSkills(Model model) {
		model.addAttribute("skills",dataService.findAllSkills());
		return "committee/listSkills";
    }

    @GetMapping("/skillShowFormForUpdate")
    public String findSkillById(Model model,@RequestParam("id") Integer id) {
    	model.addAttribute("skill", dataService.findByIdSkill(id));
    	return "committee/skillForm";
    }
    
    @GetMapping("/skillShowFormForAdd")
    public String skillShowFormForAdd(Model model) {
    	model.addAttribute("skill",new Skill());
    	return "committee/skillForm";
    }

    @PostMapping("/skills")
    public String saveSkill(Skill skill) {
        dataService.saveSkill(skill);
        return "redirect:/committee/skills";
    }
    
    @GetMapping("/locations")
    public String findAllLocations(Model model) {
		model.addAttribute("locations",dataService.findAllLocations());
		return "committee/listLocations";
    }

    @GetMapping("/locationShowFormForUpdate")
    public String findLocationById(Model model,@RequestParam("id") Integer id) {
    	model.addAttribute("location",dataService.findByIdLocation(id));
    	return "committee/locationForm";
    }
    
    @GetMapping("/locationShowFormForAdd")
    public String locationShowFormForAdd(Model model) {
    	model.addAttribute("location",new Location());
    	return "committee/locationForm";
    }

    @PostMapping("/locations")
    public String saveLocation(Location location) {
        dataService.saveLocation(location);
        return "redirect:/committee/locations";
    }
}
