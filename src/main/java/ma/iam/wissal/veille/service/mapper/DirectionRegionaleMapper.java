package ma.iam.wissal.veille.service.mapper;

import ma.iam.wissal.veille.domain.DirectionRegionale;
import ma.iam.wissal.veille.service.dto.DirectionRegionaleDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DirectionRegionale} and its DTO {@link DirectionRegionaleDTO}.
 */
@Mapper(componentModel = "spring")
public interface DirectionRegionaleMapper extends EntityMapper<DirectionRegionaleDTO, DirectionRegionale> {}
