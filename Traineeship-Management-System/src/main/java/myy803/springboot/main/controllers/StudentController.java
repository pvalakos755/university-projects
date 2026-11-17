package myy803.springboot.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import myy803.springboot.main.domainmodel.LookingForTraineeshipType;
import myy803.springboot.main.domainmodel.Student;
import myy803.springboot.main.domainmodel.TraineeshipPosition;
import myy803.springboot.main.services.interfaces.DataService;
import myy803.springboot.main.services.interfaces.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {
	@Autowired
	private StudentService studentService;
	@Autowired
	private DataService dataService;
	
	@Autowired
	public StudentController(StudentService studentService,DataService dataService) {
		this.studentService=studentService;
		this.dataService=dataService;
	}
	
	@GetMapping("/dashboard")
	public String getStudentDashboard(Model model,Authentication authentication) {
		model.addAttribute("student",studentService.retrieveProfile(authentication.getName()));
		return "student/dashboard";
	}
	
	@GetMapping("/studentShowFormForAdd")
	public String getFormForAdd(Model model,Authentication authentication) {
		model.addAttribute("student",new Student(authentication.getName(), LookingForTraineeshipType.NOT_LOOKING));
		model.addAttribute("interests",dataService.findAllInterests());
		model.addAttribute("skills",dataService.findAllSkills());
		model.addAttribute("locations",dataService.findAllLocations());
		return "student/profileForm";
	}
	
	@PostMapping("/save")
	public String saveProfile(Student student,Model model) {
		studentService.saveProfile(student);
		return "redirect:/student/dashboard";
	}
	
	@GetMapping("/viewProfile")
	public String viewProfile(Model model,Authentication authentication) {
		model.addAttribute("student",studentService.retrieveProfile(authentication.getName()));
		return "student/viewProfile";
	}
	
	@GetMapping("/interestForPosition")
	public String saveInterestForPosition(Authentication authentication) {
		studentService.saveInterestForPosition(authentication.getName());
		return "redirect:/student/dashboard";
	}
	
	@GetMapping("/viewPosition")
	public String retrieveAssignedPosition(Model model,Authentication authentication) {
		Student student=studentService.retrieveProfile(authentication.getName());
		model.addAttribute("position",student.getAssignedTraineeship());
		return "student/viewPosition";
	}
	
	@GetMapping("/fillLogbook")
	public String fillLogbook(Model model,Authentication authentication) {
		Student student=studentService.retrieveProfile(authentication.getName());
		model.addAttribute("position",student.getAssignedTraineeship());
		return "student/logbookForm";
	}
	
	@PostMapping("/saveLogbook")
	public String saveLogbook(TraineeshipPosition position,Authentication authentication) {
		studentService.saveLogbook(position,authentication.getName());
		return "redirect:/student/viewPosition";
	}
}
