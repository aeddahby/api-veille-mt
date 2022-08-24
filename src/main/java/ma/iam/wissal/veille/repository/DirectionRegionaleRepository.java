package ma.iam.wissal.veille.repository;

import ma.iam.wissal.veille.domain.DirectionRegionale;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the DirectionRegionale entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DirectionRegionaleRepository extends JpaRepository<DirectionRegionale, Long> {

}
