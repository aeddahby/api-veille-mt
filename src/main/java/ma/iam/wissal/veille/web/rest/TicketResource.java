package ma.iam.wissal.veille.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import ma.iam.wissal.veille.domain.enumeration.StateTicket;
import ma.iam.wissal.veille.domain.enumeration.Status;
import ma.iam.wissal.veille.repository.TicketRepository;
import ma.iam.wissal.veille.security.AuthoritiesConstants;
import ma.iam.wissal.veille.security.SecurityUtils;
import ma.iam.wissal.veille.service.AttachmentService;
import ma.iam.wissal.veille.service.DirectionRegionaleService;
import ma.iam.wissal.veille.service.HeaderUtil;
import ma.iam.wissal.veille.service.TicketService;
import ma.iam.wissal.veille.service.dto.AttachmentDTO;
import ma.iam.wissal.veille.service.dto.TicketDTO;
import ma.iam.wissal.veille.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link ma.iam.wissal.veille.domain.Ticket}.
 */
@RestController
@RequestMapping("/api")
public class TicketResource {

    private final Logger log = LoggerFactory.getLogger(TicketResource.class);

    private static final String ENTITY_NAME = "Remontée";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TicketService ticketService;

    private final TicketRepository ticketRepository;

    private final DirectionRegionaleService directionRegionaleService;

    private final AttachmentService attachmentService;

    public TicketResource(
        TicketService ticketService,
        TicketRepository ticketRepository,
        DirectionRegionaleService directionRegionaleService,
        AttachmentService attachmentService
    ) {
        this.ticketService = ticketService;
        this.ticketRepository = ticketRepository;
        this.directionRegionaleService = directionRegionaleService;
        this.attachmentService = attachmentService;
    }

    /**
     * {@code POST  /tickets} : Create a new ticket.
     *
     * @param ticketDTO the ticketDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ticketDTO, or with status {@code 400 (Bad Request)} if the ticket has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tickets")
    public ResponseEntity<TicketDTO> createTicket(@RequestBody TicketDTO ticketDTO) throws URISyntaxException {
        log.debug("REST request to save Ticket : {}", ticketDTO);
        if (ticketDTO.getId() != null) {
            throw new BadRequestAlertException("A new ticket cannot already have an ID", ENTITY_NAME, "idexists");
        }
        if (ticketDTO.getContributor() == null || ticketDTO.getContributor().isEmpty()) ticketDTO.setContributor(
            SecurityUtils.getCurrentUserLogin().get()
        );
        ticketDTO.setDirectionRegionale(directionRegionaleService.getOneByUser(SecurityUtils.getCurrentUserLogin().get()).get());
        ticketDTO.setStateTicket(StateTicket.OPENED);
        ticketDTO.setStatusTicket(Status.CREATED);

        AttachmentDTO attachmentDTO = new AttachmentDTO();
        attachmentDTO.setAttach(ticketDTO.getAttach());
        attachmentDTO.setAttachContentType(ticketDTO.getAttachContentType());
        TicketDTO result = ticketService.save(ticketDTO);
        attachmentDTO.setTicket(result);
        attachmentService.save(attachmentDTO);

        return ResponseEntity
            .created(new URI("/api/tickets/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tickets/:id} : Updates an existing ticket.
     *
     * @param id the id of the ticketDTO to save.
     * @param ticketDTO the ticketDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ticketDTO,
     * or with status {@code 400 (Bad Request)} if the ticketDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ticketDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tickets/{id}")
    public ResponseEntity<TicketDTO> updateTicket(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TicketDTO ticketDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Ticket : {}, {}", id, ticketDTO);
        if (ticketDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, ticketDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!ticketRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        if (ticketDTO.getStatusTicket() != Status.CREATED && SecurityUtils.hasCurrentUserThisAuthority(AuthoritiesConstants.CONTRIBUTEUR)) {
            throw new BadRequestAlertException("Vous n'avez pas le droit de modification", ENTITY_NAME, "idnotfound");
        }
        TicketDTO result = ticketService.update(ticketDTO);
        Optional<AttachmentDTO> attachmentDTO = attachmentService.findOneByTicket(ticketDTO);
        if (!attachmentDTO.isEmpty()) {
            attachmentDTO.get().setAttach(ticketDTO.getAttach());
            attachmentDTO.get().setAttachContentType(ticketDTO.getAttachContentType());
            attachmentService.update(attachmentDTO.get());
        } else {
            AttachmentDTO att01 = new AttachmentDTO();
            att01.setAttach(ticketDTO.getAttach());
            att01.setAttachContentType(ticketDTO.getAttachContentType());
            att01.setTicket(result);
            attachmentService.save(att01);
        }

        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, ticketDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tickets/:id} : Partial updates given fields of an existing ticket, field will ignore if it is null
     *
     * @param id the id of the ticketDTO to save.
     * @param ticketDTO the ticketDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ticketDTO,
     * or with status {@code 400 (Bad Request)} if the ticketDTO is not valid,
     * or with status {@code 404 (Not Found)} if the ticketDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the ticketDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tickets/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TicketDTO> partialUpdateTicket(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TicketDTO ticketDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Ticket partially : {}, {}", id, ticketDTO);
        if (ticketDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, ticketDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!ticketRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TicketDTO> result = ticketService.partialUpdate(ticketDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, ticketDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /tickets} : get all the tickets.
     *
     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tickets in body.
     */
    @GetMapping("/tickets")
    public ResponseEntity<List<TicketDTO>> getAllTickets(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        @RequestParam(required = false, defaultValue = "true") boolean eagerload
    ) {
        log.debug("REST request to get a page of Tickets");
        Page<TicketDTO> page;
        if (eagerload) {
            page = ticketService.findAll(pageable);
        } else {
            page = ticketService.findAll(pageable);
        }
        if (page == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /tickets/:id} : get the "id" ticket.
     *
     * @param id the id of the ticketDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ticketDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tickets/{id}")
    public ResponseEntity<TicketDTO> getTicket(@PathVariable Long id) {
        log.debug("REST request to get Ticket : {}", id);
        Optional<TicketDTO> ticketDTO = ticketService.findOne(id);
        Optional<AttachmentDTO> attachmentDTO = attachmentService.findOneByTicket(ticketDTO.get());
        if (!attachmentDTO.isEmpty()) {
            ticketDTO.get().setAttach(attachmentDTO.get().getAttach());
            ticketDTO.get().setAttachContentType(attachmentDTO.get().getAttachContentType());
        }
        return ResponseUtil.wrapOrNotFound(ticketDTO);
    }

    /**
     * {@code DELETE  /tickets/:id} : delete the "id" ticket.
     *
     * @param id the id of the ticketDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tickets/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        log.debug("REST request to delete Ticket : {}", id);
        ticketService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
