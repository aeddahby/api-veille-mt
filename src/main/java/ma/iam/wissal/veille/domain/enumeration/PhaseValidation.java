package ma.iam.wissal.veille.domain.enumeration;

/**
 * The Status enumeration.
 */
public enum PhaseValidation {
    CREATED("Created"),
    MODIFICATION_CONTRIBUTEUR("Modification Contributeur"),
    VALIDATION_ANIMATEUR_CENTRALE("Validation Animteur Central"),
    VALIDATION_RELAIS_CENTRALE("Validation Relais Central"),
    SUPPRESSION_CONTRIBUTEUR("Suppression Contributeur"),
    VALIDATION_RELAIS_REGIONALE("Validation Relais Regional");

    private final String value;

    PhaseValidation(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
