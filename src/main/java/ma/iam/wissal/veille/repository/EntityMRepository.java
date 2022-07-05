package ma.iam.wissal.veille.repository;

import ma.iam.wissal.veille.domain.EntityM;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the EntityM entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EntityMRepository extends JpaRepository<EntityM, Long> {}
