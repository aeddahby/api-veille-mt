package ma.iam.wissal.veille.domain.enumeration;

/**
 * The Status enumeration.
 */
public enum Status {
    CREATED("Created"),
    IN_PROGRESS("InProgress"),
    CONFIRMED("Confirmed");

    private final String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
