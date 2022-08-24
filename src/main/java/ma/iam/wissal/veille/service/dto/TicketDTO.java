package ma.iam.wissal.veille.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Lob;

import ma.iam.wissal.veille.domain.Attachment;
import ma.iam.wissal.veille.domain.enumeration.StateTicket;
import ma.iam.wissal.veille.domain.enumeration.Status;

/**
 * A DTO for the {@link ma.iam.wissal.veille.domain.Ticket} entity.
 */
public class TicketDTO implements Serializable {

    private Long id;

    private String object;

    private String description;

    private Instant creationDate;

    private Instant modificationDate;

    private Instant closedDate;

    private String contributor;

    private Boolean contributorVisibility;

    private Boolean entityVisibility;

    private Boolean directionVisibility;

    private String centralAnimator;

    private String centralRelay;

    private String regionalRelay;

    private StateTicket stateTicket;

    private Status statusTicket;

    private Boolean pertinence;

    private DirectionRegionaleDTO directionRegionale;

    private CategoryDTO category;

    private EntityMDTO entity;
    
    private List<Attachment> attachments;
    
    private byte[] attach;

    private String attachContentType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public Instant getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Instant modificationDate) {
        this.modificationDate = modificationDate;
    }

    public Instant getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(Instant closedDate) {
        this.closedDate = closedDate;
    }

    public String getContributor() {
        return contributor;
    }

    public void setContributor(String contributor) {
        this.contributor = contributor;
    }

    public Boolean getContributorVisibility() {
        return contributorVisibility;
    }

    public void setContributorVisibility(Boolean contributorVisibility) {
        this.contributorVisibility = contributorVisibility;
    }

    public Boolean getEntityVisibility() {
        return entityVisibility;
    }

    public void setEntityVisibility(Boolean entityVisibility) {
        this.entityVisibility = entityVisibility;
    }

    public Boolean getDirectionVisibility() {
        return directionVisibility;
    }

    public void setDirectionVisibility(Boolean directionVisibility) {
        this.directionVisibility = directionVisibility;
    }

    public String getCentralAnimator() {
        return centralAnimator;
    }

    public void setCentralAnimator(String centralAnimator) {
        this.centralAnimator = centralAnimator;
    }

    public String getCentralRelay() {
        return centralRelay;
    }

    public void setCentralRelay(String centralRelay) {
        this.centralRelay = centralRelay;
    }

    public String getRegionalRelay() {
        return regionalRelay;
    }

    public void setRegionalRelay(String regionalRelay) {
        this.regionalRelay = regionalRelay;
    }

    public StateTicket getStateTicket() {
        return stateTicket;
    }

    public void setStateTicket(StateTicket stateTicket) {
        this.stateTicket = stateTicket;
    }

    public Status getStatusTicket() {
        return statusTicket;
    }

    public void setStatusTicket(Status statusTicket) {
        this.statusTicket = statusTicket;
    }

    public Boolean getPertinence() {
        return pertinence;
    }

    public void setPertinence(Boolean pertinence) {
        this.pertinence = pertinence;
    }

    public DirectionRegionaleDTO getDirectionRegionale() {
        return directionRegionale;
    }

    public void setDirectionRegionale(DirectionRegionaleDTO directionRegionale) {
        this.directionRegionale = directionRegionale;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public EntityMDTO getEntity() {
        return entity;
    }

    public void setEntity(EntityMDTO entity) {
        this.entity = entity;
    }
    
    public byte[] getAttach() {
		return attach;
	}

	public void setAttach(byte[] attach) {
		this.attach = attach;
	}

	public String getAttachContentType() {
		return attachContentType;
	}

	public void setAttachContentType(String attachContentType) {
		this.attachContentType = attachContentType;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TicketDTO)) {
            return false;
        }

        TicketDTO ticketDTO = (TicketDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, ticketDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TicketDTO{" +
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
            ", directionRegionale=" + getDirectionRegionale() +
            ", category=" + getCategory() +
            ", entity=" + getEntity() +
            "}";
    }
}
