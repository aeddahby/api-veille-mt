package ma.iam.wissal.veille.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;

/**
 * A Attachment.
 */
@Entity
@Table(name = "attachment")
public class Attachment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Lob
    @Column(name = "attach")
    private byte[] attach;

    @Column(name = "attach_content_type")
    private String attachContentType;

    @ManyToOne
    @JsonIgnoreProperties(value = { "directionRegionale", "category", "entity", "attachments" }, allowSetters = true)
    private Ticket ticket;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Attachment id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Attachment name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getAttach() {
        return this.attach;
    }

    public Attachment attach(byte[] attach) {
        this.setAttach(attach);
        return this;
    }

    public void setAttach(byte[] attach) {
        this.attach = attach;
    }

    public String getAttachContentType() {
        return this.attachContentType;
    }

    public Attachment attachContentType(String attachContentType) {
        this.attachContentType = attachContentType;
        return this;
    }

    public void setAttachContentType(String attachContentType) {
        this.attachContentType = attachContentType;
    }

    public Ticket getTicket() {
        return this.ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Attachment ticket(Ticket ticket) {
        this.setTicket(ticket);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Attachment)) {
            return false;
        }
        return id != null && id.equals(((Attachment) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Attachment{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", attach='" + getAttach() + "'" +
            ", attachContentType='" + getAttachContentType() + "'" +
            "}";
    }
}
