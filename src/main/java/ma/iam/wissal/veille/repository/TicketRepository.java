package ma.iam.wissal.veille.repository;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import ma.iam.wissal.veille.domain.Ticket;
import ma.iam.wissal.veille.domain.enumeration.Status;
import ma.iam.wissal.veille.service.dto.Statistic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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
        value = "select distinct ticket from Ticket ticket left join fetch ticket.directionRegionale left join fetch ticket.category left join fetch ticket.entity order by ticket.statusTicket",
        countQuery = "select count(distinct ticket) from Ticket ticket"
    )
    Page<Ticket> findAllWithToOneRelationships(Pageable pageable);

    @Query(
        "select distinct ticket from Ticket ticket left join fetch ticket.directionRegionale left join fetch ticket.category left join fetch ticket.entity order by ticket.statusTicket"
    )
    List<Ticket> findAllWithToOneRelationships();

    @Query(
        "select ticket from Ticket ticket left join fetch ticket.directionRegionale left join fetch ticket.category left join fetch ticket.entity where ticket.id =:id"
    )
    Optional<Ticket> findOneWithToOneRelationships(@Param("id") Long id);

    Page<Ticket> findAllByContributorOrderByStatusTicketDesc(Pageable pageable, Optional<String> currentUserLogin);

    Page<Ticket> findByOrderByStatusTicketDesc(Pageable pageable);

    Page<Ticket> findAllByCentralRelayAndStatusTicket(Pageable pageable, Optional<String> currentUserLogin, Status confirmed);

    Page<Ticket> findAllByRegionalRelayAndStatusTicket(Pageable pageable, Optional<String> currentUserLogin, Status confirmed);

    /*	Page<Ticket> findAllByEntityID(Pageable pageable, Optional<String> currentUserLogin);*/

    @Query(
        value = "select  category.name as col1, count(case when pertinence = 1 then 1 else null end)as col2, " +
        "count(case when pertinence = 0 then 1 else null end)as col3 " +
        "from ticket " +
        "inner join category ON ticket.category_id = category.id " +
        "and creation_date >= :startDate and creation_date <= :finishDate " +
        "group by category.name, " +
        "ticket.category_id, " +
        "category.id ",
        nativeQuery = true
    )
    List<Statistic> getStatisticsOrderByCategory(@Param("startDate") LocalDate startDate, @Param("finishDate") LocalDate finishDate);

    @Query(
        value = "select  direction_regionale.name as col1, count(case when pertinence = 1 then 1 else null end) as col2, " +
        "count(case when pertinence = 0 then 1 else null end) as col3 " +
        "from ticket " +
        "inner join direction_regionale ON ticket.direction_regionale_id = direction_regionale.id " +
        "group by direction_regionale.name, " +
        "ticket.direction_regionale_id, " +
        "direction_regionale.id",
        nativeQuery = true
    )
    List<Statistic> getTicketsByDirection(Instant startDate, Instant finishDate);

    @Query(
        value = "select  contributor as col1, count(case when pertinence = 1 then 1 else null end)as col2, " +
        "count(case when pertinence = 0 then 1 else null end)as col3 " +
        "from ticket " +
        "group by contributor",
        nativeQuery = true
    )
    List<Statistic> getTicketsByContributor(Instant startDate, Instant finishDate);

    @Query(
        value = "select direction_regionale.name as col1, count(case when state_ticket like 'OPENED' then 1 else null end) as col2, " +
        "count(case when state_ticket like 'CLOSED' then 1 else null end) as col3 " +
        "from ticket " +
        "inner join direction_regionale ON ticket.direction_regionale_id = direction_regionale.id " +
        "group by direction_regionale.name, " +
        "ticket.direction_regionale_id, " +
        "direction_regionale.id",
        nativeQuery = true
    )
    List<Statistic> getTicketsVolumeByDirection(Instant startDate, Instant finishDate);

    @Query(
        value = "select  category.name col1, count(ticket.CATEGORY_ID) col2 " +
        "from ticket " +
        "inner join category ON ticket.category_id = category.id " +
        "group by category.name, " +
        "ticket.category_id, " +
        "category.id",
        nativeQuery = true
    )
    List<Statistic> getTicketsVolumeByCategory(Instant startDate, Instant finishDate);
}
