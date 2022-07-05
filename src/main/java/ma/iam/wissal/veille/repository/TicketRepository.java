package ma.iam.wissal.veille.repository;

import java.util.List;
import java.util.Optional;
import ma.iam.wissal.veille.domain.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Ticket entity.
 */
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    default Optional<Ticket> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<Ticket> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<Ticket> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select distinct ticket from Ticket ticket left join fetch ticket.directionRegionale left join fetch ticket.category left join fetch ticket.entity",
        countQuery = "select count(distinct ticket) from Ticket ticket"
    )
    Page<Ticket> findAllWithToOneRelationships(Pageable pageable);

    @Query(
        "select distinct ticket from Ticket ticket left join fetch ticket.directionRegionale left join fetch ticket.category left join fetch ticket.entity"
    )
    List<Ticket> findAllWithToOneRelationships();

    @Query(
        "select ticket from Ticket ticket left join fetch ticket.directionRegionale left join fetch ticket.category left join fetch ticket.entity where ticket.id =:id"
    )
    Optional<Ticket> findOneWithToOneRelationships(@Param("id") Long id);
}
