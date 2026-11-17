package myy803.springboot.main.domainmodel;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="traineeshipPositions")
public class TraineeshipPosition {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="description")
	private String description;
	
	@Column(name="fromDate")
	private LocalDate fromDate;
	
	@Column(name="toDate")
	private LocalDate toDate;
	
	@ManyToMany(fetch = FetchType.EAGER, 
		    cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
	@JoinTable(
	        name = "traineeshipPositionsTopics",
	        joinColumns = @JoinColumn(
	                name = "traineeship_position_id", referencedColumnName = "id"
	        ),
	        inverseJoinColumns = @JoinColumn(
	                name = "topic_id", referencedColumnName = "id"
	        )
	)
	private List<Interest> topics;

	@ManyToMany(fetch = FetchType.EAGER, 
		        cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
	@JoinTable(
			name = "traineeshipPositionsSkills",
	        joinColumns = @JoinColumn(
	                name = "traineeship_position_id", referencedColumnName = "id"
	        ),
	        inverseJoinColumns = @JoinColumn(
	                name = "skill_id", referencedColumnName = "id"
	        )
	)
	private List<Skill> skills;
	
	@Column(name="isAssigned")
	private boolean isAssigned;
	
	@Column(name="studentLogbook")
	private String studentLogbook;
	
	@Column(name="passFailGrade")
	private boolean passFailGrade;
	
	@Column(name="isCompleted")
	private boolean isCompleted;
	
	@Column(name="isEvaluatedByCompany")
	private boolean isEvaluatedByCompany;
	
	@Column(name="isEvaluatedByProfessor")
	private boolean isEvaluatedByProfessor;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "studentId", referencedColumnName = "id")
	private Student student;
	
	@ManyToOne
    @JoinColumn(name = "supervisorId")
	private Professor supervisor;
	
	@ManyToOne
    @JoinColumn(name = "companyId")
	private Company company;
	
	@OneToMany(mappedBy = "position", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	List<Evaluation> evaluations;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}

	public LocalDate getToDate() {
		return toDate;
	}

	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}

	public List<Interest> getTopics() {
		return topics;
	}

	public void setTopics(List<Interest> topics) {
		this.topics = topics;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	public boolean isAssigned() {
		return isAssigned;
	}

	public void setAssigned(boolean isAssigned) {
		this.isAssigned = isAssigned;
	}

	public String getStudentLogbook() {
		return studentLogbook;
	}

	public void setStudentLogbook(String studentLogbook) {
		this.studentLogbook = studentLogbook;
	}

	public boolean isPassFailGrade() {
		return passFailGrade;
	}

	public void setPassFailGrade(boolean passFailGrade) {
		this.passFailGrade = passFailGrade;
	}

	public boolean isCompleted() {
		return isCompleted;
	}

	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	public boolean isEvaluatedByCompany() {
		return isEvaluatedByCompany;
	}

	public void setEvaluatedByCompany(boolean isEvaluatedByCompany) {
		this.isEvaluatedByCompany = isEvaluatedByCompany;
	}

	public boolean isEvaluatedByProfessor() {
		return isEvaluatedByProfessor;
	}

	public void setEvaluatedByProfessor(boolean isEvaluatedByProfessor) {
		this.isEvaluatedByProfessor = isEvaluatedByProfessor;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Professor getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Professor supervisor) {
		this.supervisor = supervisor;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public List<Evaluation> getEvaluations() {
		return evaluations;
	}

	public void setEvaluations(List<Evaluation> evaluations) {
		this.evaluations = evaluations;
	}
}
