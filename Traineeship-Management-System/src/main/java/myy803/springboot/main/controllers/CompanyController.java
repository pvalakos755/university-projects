package myy803.springboot.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import myy803.springboot.main.domainmodel.Company;
import myy803.springboot.main.domainmodel.Evaluation;
import myy803.springboot.main.domainmodel.TraineeshipPosition;
import myy803.springboot.main.services.interfaces.CompanyService;
import myy803.springboot.main.services.interfaces.DataService;

@Controller
@RequestMapping("/company")
public class CompanyController {
	@Autowired
	private CompanyService companyService;
	@Autowired
	private DataService dataService;
	
	@Autowired
	public CompanyController(CompanyService companyService,DataService dataService) {
		this.companyService=companyService;
		this.dataService=dataService;
	}
	
	@GetMapping("/dashboard")
	public String getCompanyDashboard(Model model,Authentication authentication) {
		model.addAttribute("company",companyService.retrieveProfile(authentication.getName()));
		return "company/dashboard";
	}
	
	@GetMapping("/companyShowFormForAdd")
	public String getFormForAdd(Model model,Authentication authentication) {
		model.addAttribute("company",new Company(authentication.getName()));
		model.addAttribute("locations",dataService.findAllLocations());
		return "company/profileForm";
	}
	
	@PostMapping("/save")
	public String saveProfile(Company company,Model model) {
		companyService.saveProfile(company);
		return "redirect:/company/dashboard";
	}
	
	@GetMapping("/viewProfile")
	public String viewProfile(Model model,Authentication authentication) {
		model.addAttribute("company",companyService.retrieveProfile(authentication.getName()));
		return "company/viewProfile";
	}
	
	@GetMapping("/positionShowFormForAdd")
	public String getPositionsFormForAdd(Model model,Authentication authentication) {
		model.addAttribute("position",new TraineeshipPosition());
		model.addAttribute("skills",dataService.findAllSkills());
		model.addAttribute("interests",dataService.findAllInterests());
		return "company/positionForm";
	}
	
	@PostMapping("/savePosition")
	public String savePosition(TraineeshipPosition position,Model model,Authentication authentication) {
		companyService.savePosition(position,authentication.getName());
		return "redirect:/company/dashboard";
	}
	
	@GetMapping("/availablePositions")
	public String getAvailablePositions(Model model,Authentication authentication) {
		model.addAttribute("positions",companyService.retrieveAvailablePositions(authentication.getName()));
		return "company/viewPositions";
	}
	
	@GetMapping("/deletePosition")
	public String deletePosition(@RequestParam("positionId") int positionId) {
		companyService.deletePosition(positionId);
		return "redirect:/company/availablePositions";
	}
	
	@GetMapping("/assignedPositions")
	public String getAssignedPositions(Model model,Authentication authentication) {
		model.addAttribute("positions",companyService.retrieveAssignedPositions(authentication.getName()));
		return "company/viewPositions";
	}
	
	@GetMapping("/evaluateAssignedTraineeship")
	public String evaluateAssignedTraineeship(@RequestParam("positionId") int positionId,Model model) {
		model.addAttribute("evaluation",new Evaluation());
		model.addAttribute("positionId",positionId);
		return "company/evaluatePosition";
	}
	
	@PostMapping("/saveEvaluation")
	public String saveEvaluation(@RequestParam("positionId") int positionId, Evaluation evaluation) {
		companyService.saveEvaluation(positionId,evaluation);
		return "redirect:/company/assignedPositions";
	}
	
	@GetMapping("/showStudent")
	public String showStudent(@RequestParam("studentUsername") String studentUsername,@RequestParam("backUrl") String backUrl,Model model) {
	    model.addAttribute("student",companyService.showStudentDetails(studentUsername));
	    model.addAttribute("backUrl",backUrl);
	    return "committee/viewProfileStudent";
	}
}
