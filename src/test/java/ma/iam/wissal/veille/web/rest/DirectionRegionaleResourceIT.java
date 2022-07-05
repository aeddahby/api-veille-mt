package ma.iam.wissal.veille.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import ma.iam.wissal.veille.IntegrationTest;
import ma.iam.wissal.veille.domain.DirectionRegionale;
import ma.iam.wissal.veille.repository.DirectionRegionaleRepository;
import ma.iam.wissal.veille.service.dto.DirectionRegionaleDTO;
import ma.iam.wissal.veille.service.mapper.DirectionRegionaleMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link DirectionRegionaleResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DirectionRegionaleResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/direction-regionales";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private DirectionRegionaleRepository directionRegionaleRepository;

    @Autowired
    private DirectionRegionaleMapper directionRegionaleMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDirectionRegionaleMockMvc;

    private DirectionRegionale directionRegionale;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DirectionRegionale createEntity(EntityManager em) {
        DirectionRegionale directionRegionale = new DirectionRegionale().name(DEFAULT_NAME);
        return directionRegionale;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DirectionRegionale createUpdatedEntity(EntityManager em) {
        DirectionRegionale directionRegionale = new DirectionRegionale().name(UPDATED_NAME);
        return directionRegionale;
    }

    @BeforeEach
    public void initTest() {
        directionRegionale = createEntity(em);
    }

    @Test
    @Transactional
    void createDirectionRegionale() throws Exception {
        int databaseSizeBeforeCreate = directionRegionaleRepository.findAll().size();
        // Create the DirectionRegionale
        DirectionRegionaleDTO directionRegionaleDTO = directionRegionaleMapper.toDto(directionRegionale);
        restDirectionRegionaleMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(directionRegionaleDTO))
            )
            .andExpect(status().isCreated());

        // Validate the DirectionRegionale in the database
        List<DirectionRegionale> directionRegionaleList = directionRegionaleRepository.findAll();
        assertThat(directionRegionaleList).hasSize(databaseSizeBeforeCreate + 1);
        DirectionRegionale testDirectionRegionale = directionRegionaleList.get(directionRegionaleList.size() - 1);
        assertThat(testDirectionRegionale.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    void createDirectionRegionaleWithExistingId() throws Exception {
        // Create the DirectionRegionale with an existing ID
        directionRegionale.setId(1L);
        DirectionRegionaleDTO directionRegionaleDTO = directionRegionaleMapper.toDto(directionRegionale);

        int databaseSizeBeforeCreate = directionRegionaleRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDirectionRegionaleMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(directionRegionaleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DirectionRegionale in the database
        List<DirectionRegionale> directionRegionaleList = directionRegionaleRepository.findAll();
        assertThat(directionRegionaleList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDirectionRegionales() throws Exception {
        // Initialize the database
        directionRegionaleRepository.saveAndFlush(directionRegionale);

        // Get all the directionRegionaleList
        restDirectionRegionaleMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(directionRegionale.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }

    @Test
    @Transactional
    void getDirectionRegionale() throws Exception {
        // Initialize the database
        directionRegionaleRepository.saveAndFlush(directionRegionale);

        // Get the directionRegionale
        restDirectionRegionaleMockMvc
            .perform(get(ENTITY_API_URL_ID, directionRegionale.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(directionRegionale.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    void getNonExistingDirectionRegionale() throws Exception {
        // Get the directionRegionale
        restDirectionRegionaleMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewDirectionRegionale() throws Exception {
        // Initialize the database
        directionRegionaleRepository.saveAndFlush(directionRegionale);

        int databaseSizeBeforeUpdate = directionRegionaleRepository.findAll().size();

        // Update the directionRegionale
        DirectionRegionale updatedDirectionRegionale = directionRegionaleRepository.findById(directionRegionale.getId()).get();
        // Disconnect from session so that the updates on updatedDirectionRegionale are not directly saved in db
        em.detach(updatedDirectionRegionale);
        updatedDirectionRegionale.name(UPDATED_NAME);
        DirectionRegionaleDTO directionRegionaleDTO = directionRegionaleMapper.toDto(updatedDirectionRegionale);

        restDirectionRegionaleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, directionRegionaleDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(directionRegionaleDTO))
            )
            .andExpect(status().isOk());

        // Validate the DirectionRegionale in the database
        List<DirectionRegionale> directionRegionaleList = directionRegionaleRepository.findAll();
        assertThat(directionRegionaleList).hasSize(databaseSizeBeforeUpdate);
        DirectionRegionale testDirectionRegionale = directionRegionaleList.get(directionRegionaleList.size() - 1);
        assertThat(testDirectionRegionale.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    void putNonExistingDirectionRegionale() throws Exception {
        int databaseSizeBeforeUpdate = directionRegionaleRepository.findAll().size();
        directionRegionale.setId(count.incrementAndGet());

        // Create the DirectionRegionale
        DirectionRegionaleDTO directionRegionaleDTO = directionRegionaleMapper.toDto(directionRegionale);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDirectionRegionaleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, directionRegionaleDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(directionRegionaleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DirectionRegionale in the database
        List<DirectionRegionale> directionRegionaleList = directionRegionaleRepository.findAll();
        assertThat(directionRegionaleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDirectionRegionale() throws Exception {
        int databaseSizeBeforeUpdate = directionRegionaleRepository.findAll().size();
        directionRegionale.setId(count.incrementAndGet());

        // Create the DirectionRegionale
        DirectionRegionaleDTO directionRegionaleDTO = directionRegionaleMapper.toDto(directionRegionale);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDirectionRegionaleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(directionRegionaleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DirectionRegionale in the database
        List<DirectionRegionale> directionRegionaleList = directionRegionaleRepository.findAll();
        assertThat(directionRegionaleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDirectionRegionale() throws Exception {
        int databaseSizeBeforeUpdate = directionRegionaleRepository.findAll().size();
        directionRegionale.setId(count.incrementAndGet());

        // Create the DirectionRegionale
        DirectionRegionaleDTO directionRegionaleDTO = directionRegionaleMapper.toDto(directionRegionale);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDirectionRegionaleMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(directionRegionaleDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DirectionRegionale in the database
        List<DirectionRegionale> directionRegionaleList = directionRegionaleRepository.findAll();
        assertThat(directionRegionaleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDirectionRegionaleWithPatch() throws Exception {
        // Initialize the database
        directionRegionaleRepository.saveAndFlush(directionRegionale);

        int databaseSizeBeforeUpdate = directionRegionaleRepository.findAll().size();

        // Update the directionRegionale using partial update
        DirectionRegionale partialUpdatedDirectionRegionale = new DirectionRegionale();
        partialUpdatedDirectionRegionale.setId(directionRegionale.getId());

        restDirectionRegionaleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDirectionRegionale.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDirectionRegionale))
            )
            .andExpect(status().isOk());

        // Validate the DirectionRegionale in the database
        List<DirectionRegionale> directionRegionaleList = directionRegionaleRepository.findAll();
        assertThat(directionRegionaleList).hasSize(databaseSizeBeforeUpdate);
        DirectionRegionale testDirectionRegionale = directionRegionaleList.get(directionRegionaleList.size() - 1);
        assertThat(testDirectionRegionale.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    void fullUpdateDirectionRegionaleWithPatch() throws Exception {
        // Initialize the database
        directionRegionaleRepository.saveAndFlush(directionRegionale);

        int databaseSizeBeforeUpdate = directionRegionaleRepository.findAll().size();

        // Update the directionRegionale using partial update
        DirectionRegionale partialUpdatedDirectionRegionale = new DirectionRegionale();
        partialUpdatedDirectionRegionale.setId(directionRegionale.getId());

        partialUpdatedDirectionRegionale.name(UPDATED_NAME);

        restDirectionRegionaleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDirectionRegionale.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDirectionRegionale))
            )
            .andExpect(status().isOk());

        // Validate the DirectionRegionale in the database
        List<DirectionRegionale> directionRegionaleList = directionRegionaleRepository.findAll();
        assertThat(directionRegionaleList).hasSize(databaseSizeBeforeUpdate);
        DirectionRegionale testDirectionRegionale = directionRegionaleList.get(directionRegionaleList.size() - 1);
        assertThat(testDirectionRegionale.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    void patchNonExistingDirectionRegionale() throws Exception {
        int databaseSizeBeforeUpdate = directionRegionaleRepository.findAll().size();
        directionRegionale.setId(count.incrementAndGet());

        // Create the DirectionRegionale
        DirectionRegionaleDTO directionRegionaleDTO = directionRegionaleMapper.toDto(directionRegionale);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDirectionRegionaleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, directionRegionaleDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(directionRegionaleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DirectionRegionale in the database
        List<DirectionRegionale> directionRegionaleList = directionRegionaleRepository.findAll();
        assertThat(directionRegionaleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDirectionRegionale() throws Exception {
        int databaseSizeBeforeUpdate = directionRegionaleRepository.findAll().size();
        directionRegionale.setId(count.incrementAndGet());

        // Create the DirectionRegionale
        DirectionRegionaleDTO directionRegionaleDTO = directionRegionaleMapper.toDto(directionRegionale);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDirectionRegionaleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(directionRegionaleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DirectionRegionale in the database
        List<DirectionRegionale> directionRegionaleList = directionRegionaleRepository.findAll();
        assertThat(directionRegionaleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDirectionRegionale() throws Exception {
        int databaseSizeBeforeUpdate = directionRegionaleRepository.findAll().size();
        directionRegionale.setId(count.incrementAndGet());

        // Create the DirectionRegionale
        DirectionRegionaleDTO directionRegionaleDTO = directionRegionaleMapper.toDto(directionRegionale);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDirectionRegionaleMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(directionRegionaleDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DirectionRegionale in the database
        List<DirectionRegionale> directionRegionaleList = directionRegionaleRepository.findAll();
        assertThat(directionRegionaleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDirectionRegionale() throws Exception {
        // Initialize the database
        directionRegionaleRepository.saveAndFlush(directionRegionale);

        int databaseSizeBeforeDelete = directionRegionaleRepository.findAll().size();

        // Delete the directionRegionale
        restDirectionRegionaleMockMvc
            .perform(delete(ENTITY_API_URL_ID, directionRegionale.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DirectionRegionale> directionRegionaleList = directionRegionaleRepository.findAll();
        assertThat(directionRegionaleList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
