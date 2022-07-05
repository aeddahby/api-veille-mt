package ma.iam.wissal.veille.service.mapper;

import ma.iam.wissal.veille.domain.EntityM;
import ma.iam.wissal.veille.service.dto.EntityMDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EntityM} and its DTO {@link EntityMDTO}.
 */
@Mapper(componentModel = "spring")
public interface EntityMMapper extends EntityMapper<EntityMDTO, EntityM> {}
