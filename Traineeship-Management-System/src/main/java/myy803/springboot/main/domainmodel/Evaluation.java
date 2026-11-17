package myy803.springboot.main.domainmodel;

import java.util.List;

import jakarta.persistence.*;
import myy803.springboot.main.domainmodel.EvaluationType;
import myy803.springboot.main.domainmodel.TraineeshipPosition;

@Entity
@Table(name="evaluations")
public class Evaluation {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Enumerated(EnumType.STRING)
    @Column(name="evaluationType")
	private EvaluationType evaluationType;
	
	@Column(name="motivation")
	private int motivation;
	
	@Column(name="efficiency")
	private int efficiency;
	
	@Column(name="effectiveness")
	private int effectiveness;
	
	@Column(name="facilities")
	private int facilities;
	
	@Column(name="guidance")
	private int guidance;
	
	@ManyToOne
	@JoinColumn(name = "traineeshipPositionId")
	private TraineeshipPosition position;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EvaluationType getEvaluationType() {
		return evaluationType;
	}

	public void setEvaluationType(EvaluationType evaluationType) {
		this.evaluationType = evaluationType;
	}

	public int getMotivation() {
		return motivation;
	}

	public void setMotivation(int motivation) {
		this.motivation = motivation;
	}

	public int getEfficiency() {
		return efficiency;
	}

	public void setEfficiency(int efficiency) {
		this.efficiency = efficiency;
	}

	public int getEffectiveness() {
		return effectiveness;
	}

	public void setEffectiveness(int effectiveness) {
		this.effectiveness = effectiveness;
	}

	public int getFacilities() {
		return facilities;
	}

	public void setFacilities(int facilities) {
		this.facilities = facilities;
	}

	public int getGuidance() {
		return guidance;
	}

	public void setGuidance(int guidance) {
		this.guidance = guidance;
	}

	public TraineeshipPosition getPosition() {
		return position;
	}

	public void setPosition(TraineeshipPosition position) {
		this.position = position;
	}
}
