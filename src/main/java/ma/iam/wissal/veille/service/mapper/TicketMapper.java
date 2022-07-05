package ma.iam.wissal.veille.service.mapper;

import ma.iam.wissal.veille.domain.Category;
import ma.iam.wissal.veille.domain.DirectionRegionale;
import ma.iam.wissal.veille.domain.EntityM;
import ma.iam.wissal.veille.domain.Ticket;
import ma.iam.wissal.veille.service.dto.CategoryDTO;
import ma.iam.wissal.veille.service.dto.DirectionRegionaleDTO;
import ma.iam.wissal.veille.service.dto.EntityMDTO;
import ma.iam.wissal.veille.service.dto.TicketDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Ticket} and its DTO {@link TicketDTO}.
 */
@Mapper(componentModel = "spring")
public interface TicketMapper extends EntityMapper<TicketDTO, Ticket> {
    @Mapping(target = "directionRegionale", source = "directionRegionale", qualifiedByName = "directionRegionaleName")
    @Mapping(target = "category", source = "category", qualifiedByName = "categoryName")
    @Mapping(target = "entity", source = "entity", qualifiedByName = "entityMTitle")
    TicketDTO toDto(Ticket s);

    @Named("directionRegionaleName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    DirectionRegionaleDTO toDtoDirectionRegionaleName(DirectionRegionale directionRegionale);

    @Named("categoryName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    CategoryDTO toDtoCategoryName(Category category);

    @Named("entityMTitle")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "title", source = "title")
    EntityMDTO toDtoEntityMTitle(EntityM entityM);
}
