package myy803.springboot.main.domainmodel;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="companies")
public class Company {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="username", unique=true)
	private String username;
	
	@Column(name="name")
	private String name;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="locationId")
	private Location location;
	
	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<TraineeshipPosition> positions;
	
	public Company() {
	}
	
	public Company(String username) {
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

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public List<TraineeshipPosition> getPositions() {
		return positions;
	}

	public void setPositions(List<TraineeshipPosition> positions) {
		this.positions = positions;
	}
}
