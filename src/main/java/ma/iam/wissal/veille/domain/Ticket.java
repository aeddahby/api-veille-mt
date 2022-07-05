package ma.iam.wissal.veille.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import ma.iam.wissal.veille.domain.enumeration.StateTicket;
import ma.iam.wissal.veille.domain.enumeration.Status;

/**
 * A Ticket.
 */
@Entity
@Table(name = "ticket")
public class Ticket implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "jhi_object")
    private String object;

    @Column(name = "description")
    private String description;

    @Column(name = "creation_date")
    private Instant creationDate;

    @Column(name = "modification_date")
    private Instant modificationDate;

    @Column(name = "closed_date")
    private Instant closedDate;

    @Column(name = "contributor")
    private String contributor;

    @Column(name = "contributor_visibility")
    private Boolean contributorVisibility;

    @Column(name = "entity_visibility")
    private Boolean entityVisibility;

    @Column(name = "direction_visibility")
    private Boolean directionVisibility;

    @Column(name = "central_animator")
    private String centralAnimator;

    @Column(name = "central_relay")
    private String centralRelay;

    @Column(name = "regional_relay")
    private String regionalRelay;

    @Enumerated(EnumType.STRING)
    @Column(name = "state_ticket")
    private StateTicket stateTicket;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_ticket")
    private Status statusTicket;

    @Column(name = "pertinence")
    private Boolean pertinence;

    @OneToOne
    @JoinColumn(unique = true)
    private DirectionRegionale directionRegionale;

    @OneToOne
    @JoinColumn(unique = true)
    private Category category;

    @OneToOne
    @JoinColumn(unique = true)
    private EntityM entity;

    @OneToMany(mappedBy = "ticket")
    @JsonIgnoreProperties(value = { "ticket" }, allowSetters = true)
    private Set<Attachment> attachments = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Ticket id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getObject() {
        return this.object;
    }

    public Ticket object(String object) {
        this.setObject(object);
        return this;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getDescription() {
        return this.description;
    }

    public Ticket description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getCreationDate() {
        return this.creationDate;
    }

    public Ticket creationDate(Instant creationDate) {
        this.setCreationDate(creationDate);
        return this;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public Instant getModificationDate() {
        return this.modificationDate;
    }

    public Ticket modificationDate(Instant modificationDate) {
        this.setModificationDate(modificationDate);
        return this;
    }

    public void setModificationDate(Instant modificationDate) {
        this.modificationDate = modificationDate;
    }

    public Instant getClosedDate() {
        return this.closedDate;
    }

    public Ticket closedDate(Instant closedDate) {
        this.setClosedDate(closedDate);
        return this;
    }

    public void setClosedDate(Instant closedDate) {
        this.closedDate = closedDate;
    }

    public String getContributor() {
        return this.contributor;
    }

    public Ticket contributor(String contributor) {
        this.setContributor(contributor);
        return this;
    }

    public void setContributor(String contributor) {
        this.contributor = contributor;
    }

    public Boolean getContributorVisibility() {
        return this.contributorVisibility;
    }

    public Ticket contributorVisibility(Boolean contributorVisibility) {
        this.setContributorVisibility(contributorVisibility);
        return this;
    }

    public void setContributorVisibility(Boolean contributorVisibility) {
        this.contributorVisibility = contributorVisibility;
    }

    public Boolean getEntityVisibility() {
        return this.entityVisibility;
    }

    public Ticket entityVisibility(Boolean entityVisibility) {
        this.setEntityVisibility(entityVisibility);
        return this;
    }

    public void setEntityVisibility(Boolean entityVisibility) {
        this.entityVisibility = entityVisibility;
    }

    public Boolean getDirectionVisibility() {
        return this.directionVisibility;
    }

    public Ticket directionVisibility(Boolean directionVisibility) {
        this.setDirectionVisibility(directionVisibility);
        return this;
    }

    public void setDirectionVisibility(Boolean directionVisibility) {
        this.directionVisibility = directionVisibility;
    }

    public String getCentralAnimator() {
        return this.centralAnimator;
    }

    public Ticket centralAnimator(String centralAnimator) {
        this.setCentralAnimator(centralAnimator);
        return this;
    }

    public void setCentralAnimator(String centralAnimator) {
        this.centralAnimator = centralAnimator;
    }

    public String getCentralRelay() {
        return this.centralRelay;
    }

    public Ticket centralRelay(String centralRelay) {
        this.setCentralRelay(centralRelay);
        return this;
    }

    public void setCentralRelay(String centralRelay) {
        this.centralRelay = centralRelay;
    }

    public String getRegionalRelay() {
        return this.regionalRelay;
    }

    public Ticket regionalRelay(String regionalRelay) {
        this.setRegionalRelay(regionalRelay);
        return this;
    }

    public void setRegionalRelay(String regionalRelay) {
        this.regionalRelay = regionalRelay;
    }

    public StateTicket getStateTicket() {
        return this.stateTicket;
    }

    public Ticket stateTicket(StateTicket stateTicket) {
        this.setStateTicket(stateTicket);
        return this;
    }

    public void setStateTicket(StateTicket stateTicket) {
        this.stateTicket = stateTicket;
    }

    public Status getStatusTicket() {
        return this.statusTicket;
    }

    public Ticket statusTicket(Status statusTicket) {
        this.setStatusTicket(statusTicket);
        return this;
    }

    public void setStatusTicket(Status statusTicket) {
        this.statusTicket = statusTicket;
    }

    public Boolean getPertinence() {
        return this.pertinence;
    }

    public Ticket pertinence(Boolean pertinence) {
        this.setPertinence(pertinence);
        return this;
    }

    public void setPertinence(Boolean pertinence) {
        this.pertinence = pertinence;
    }

    public DirectionRegionale getDirectionRegionale() {
        return this.directionRegionale;
    }

    public void setDirectionRegionale(DirectionRegionale directionRegionale) {
        this.directionRegionale = directionRegionale;
    }

    public Ticket directionRegionale(DirectionRegionale directionRegionale) {
        this.setDirectionRegionale(directionRegionale);
        return this;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Ticket category(Category category) {
        this.setCategory(category);
        return this;
    }

    public EntityM getEntity() {
        return this.entity;
    }

    public void setEntity(EntityM entityM) {
        this.entity = entityM;
    }

    public Ticket entity(EntityM entityM) {
        this.setEntity(entityM);
        return this;
    }

    public Set<Attachment> getAttachments() {
        return this.attachments;
    }

    public void setAttachments(Set<Attachment> attachments) {
        if (this.attachments != null) {
            this.attachments.forEach(i -> i.setTicket(null));
        }
        if (attachments != null) {
            attachments.forEach(i -> i.setTicket(this));
        }
        this.attachments = attachments;
    }

    public Ticket attachments(Set<Attachment> attachments) {
        this.setAttachments(attachments);
        return this;
    }

    public Ticket addAttachment(Attachment attachment) {
        this.attachments.add(attachment);
        attachment.setTicket(this);
        return this;
    }

    public Ticket removeAttachment(Attachment attachment) {
        this.attachments.remove(attachment);
        attachment.setTicket(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Ticket)) {
            return false;
        }
        return id != null && id.equals(((Ticket) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Ticket{" +
            "id=" + getId() +
            ", object='" + getObject() + "'" +
            ", description='" + getDescription() + "'" +
            ", creationDate='" + getCreationDate() + "'" +
            ", modificationDate='" + getModificationDate() + "'" +
            ", closedDate='" + getClosedDate() + "'" +
            ", contributor='" + getContributor() + "'" +
            ", contributorVisibility='" + getContributorVisibility() + "'" +
            ", entityVisibility='" + getEntityVisibility() + "'" +
            ", directionVisibility='" + getDirectionVisibility() + "'" +
            ", centralAnimator='" + getCentralAnimator() + "'" +
            ", centralRelay='" + getCentralRelay() + "'" +
            ", regionalRelay='" + getRegionalRelay() + "'" +
            ", stateTicket='" + getStateTicket() + "'" +
            ", statusTicket='" + getStatusTicket() + "'" +
            ", pertinence='" + getPertinence() + "'" +
            "}";
    }
}
