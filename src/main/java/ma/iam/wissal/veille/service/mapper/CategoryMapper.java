package ma.iam.wissal.veille.service.mapper;

import ma.iam.wissal.veille.domain.Category;
import ma.iam.wissal.veille.service.dto.CategoryDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Category} and its DTO {@link CategoryDTO}.
 */
@Mapper(componentModel = "spring")
public interface CategoryMapper extends EntityMapper<CategoryDTO, Category> {}
