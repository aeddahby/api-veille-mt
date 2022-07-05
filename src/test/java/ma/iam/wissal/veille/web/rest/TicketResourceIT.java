package ma.iam.wissal.veille.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import ma.iam.wissal.veille.IntegrationTest;
import ma.iam.wissal.veille.domain.Ticket;
import ma.iam.wissal.veille.domain.enumeration.StateTicket;
import ma.iam.wissal.veille.domain.enumeration.Status;
import ma.iam.wissal.veille.repository.TicketRepository;
import ma.iam.wissal.veille.service.TicketService;
import ma.iam.wissal.veille.service.dto.TicketDTO;
import ma.iam.wissal.veille.service.mapper.TicketMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link TicketResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class TicketResourceIT {

    private static final String DEFAULT_OBJECT = "AAAAAAAAAA";
    private static final String UPDATED_OBJECT = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATION_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATION_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_MODIFICATION_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_MODIFICATION_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_CLOSED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CLOSED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CONTRIBUTOR = "AAAAAAAAAA";
    private static final String UPDATED_CONTRIBUTOR = "BBBBBBBBBB";

    private static final Boolean DEFAULT_CONTRIBUTOR_VISIBILITY = false;
    private static final Boolean UPDATED_CONTRIBUTOR_VISIBILITY = true;

    private static final Boolean DEFAULT_ENTITY_VISIBILITY = false;
    private static final Boolean UPDATED_ENTITY_VISIBILITY = true;

    private static final Boolean DEFAULT_DIRECTION_VISIBILITY = false;
    private static final Boolean UPDATED_DIRECTION_VISIBILITY = true;

    private static final String DEFAULT_CENTRAL_ANIMATOR = "AAAAAAAAAA";
    private static final String UPDATED_CENTRAL_ANIMATOR = "BBBBBBBBBB";

    private static final String DEFAULT_CENTRAL_RELAY = "AAAAAAAAAA";
    private static final String UPDATED_CENTRAL_RELAY = "BBBBBBBBBB";

    private static final String DEFAULT_REGIONAL_RELAY = "AAAAAAAAAA";
    private static final String UPDATED_REGIONAL_RELAY = "BBBBBBBBBB";

    private static final StateTicket DEFAULT_STATE_TICKET = StateTicket.OPENED;
    private static final StateTicket UPDATED_STATE_TICKET = StateTicket.CLOSED;

    private static final Status DEFAULT_STATUS_TICKET = Status.CREATED;
    private static final Status UPDATED_STATUS_TICKET = Status.IN_PROGRESS;

    private static final Boolean DEFAULT_PERTINENCE = false;
    private static final Boolean UPDATED_PERTINENCE = true;

    private static final String ENTITY_API_URL = "/api/tickets";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TicketRepository ticketRepository;

    @Mock
    private TicketRepository ticketRepositoryMock;

    @Autowired
    private TicketMapper ticketMapper;

    @Mock
    private TicketService ticketServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTicketMockMvc;

    private Ticket ticket;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Ticket createEntity(EntityManager em) {
        Ticket ticket = new Ticket()
            .object(DEFAULT_OBJECT)
            .description(DEFAULT_DESCRIPTION)
            .creationDate(DEFAULT_CREATION_DATE)
            .modificationDate(DEFAULT_MODIFICATION_DATE)
            .closedDate(DEFAULT_CLOSED_DATE)
            .contributor(DEFAULT_CONTRIBUTOR)
            .contributorVisibility(DEFAULT_CONTRIBUTOR_VISIBILITY)
            .entityVisibility(DEFAULT_ENTITY_VISIBILITY)
            .directionVisibility(DEFAULT_DIRECTION_VISIBILITY)
            .centralAnimator(DEFAULT_CENTRAL_ANIMATOR)
            .centralRelay(DEFAULT_CENTRAL_RELAY)
            .regionalRelay(DEFAULT_REGIONAL_RELAY)
            .stateTicket(DEFAULT_STATE_TICKET)
            .statusTicket(DEFAULT_STATUS_TICKET)
            .pertinence(DEFAULT_PERTINENCE);
        return ticket;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Ticket createUpdatedEntity(EntityManager em) {
        Ticket ticket = new Ticket()
            .object(UPDATED_OBJECT)
            .description(UPDATED_DESCRIPTION)
            .creationDate(UPDATED_CREATION_DATE)
            .modificationDate(UPDATED_MODIFICATION_DATE)
            .closedDate(UPDATED_CLOSED_DATE)
            .contributor(UPDATED_CONTRIBUTOR)
            .contributorVisibility(UPDATED_CONTRIBUTOR_VISIBILITY)
            .entityVisibility(UPDATED_ENTITY_VISIBILITY)
            .directionVisibility(UPDATED_DIRECTION_VISIBILITY)
            .centralAnimator(UPDATED_CENTRAL_ANIMATOR)
            .centralRelay(UPDATED_CENTRAL_RELAY)
            .regionalRelay(UPDATED_REGIONAL_RELAY)
            .stateTicket(UPDATED_STATE_TICKET)
            .statusTicket(UPDATED_STATUS_TICKET)
            .pertinence(UPDATED_PERTINENCE);
        return ticket;
    }

    @BeforeEach
    public void initTest() {
        ticket = createEntity(em);
    }

    @Test
    @Transactional
    void createTicket() throws Exception {
        int databaseSizeBeforeCreate = ticketRepository.findAll().size();
        // Create the Ticket
        TicketDTO ticketDTO = ticketMapper.toDto(ticket);
        restTicketMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ticketDTO)))
            .andExpect(status().isCreated());

        // Validate the Ticket in the database
        List<Ticket> ticketList = ticketRepository.findAll();
        assertThat(ticketList).hasSize(databaseSizeBeforeCreate + 1);
        Ticket testTicket = ticketList.get(ticketList.size() - 1);
        assertThat(testTicket.getObject()).isEqualTo(DEFAULT_OBJECT);
        assertThat(testTicket.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testTicket.getCreationDate()).isEqualTo(DEFAULT_CREATION_DATE);
        assertThat(testTicket.getModificationDate()).isEqualTo(DEFAULT_MODIFICATION_DATE);
        assertThat(testTicket.getClosedDate()).isEqualTo(DEFAULT_CLOSED_DATE);
        assertThat(testTicket.getContributor()).isEqualTo(DEFAULT_CONTRIBUTOR);
        assertThat(testTicket.getContributorVisibility()).isEqualTo(DEFAULT_CONTRIBUTOR_VISIBILITY);
        assertThat(testTicket.getEntityVisibility()).isEqualTo(DEFAULT_ENTITY_VISIBILITY);
        assertThat(testTicket.getDirectionVisibility()).isEqualTo(DEFAULT_DIRECTION_VISIBILITY);
        assertThat(testTicket.getCentralAnimator()).isEqualTo(DEFAULT_CENTRAL_ANIMATOR);
        assertThat(testTicket.getCentralRelay()).isEqualTo(DEFAULT_CENTRAL_RELAY);
        assertThat(testTicket.getRegionalRelay()).isEqualTo(DEFAULT_REGIONAL_RELAY);
        assertThat(testTicket.getStateTicket()).isEqualTo(DEFAULT_STATE_TICKET);
        assertThat(testTicket.getStatusTicket()).isEqualTo(DEFAULT_STATUS_TICKET);
        assertThat(testTicket.getPertinence()).isEqualTo(DEFAULT_PERTINENCE);
    }

    @Test
    @Transactional
    void createTicketWithExistingId() throws Exception {
        // Create the Ticket with an existing ID
        ticket.setId(1L);
        TicketDTO ticketDTO = ticketMapper.toDto(ticket);

        int databaseSizeBeforeCreate = ticketRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTicketMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ticketDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Ticket in the database
        List<Ticket> ticketList = ticketRepository.findAll();
        assertThat(ticketList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTickets() throws Exception {
        // Initialize the database
        ticketRepository.saveAndFlush(ticket);

        // Get all the ticketList
        restTicketMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ticket.getId().intValue())))
            .andExpect(jsonPath("$.[*].object").value(hasItem(DEFAULT_OBJECT)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].modificationDate").value(hasItem(DEFAULT_MODIFICATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].closedDate").value(hasItem(DEFAULT_CLOSED_DATE.toString())))
            .andExpect(jsonPath("$.[*].contributor").value(hasItem(DEFAULT_CONTRIBUTOR)))
            .andExpect(jsonPath("$.[*].contributorVisibility").value(hasItem(DEFAULT_CONTRIBUTOR_VISIBILITY.booleanValue())))
            .andExpect(jsonPath("$.[*].entityVisibility").value(hasItem(DEFAULT_ENTITY_VISIBILITY.booleanValue())))
            .andExpect(jsonPath("$.[*].directionVisibility").value(hasItem(DEFAULT_DIRECTION_VISIBILITY.booleanValue())))
            .andExpect(jsonPath("$.[*].centralAnimator").value(hasItem(DEFAULT_CENTRAL_ANIMATOR)))
            .andExpect(jsonPath("$.[*].centralRelay").value(hasItem(DEFAULT_CENTRAL_RELAY)))
            .andExpect(jsonPath("$.[*].regionalRelay").value(hasItem(DEFAULT_REGIONAL_RELAY)))
            .andExpect(jsonPath("$.[*].stateTicket").value(hasItem(DEFAULT_STATE_TICKET.toString())))
            .andExpect(jsonPath("$.[*].statusTicket").value(hasItem(DEFAULT_STATUS_TICKET.toString())))
            .andExpect(jsonPath("$.[*].pertinence").value(hasItem(DEFAULT_PERTINENCE.booleanValue())));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllTicketsWithEagerRelationshipsIsEnabled() throws Exception {
        when(ticketServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restTicketMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(ticketServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllTicketsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(ticketServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restTicketMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(ticketServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    void getTicket() throws Exception {
        // Initialize the database
        ticketRepository.saveAndFlush(ticket);

        // Get the ticket
        restTicketMockMvc
            .perform(get(ENTITY_API_URL_ID, ticket.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ticket.getId().intValue()))
            .andExpect(jsonPath("$.object").value(DEFAULT_OBJECT))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.creationDate").value(DEFAULT_CREATION_DATE.toString()))
            .andExpect(jsonPath("$.modificationDate").value(DEFAULT_MODIFICATION_DATE.toString()))
            .andExpect(jsonPath("$.closedDate").value(DEFAULT_CLOSED_DATE.toString()))
            .andExpect(jsonPath("$.contributor").value(DEFAULT_CONTRIBUTOR))
            .andExpect(jsonPath("$.contributorVisibility").value(DEFAULT_CONTRIBUTOR_VISIBILITY.booleanValue()))
            .andExpect(jsonPath("$.entityVisibility").value(DEFAULT_ENTITY_VISIBILITY.booleanValue()))
            .andExpect(jsonPath("$.directionVisibility").value(DEFAULT_DIRECTION_VISIBILITY.booleanValue()))
            .andExpect(jsonPath("$.centralAnimator").value(DEFAULT_CENTRAL_ANIMATOR))
            .andExpect(jsonPath("$.centralRelay").value(DEFAULT_CENTRAL_RELAY))
            .andExpect(jsonPath("$.regionalRelay").value(DEFAULT_REGIONAL_RELAY))
            .andExpect(jsonPath("$.stateTicket").value(DEFAULT_STATE_TICKET.toString()))
            .andExpect(jsonPath("$.statusTicket").value(DEFAULT_STATUS_TICKET.toString()))
            .andExpect(jsonPath("$.pertinence").value(DEFAULT_PERTINENCE.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingTicket() throws Exception {
        // Get the ticket
        restTicketMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTicket() throws Exception {
        // Initialize the database
        ticketRepository.saveAndFlush(ticket);

        int databaseSizeBeforeUpdate = ticketRepository.findAll().size();

        // Update the ticket
        Ticket updatedTicket = ticketRepository.findById(ticket.getId()).get();
        // Disconnect from session so that the updates on updatedTicket are not directly saved in db
        em.detach(updatedTicket);
        updatedTicket
            .object(UPDATED_OBJECT)
            .description(UPDATED_DESCRIPTION)
            .creationDate(UPDATED_CREATION_DATE)
            .modificationDate(UPDATED_MODIFICATION_DATE)
            .closedDate(UPDATED_CLOSED_DATE)
            .contributor(UPDATED_CONTRIBUTOR)
            .contributorVisibility(UPDATED_CONTRIBUTOR_VISIBILITY)
            .entityVisibility(UPDATED_ENTITY_VISIBILITY)
            .directionVisibility(UPDATED_DIRECTION_VISIBILITY)
            .centralAnimator(UPDATED_CENTRAL_ANIMATOR)
            .centralRelay(UPDATED_CENTRAL_RELAY)
            .regionalRelay(UPDATED_REGIONAL_RELAY)
            .stateTicket(UPDATED_STATE_TICKET)
            .statusTicket(UPDATED_STATUS_TICKET)
            .pertinence(UPDATED_PERTINENCE);
        TicketDTO ticketDTO = ticketMapper.toDto(updatedTicket);

        restTicketMockMvc
            .perform(
                put(ENTITY_API_URL_ID, ticketDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(ticketDTO))
            )
            .andExpect(status().isOk());

        // Validate the Ticket in the database
        List<Ticket> ticketList = ticketRepository.findAll();
        assertThat(ticketList).hasSize(databaseSizeBeforeUpdate);
        Ticket testTicket = ticketList.get(ticketList.size() - 1);
        assertThat(testTicket.getObject()).isEqualTo(UPDATED_OBJECT);
        assertThat(testTicket.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testTicket.getCreationDate()).isEqualTo(UPDATED_CREATION_DATE);
        assertThat(testTicket.getModificationDate()).isEqualTo(UPDATED_MODIFICATION_DATE);
        assertThat(testTicket.getClosedDate()).isEqualTo(UPDATED_CLOSED_DATE);
        assertThat(testTicket.getContributor()).isEqualTo(UPDATED_CONTRIBUTOR);
        assertThat(testTicket.getContributorVisibility()).isEqualTo(UPDATED_CONTRIBUTOR_VISIBILITY);
        assertThat(testTicket.getEntityVisibility()).isEqualTo(UPDATED_ENTITY_VISIBILITY);
        assertThat(testTicket.getDirectionVisibility()).isEqualTo(UPDATED_DIRECTION_VISIBILITY);
        assertThat(testTicket.getCentralAnimator()).isEqualTo(UPDATED_CENTRAL_ANIMATOR);
        assertThat(testTicket.getCentralRelay()).isEqualTo(UPDATED_CENTRAL_RELAY);
        assertThat(testTicket.getRegionalRelay()).isEqualTo(UPDATED_REGIONAL_RELAY);
        assertThat(testTicket.getStateTicket()).isEqualTo(UPDATED_STATE_TICKET);
        assertThat(testTicket.getStatusTicket()).isEqualTo(UPDATED_STATUS_TICKET);
        assertThat(testTicket.getPertinence()).isEqualTo(UPDATED_PERTINENCE);
    }

    @Test
    @Transactional
    void putNonExistingTicket() throws Exception {
        int databaseSizeBeforeUpdate = ticketRepository.findAll().size();
        ticket.setId(count.incrementAndGet());

        // Create the Ticket
        TicketDTO ticketDTO = ticketMapper.toDto(ticket);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTicketMockMvc
            .perform(
                put(ENTITY_API_URL_ID, ticketDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(ticketDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Ticket in the database
        List<Ticket> ticketList = ticketRepository.findAll();
        assertThat(ticketList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTicket() throws Exception {
        int databaseSizeBeforeUpdate = ticketRepository.findAll().size();
        ticket.setId(count.incrementAndGet());

        // Create the Ticket
        TicketDTO ticketDTO = ticketMapper.toDto(ticket);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTicketMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(ticketDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Ticket in the database
        List<Ticket> ticketList = ticketRepository.findAll();
        assertThat(ticketList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTicket() throws Exception {
        int databaseSizeBeforeUpdate = ticketRepository.findAll().size();
        ticket.setId(count.incrementAndGet());

        // Create the Ticket
        TicketDTO ticketDTO = ticketMapper.toDto(ticket);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTicketMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ticketDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Ticket in the database
        List<Ticket> ticketList = ticketRepository.findAll();
        assertThat(ticketList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTicketWithPatch() throws Exception {
        // Initialize the database
        ticketRepository.saveAndFlush(ticket);

        int databaseSizeBeforeUpdate = ticketRepository.findAll().size();

        // Update the ticket using partial update
        Ticket partialUpdatedTicket = new Ticket();
        partialUpdatedTicket.setId(ticket.getId());

        partialUpdatedTicket
            .creationDate(UPDATED_CREATION_DATE)
            .entityVisibility(UPDATED_ENTITY_VISIBILITY)
            .directionVisibility(UPDATED_DIRECTION_VISIBILITY)
            .centralAnimator(UPDATED_CENTRAL_ANIMATOR)
            .centralRelay(UPDATED_CENTRAL_RELAY)
            .stateTicket(UPDATED_STATE_TICKET)
            .statusTicket(UPDATED_STATUS_TICKET);

        restTicketMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTicket.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTicket))
            )
            .andExpect(status().isOk());

        // Validate the Ticket in the database
        List<Ticket> ticketList = ticketRepository.findAll();
        assertThat(ticketList).hasSize(databaseSizeBeforeUpdate);
        Ticket testTicket = ticketList.get(ticketList.size() - 1);
        assertThat(testTicket.getObject()).isEqualTo(DEFAULT_OBJECT);
        assertThat(testTicket.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testTicket.getCreationDate()).isEqualTo(UPDATED_CREATION_DATE);
        assertThat(testTicket.getModificationDate()).isEqualTo(DEFAULT_MODIFICATION_DATE);
        assertThat(testTicket.getClosedDate()).isEqualTo(DEFAULT_CLOSED_DATE);
        assertThat(testTicket.getContributor()).isEqualTo(DEFAULT_CONTRIBUTOR);
        assertThat(testTicket.getContributorVisibility()).isEqualTo(DEFAULT_CONTRIBUTOR_VISIBILITY);
        assertThat(testTicket.getEntityVisibility()).isEqualTo(UPDATED_ENTITY_VISIBILITY);
        assertThat(testTicket.getDirectionVisibility()).isEqualTo(UPDATED_DIRECTION_VISIBILITY);
        assertThat(testTicket.getCentralAnimator()).isEqualTo(UPDATED_CENTRAL_ANIMATOR);
        assertThat(testTicket.getCentralRelay()).isEqualTo(UPDATED_CENTRAL_RELAY);
        assertThat(testTicket.getRegionalRelay()).isEqualTo(DEFAULT_REGIONAL_RELAY);
        assertThat(testTicket.getStateTicket()).isEqualTo(UPDATED_STATE_TICKET);
        assertThat(testTicket.getStatusTicket()).isEqualTo(UPDATED_STATUS_TICKET);
        assertThat(testTicket.getPertinence()).isEqualTo(DEFAULT_PERTINENCE);
    }

    @Test
    @Transactional
    void fullUpdateTicketWithPatch() throws Exception {
        // Initialize the database
        ticketRepository.saveAndFlush(ticket);

        int databaseSizeBeforeUpdate = ticketRepository.findAll().size();

        // Update the ticket using partial update
        Ticket partialUpdatedTicket = new Ticket();
        partialUpdatedTicket.setId(ticket.getId());

        partialUpdatedTicket
            .object(UPDATED_OBJECT)
            .description(UPDATED_DESCRIPTION)
            .creationDate(UPDATED_CREATION_DATE)
            .modificationDate(UPDATED_MODIFICATION_DATE)
            .closedDate(UPDATED_CLOSED_DATE)
            .contributor(UPDATED_CONTRIBUTOR)
            .contributorVisibility(UPDATED_CONTRIBUTOR_VISIBILITY)
            .entityVisibility(UPDATED_ENTITY_VISIBILITY)
            .directionVisibility(UPDATED_DIRECTION_VISIBILITY)
            .centralAnimator(UPDATED_CENTRAL_ANIMATOR)
            .centralRelay(UPDATED_CENTRAL_RELAY)
            .regionalRelay(UPDATED_REGIONAL_RELAY)
            .stateTicket(UPDATED_STATE_TICKET)
            .statusTicket(UPDATED_STATUS_TICKET)
            .pertinence(UPDATED_PERTINENCE);

        restTicketMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTicket.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTicket))
            )
            .andExpect(status().isOk());

        // Validate the Ticket in the database
        List<Ticket> ticketList = ticketRepository.findAll();
        assertThat(ticketList).hasSize(databaseSizeBeforeUpdate);
        Ticket testTicket = ticketList.get(ticketList.size() - 1);
        assertThat(testTicket.getObject()).isEqualTo(UPDATED_OBJECT);
        assertThat(testTicket.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testTicket.getCreationDate()).isEqualTo(UPDATED_CREATION_DATE);
        assertThat(testTicket.getModificationDate()).isEqualTo(UPDATED_MODIFICATION_DATE);
        assertThat(testTicket.getClosedDate()).isEqualTo(UPDATED_CLOSED_DATE);
        assertThat(testTicket.getContributor()).isEqualTo(UPDATED_CONTRIBUTOR);
        assertThat(testTicket.getContributorVisibility()).isEqualTo(UPDATED_CONTRIBUTOR_VISIBILITY);
        assertThat(testTicket.getEntityVisibility()).isEqualTo(UPDATED_ENTITY_VISIBILITY);
        assertThat(testTicket.getDirectionVisibility()).isEqualTo(UPDATED_DIRECTION_VISIBILITY);
        assertThat(testTicket.getCentralAnimator()).isEqualTo(UPDATED_CENTRAL_ANIMATOR);
        assertThat(testTicket.getCentralRelay()).isEqualTo(UPDATED_CENTRAL_RELAY);
        assertThat(testTicket.getRegionalRelay()).isEqualTo(UPDATED_REGIONAL_RELAY);
        assertThat(testTicket.getStateTicket()).isEqualTo(UPDATED_STATE_TICKET);
        assertThat(testTicket.getStatusTicket()).isEqualTo(UPDATED_STATUS_TICKET);
        assertThat(testTicket.getPertinence()).isEqualTo(UPDATED_PERTINENCE);
    }

    @Test
    @Transactional
    void patchNonExistingTicket() throws Exception {
        int databaseSizeBeforeUpdate = ticketRepository.findAll().size();
        ticket.setId(count.incrementAndGet());

        // Create the Ticket
        TicketDTO ticketDTO = ticketMapper.toDto(ticket);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTicketMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, ticketDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(ticketDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Ticket in the database
        List<Ticket> ticketList = ticketRepository.findAll();
        assertThat(ticketList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTicket() throws Exception {
        int databaseSizeBeforeUpdate = ticketRepository.findAll().size();
        ticket.setId(count.incrementAndGet());

        // Create the Ticket
        TicketDTO ticketDTO = ticketMapper.toDto(ticket);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTicketMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(ticketDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Ticket in the database
        List<Ticket> ticketList = ticketRepository.findAll();
        assertThat(ticketList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTicket() throws Exception {
        int databaseSizeBeforeUpdate = ticketRepository.findAll().size();
        ticket.setId(count.incrementAndGet());

        // Create the Ticket
        TicketDTO ticketDTO = ticketMapper.toDto(ticket);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTicketMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(ticketDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Ticket in the database
        List<Ticket> ticketList = ticketRepository.findAll();
        assertThat(ticketList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTicket() throws Exception {
        // Initialize the database
        ticketRepository.saveAndFlush(ticket);

        int databaseSizeBeforeDelete = ticketRepository.findAll().size();

        // Delete the ticket
        restTicketMockMvc
            .perform(delete(ENTITY_API_URL_ID, ticket.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Ticket> ticketList = ticketRepository.findAll();
        assertThat(ticketList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
