package ma.iam.wissal.veille.service.impl;

import java.util.Optional;
import ma.iam.wissal.veille.domain.Ticket;
import ma.iam.wissal.veille.domain.User;
import ma.iam.wissal.veille.repository.TicketRepository;
import ma.iam.wissal.veille.service.MailService;
import ma.iam.wissal.veille.security.AuthoritiesConstants;
import ma.iam.wissal.veille.security.SecurityUtils;
import ma.iam.wissal.veille.service.DirectionRegionaleService;
import ma.iam.wissal.veille.service.TicketService;
import ma.iam.wissal.veille.service.UserService;
import ma.iam.wissal.veille.service.dto.TicketDTO;
import ma.iam.wissal.veille.service.mapper.TicketMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Ticket}.
 */
@Service
@Transactional
public class TicketServiceImpl implements TicketService {

    private final Logger log = LoggerFactory.getLogger(TicketServiceImpl.class);

    private final TicketRepository ticketRepository;

    private final TicketMapper ticketMapper;

    private MailService mailService;

    private UserService userService;

    @Autowired
    private DirectionRegionaleService directionRegionaleService;
    
    public TicketServiceImpl(
            TicketRepository ticketRepository,
            TicketMapper ticketMapper,
            MailService mailService,
            UserService userService
        ) {
        this.ticketRepository = ticketRepository;
        this.ticketMapper = ticketMapper;
        this.mailService = mailService;
        this.userService = userService;
    }

    @Override
    public TicketDTO save(TicketDTO ticketDTO) {
        log.debug("Request to save Ticket : {}", ticketDTO);
        Ticket ticket = ticketMapper.toEntity(ticketDTO);
        User recipient = userService.findOneByLogin(ticket.getCentralAnimator());
        ticket = ticketRepository.save(ticket);
        if (recipient != null) {
            mailService.sendTicketCreationEmail(recipient, ticket);
        }

        return ticketMapper.toDto(ticket);
    }

    @Override
    public TicketDTO update(TicketDTO ticketDTO) {
        log.debug("Request to save Ticket : {}", ticketDTO);

        Ticket ticket = ticketMapper.toEntity(ticketDTO);
        ticket = ticketRepository.save(ticket);
        /*User recipient = userService.findOneByLogin(ticket.getCentralAnimator());
        if (recipient != null) {
            mailService.sendTicketModificationEmail(recipient, ticket);
        }*/
        return ticketMapper.toDto(ticket);
    }

    @Override
    public Optional<TicketDTO> partialUpdate(TicketDTO ticketDTO) {
        log.debug("Request to partially update Ticket : {}", ticketDTO);

        return ticketRepository
            .findById(ticketDTO.getId())
            .map(existingTicket -> {
                ticketMapper.partialUpdate(existingTicket, ticketDTO);

                return existingTicket;
            })
            .map(ticketRepository::save)
            .map(ticketMapper::toDto);
    }

    @Override
    
    @Transactional(readOnly = true)
    public Page<TicketDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Tickets");
        
        if(SecurityUtils.hasCurrentUserThisAuthority(AuthoritiesConstants.ANIMATEUR_CENTRAL))
        	return ticketRepository.findAll(pageable).map(ticketMapper::toDto);
        
        if(SecurityUtils.hasCurrentUserThisAuthority(AuthoritiesConstants.CONTRIBUTEUR))
        	return ticketRepository.findAllByContributor(pageable, SecurityUtils.getCurrentUserLogin()).map(ticketMapper::toDto);
       
       //if(SecurityUtils.hasCurrentUserThisAuthority(AuthoritiesConstants.RELAIS_CENTRAL))
        	//return ticketRepository.findAllByEntityID(pageable, directionRegionaleService.getOneByUser(SecurityUtils.getCurrentUserLogin()).map(ticketMapper::toDto)).map(ticketMapper::toDto);
        
      //  if(SecurityUtils.hasCurrentUserThisAuthority(AuthoritiesConstants.RELAIS_REGIONAL))
      //  	return ticketRepository.findAllByDirectionRegionaleID(pageable, SecurityUtils.getCurrentUserLogin()).map(ticketMapper::toDto);
        	
        return null;
    }

    public Page<TicketDTO> findAllWithEagerRelationships(Pageable pageable) {
        return ticketRepository.findAllWithEagerRelationships(pageable).map(ticketMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TicketDTO> findOne(Long id) {
        log.debug("Request to get Ticket : {}", id);
        return ticketRepository.findOneWithEagerRelationships(id).map(ticketMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Ticket : {}", id);
        ticketRepository.deleteById(id);
    }
}
