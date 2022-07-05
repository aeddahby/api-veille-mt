package ma.iam.wissal.veille.domain.enumeration;

/**
 * The StateTicket enumeration.
 */
public enum StateTicket {
    OPENED("Opened"),
    CLOSED("Closed");

    private final String value;

    StateTicket(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
