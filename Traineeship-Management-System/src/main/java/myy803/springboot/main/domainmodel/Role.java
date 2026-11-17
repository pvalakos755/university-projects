package myy803.springboot.main.domainmodel;

public enum Role {
	STUDENT("Student"),
	PROFESSOR("Professor"),
	COMPANY("Company"),
    TRAINEESHIP_COMMITTEE("TraineeshipCommittee");

    private final String value;

    private Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
