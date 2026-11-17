package myy803.springboot.main.domainmodel;

public enum LookingForTraineeshipType {
	NOT_LOOKING("NotLooking"),
    LOOKING("Looking"),
    ASSIGNED("Assigned");

    private final String value;

    private LookingForTraineeshipType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
