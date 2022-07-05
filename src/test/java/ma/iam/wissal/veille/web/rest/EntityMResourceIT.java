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
import ma.iam.wissal.veille.domain.EntityM;
import ma.iam.wissal.veille.repository.EntityMRepository;
import ma.iam.wissal.veille.service.dto.EntityMDTO;
import ma.iam.wissal.veille.service.mapper.EntityMMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link EntityMResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class EntityMResourceIT {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/entity-ms";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private EntityMRepository entityMRepository;

    @Autowired
    private EntityMMapper entityMMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEntityMMockMvc;

    private EntityM entityM;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntityM createEntity(EntityManager em) {
        EntityM entityM = new EntityM().title(DEFAULT_TITLE);
        return entityM;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntityM createUpdatedEntity(EntityManager em) {
        EntityM entityM = new EntityM().title(UPDATED_TITLE);
        return entityM;
    }

    @BeforeEach
    public void initTest() {
        entityM = createEntity(em);
    }

    @Test
    @Transactional
    void createEntityM() throws Exception {
        int databaseSizeBeforeCreate = entityMRepository.findAll().size();
        // Create the EntityM
        EntityMDTO entityMDTO = entityMMapper.toDto(entityM);
        restEntityMMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(entityMDTO)))
            .andExpect(status().isCreated());

        // Validate the EntityM in the database
        List<EntityM> entityMList = entityMRepository.findAll();
        assertThat(entityMList).hasSize(databaseSizeBeforeCreate + 1);
        EntityM testEntityM = entityMList.get(entityMList.size() - 1);
        assertThat(testEntityM.getTitle()).isEqualTo(DEFAULT_TITLE);
    }

    @Test
    @Transactional
    void createEntityMWithExistingId() throws Exception {
        // Create the EntityM with an existing ID
        entityM.setId(1L);
        EntityMDTO entityMDTO = entityMMapper.toDto(entityM);

        int databaseSizeBeforeCreate = entityMRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restEntityMMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(entityMDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EntityM in the database
        List<EntityM> entityMList = entityMRepository.findAll();
        assertThat(entityMList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllEntityMS() throws Exception {
        // Initialize the database
        entityMRepository.saveAndFlush(entityM);

        // Get all the entityMList
        restEntityMMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(entityM.getId().intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)));
    }

    @Test
    @Transactional
    void getEntityM() throws Exception {
        // Initialize the database
        entityMRepository.saveAndFlush(entityM);

        // Get the entityM
        restEntityMMockMvc
            .perform(get(ENTITY_API_URL_ID, entityM.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(entityM.getId().intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE));
    }

    @Test
    @Transactional
    void getNonExistingEntityM() throws Exception {
        // Get the entityM
        restEntityMMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewEntityM() throws Exception {
        // Initialize the database
        entityMRepository.saveAndFlush(entityM);

        int databaseSizeBeforeUpdate = entityMRepository.findAll().size();

        // Update the entityM
        EntityM updatedEntityM = entityMRepository.findById(entityM.getId()).get();
        // Disconnect from session so that the updates on updatedEntityM are not directly saved in db
        em.detach(updatedEntityM);
        updatedEntityM.title(UPDATED_TITLE);
        EntityMDTO entityMDTO = entityMMapper.toDto(updatedEntityM);

        restEntityMMockMvc
            .perform(
                put(ENTITY_API_URL_ID, entityMDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(entityMDTO))
            )
            .andExpect(status().isOk());

        // Validate the EntityM in the database
        List<EntityM> entityMList = entityMRepository.findAll();
        assertThat(entityMList).hasSize(databaseSizeBeforeUpdate);
        EntityM testEntityM = entityMList.get(entityMList.size() - 1);
        assertThat(testEntityM.getTitle()).isEqualTo(UPDATED_TITLE);
    }

    @Test
    @Transactional
    void putNonExistingEntityM() throws Exception {
        int databaseSizeBeforeUpdate = entityMRepository.findAll().size();
        entityM.setId(count.incrementAndGet());

        // Create the EntityM
        EntityMDTO entityMDTO = entityMMapper.toDto(entityM);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEntityMMockMvc
            .perform(
                put(ENTITY_API_URL_ID, entityMDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(entityMDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityM in the database
        List<EntityM> entityMList = entityMRepository.findAll();
        assertThat(entityMList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchEntityM() throws Exception {
        int databaseSizeBeforeUpdate = entityMRepository.findAll().size();
        entityM.setId(count.incrementAndGet());

        // Create the EntityM
        EntityMDTO entityMDTO = entityMMapper.toDto(entityM);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityMMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(entityMDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityM in the database
        List<EntityM> entityMList = entityMRepository.findAll();
        assertThat(entityMList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamEntityM() throws Exception {
        int databaseSizeBeforeUpdate = entityMRepository.findAll().size();
        entityM.setId(count.incrementAndGet());

        // Create the EntityM
        EntityMDTO entityMDTO = entityMMapper.toDto(entityM);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityMMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(entityMDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the EntityM in the database
        List<EntityM> entityMList = entityMRepository.findAll();
        assertThat(entityMList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateEntityMWithPatch() throws Exception {
        // Initialize the database
        entityMRepository.saveAndFlush(entityM);

        int databaseSizeBeforeUpdate = entityMRepository.findAll().size();

        // Update the entityM using partial update
        EntityM partialUpdatedEntityM = new EntityM();
        partialUpdatedEntityM.setId(entityM.getId());

        restEntityMMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEntityM.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEntityM))
            )
            .andExpect(status().isOk());

        // Validate the EntityM in the database
        List<EntityM> entityMList = entityMRepository.findAll();
        assertThat(entityMList).hasSize(databaseSizeBeforeUpdate);
        EntityM testEntityM = entityMList.get(entityMList.size() - 1);
        assertThat(testEntityM.getTitle()).isEqualTo(DEFAULT_TITLE);
    }

    @Test
    @Transactional
    void fullUpdateEntityMWithPatch() throws Exception {
        // Initialize the database
        entityMRepository.saveAndFlush(entityM);

        int databaseSizeBeforeUpdate = entityMRepository.findAll().size();

        // Update the entityM using partial update
        EntityM partialUpdatedEntityM = new EntityM();
        partialUpdatedEntityM.setId(entityM.getId());

        partialUpdatedEntityM.title(UPDATED_TITLE);

        restEntityMMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEntityM.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEntityM))
            )
            .andExpect(status().isOk());

        // Validate the EntityM in the database
        List<EntityM> entityMList = entityMRepository.findAll();
        assertThat(entityMList).hasSize(databaseSizeBeforeUpdate);
        EntityM testEntityM = entityMList.get(entityMList.size() - 1);
        assertThat(testEntityM.getTitle()).isEqualTo(UPDATED_TITLE);
    }

    @Test
    @Transactional
    void patchNonExistingEntityM() throws Exception {
        int databaseSizeBeforeUpdate = entityMRepository.findAll().size();
        entityM.setId(count.incrementAndGet());

        // Create the EntityM
        EntityMDTO entityMDTO = entityMMapper.toDto(entityM);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEntityMMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, entityMDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(entityMDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityM in the database
        List<EntityM> entityMList = entityMRepository.findAll();
        assertThat(entityMList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchEntityM() throws Exception {
        int databaseSizeBeforeUpdate = entityMRepository.findAll().size();
        entityM.setId(count.incrementAndGet());

        // Create the EntityM
        EntityMDTO entityMDTO = entityMMapper.toDto(entityM);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityMMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(entityMDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EntityM in the database
        List<EntityM> entityMList = entityMRepository.findAll();
        assertThat(entityMList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamEntityM() throws Exception {
        int databaseSizeBeforeUpdate = entityMRepository.findAll().size();
        entityM.setId(count.incrementAndGet());

        // Create the EntityM
        EntityMDTO entityMDTO = entityMMapper.toDto(entityM);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEntityMMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(entityMDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the EntityM in the database
        List<EntityM> entityMList = entityMRepository.findAll();
        assertThat(entityMList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteEntityM() throws Exception {
        // Initialize the database
        entityMRepository.saveAndFlush(entityM);

        int databaseSizeBeforeDelete = entityMRepository.findAll().size();

        // Delete the entityM
        restEntityMMockMvc
            .perform(delete(ENTITY_API_URL_ID, entityM.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EntityM> entityMList = entityMRepository.findAll();
        assertThat(entityMList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
