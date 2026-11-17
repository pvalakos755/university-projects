package myy803.springboot.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import myy803.springboot.main.domainmodel.Evaluation;
import myy803.springboot.main.domainmodel.Professor;
import myy803.springboot.main.services.interfaces.DataService;
import myy803.springboot.main.services.interfaces.ProfessorService;

@Controller
@RequestMapping("/professor")
public class ProfessorController {
	@Autowired
	private ProfessorService professorService;
	@Autowired
	private DataService dataService;
	
	@Autowired
	public ProfessorController(ProfessorService professorService,DataService dataService) {
		this.professorService=professorService;
		this.dataService=dataService;
	}
	
	@GetMapping("/dashboard")
	public String getProfessorDashboard(Model model,Authentication authentication) {
		model.addAttribute("professor",professorService.retrieveProfile(authentication.getName()));
		return "professor/dashboard";
	}
	
	@GetMapping("/professorShowFormForAdd")
	public String getFormForAdd(Model model,Authentication authentication) {
		model.addAttribute("professor",new Professor(authentication.getName()));
		model.addAttribute("interests",dataService.findAllInterests());
		return "professor/profileForm";
	}
	
	@PostMapping("/save")
	public String saveProfile(Professor professor,Model model) {
		professorService.saveProfile(professor);
		return "redirect:/professor/dashboard";
	}
	
	@GetMapping("/viewProfile")
	public String viewProfile(Model model,Authentication authentication) {
		model.addAttribute("professor",professorService.retrieveProfile(authentication.getName()));
		return "professor/viewProfile";
	}
	
	@GetMapping("/listAssignedTraineeships")
	public String listAssignedTraineeships(Model model,Authentication authentication) {
		model.addAttribute("positions", professorService.retrieveAssignedPositions(authentication.getName()));
		return "professor/viewPositions";
	}
	
	@GetMapping("/evaluateAssignedTraineeship")
	public String evaluateAssignedTraineeship(@RequestParam("positionId") int positionId,Model model) {
		model.addAttribute("evaluation",new Evaluation());
		model.addAttribute("positionId",positionId);
		return "professor/evaluatePosition";
	}
	
	@PostMapping("/saveEvaluation")
	public String saveEvaluation(@RequestParam("positionId") int positionId, Evaluation evaluation) {
		professorService.saveEvaluation(positionId,evaluation);
		return "redirect:/professor/listAssignedTraineeships";
	}
	
	@GetMapping("/showStudent")
	public String showStudent(@RequestParam("studentUsername") String studentUsername,@RequestParam("backUrl") String backUrl,Model model) {
	    model.addAttribute("student",professorService.showStudentDetails(studentUsername));
	    model.addAttribute("backUrl",backUrl);
	    return "professor/viewProfileStudent";
	}
}
