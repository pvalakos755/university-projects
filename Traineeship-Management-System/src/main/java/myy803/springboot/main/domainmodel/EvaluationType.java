package myy803.springboot.main.domainmodel;

public enum EvaluationType {
	FROM_COMPANY("FromCompany"),
    FROM_PROFESSOR("FromProfessor");

    private final String value;

    private EvaluationType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
