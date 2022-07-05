package ma.iam.wissal.veille.service.mapper;

import ma.iam.wissal.veille.domain.Attachment;
import ma.iam.wissal.veille.domain.Ticket;
import ma.iam.wissal.veille.service.dto.AttachmentDTO;
import ma.iam.wissal.veille.service.dto.TicketDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Attachment} and its DTO {@link AttachmentDTO}.
 */
@Mapper(componentModel = "spring")
public interface AttachmentMapper extends EntityMapper<AttachmentDTO, Attachment> {
    @Mapping(target = "ticket", source = "ticket", qualifiedByName = "ticketId")
    AttachmentDTO toDto(Attachment s);

    @Named("ticketId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    TicketDTO toDtoTicketId(Ticket ticket);
}
