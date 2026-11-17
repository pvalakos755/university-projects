package myy803.springboot.main.domainmodel;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="professors")
public class Professor {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="username", unique=true)
	private String username;
	
	@Column(name="name")
	private String name;
	
	@ManyToMany(fetch = FetchType.EAGER, 
		    	cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
	@JoinTable(
	        name = "professorInterests",
	        joinColumns = @JoinColumn(
	                name = "professor_id", referencedColumnName = "id"
	        ),
	        inverseJoinColumns = @JoinColumn(
	                name = "interest_id", referencedColumnName = "id"
	        )
	)
	private List<Interest> interests;
	
	@OneToMany(mappedBy = "supervisor", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<TraineeshipPosition> supervisedPositions;
	
	public Professor() {
	}
	
	public Professor(String username) {
		this.username=username;
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

	public List<Interest> getInterests() {
		return interests;
	}

	public void setInterests(List<Interest> interests) {
		this.interests = interests;
	}

	public List<TraineeshipPosition> getSupervisedPositions() {
		return supervisedPositions;
	}

	public void setSupervisedPositions(List<TraineeshipPosition> supervisedPositions) {
		this.supervisedPositions = supervisedPositions;
	}
}
