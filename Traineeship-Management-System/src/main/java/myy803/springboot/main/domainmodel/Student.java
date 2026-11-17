package myy803.springboot.main.domainmodel;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="students")
public class Student {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="username", unique=true)
	private String username;
	
	@Column(name="name")
	private String name;
	
	@Column(name="universityIdNumber")
	private String universityIdNumber;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="preferredLocationId")
	private Location preferredLocation;
	
	@ManyToMany(fetch = FetchType.EAGER, 
			    cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinTable(
            name = "studentInterests",
            joinColumns = @JoinColumn(
                    name = "student_id", referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "interest_id", referencedColumnName = "id"
            )
    )
	private List<Interest> interests;
	
	@ManyToMany(fetch = FetchType.EAGER, 
		        cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
	@JoinTable(
			name = "studentSkills",
	        joinColumns = @JoinColumn(
	                name = "student_id", referencedColumnName = "id"
	        ),
	        inverseJoinColumns = @JoinColumn(
	                name = "skill_id", referencedColumnName = "id"
	        )
	)
	private List<Skill> skills;
	
	@Enumerated(EnumType.STRING)
    @Column(name="lookingForTraineeship")
	private LookingForTraineeshipType lookingForTraineeship;
	
	@OneToOne(mappedBy = "student")
	private TraineeshipPosition assignedTraineeship;
	
	public Student() {
	}
	
	public Student(String username,LookingForTraineeshipType lookingForTraineeship) {
		this.username=username;
		this.lookingForTraineeship=lookingForTraineeship;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUniversityIdNumber() {
		return universityIdNumber;
	}

	public void setUniversityIdNumber(String universityIdNumber) {
		this.universityIdNumber = universityIdNumber;
	}

	public Location getPreferredLocation() {
		return preferredLocation;
	}

	public void setPreferredLocation(Location preferredLocation) {
		this.preferredLocation = preferredLocation;
	}

	public List<Interest> getInterests() {
		return interests;
	}

	public void setInterests(List<Interest> interests) {
		this.interests = interests;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	public LookingForTraineeshipType getLookingForTraineeship() {
		return lookingForTraineeship;
	}

	public void setLookingForTraineeship(LookingForTraineeshipType lookingForTraineeship) {
		this.lookingForTraineeship = lookingForTraineeship;
	}

	public TraineeshipPosition getAssignedTraineeship() {
		return assignedTraineeship;
	}

	public void setAssignedTraineeship(TraineeshipPosition assignedTraineeship) {
		this.assignedTraineeship = assignedTraineeship;
	}
}
