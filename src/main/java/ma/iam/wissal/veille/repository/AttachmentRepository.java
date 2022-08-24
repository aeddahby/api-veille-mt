package ma.iam.wissal.veille.repository;

import ma.iam.wissal.veille.domain.Attachment;
import ma.iam.wissal.veille.domain.Ticket;
import ma.iam.wissal.veille.service.dto.AttachmentDTO;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Attachment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Long> {

	Optional<Attachment> findByTicket(Ticket ticket);

}
