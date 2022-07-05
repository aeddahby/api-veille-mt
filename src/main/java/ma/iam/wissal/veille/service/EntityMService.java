package ma.iam.wissal.veille.service;

import java.util.Optional;
import ma.iam.wissal.veille.domain.EntityM;
import ma.iam.wissal.veille.repository.EntityMRepository;
import ma.iam.wissal.veille.service.dto.EntityMDTO;
import ma.iam.wissal.veille.service.mapper.EntityMMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link EntityM}.
 */
@Service
@Transactional
public class EntityMService {

    private final Logger log = LoggerFactory.getLogger(EntityMService.class);

    private final EntityMRepository entityMRepository;

    private final EntityMMapper entityMMapper;

    public EntityMService(EntityMRepository entityMRepository, EntityMMapper entityMMapper) {
        this.entityMRepository = entityMRepository;
        this.entityMMapper = entityMMapper;
    }

    /**
     * Save a entityM.
     *
     * @param entityMDTO the entity to save.
     * @return the persisted entity.
     */
    public EntityMDTO save(EntityMDTO entityMDTO) {
        log.debug("Request to save EntityM : {}", entityMDTO);
        EntityM entityM = entityMMapper.toEntity(entityMDTO);
        entityM = entityMRepository.save(entityM);
        return entityMMapper.toDto(entityM);
    }

    /**
     * Update a entityM.
     *
     * @param entityMDTO the entity to save.
     * @return the persisted entity.
     */
    public EntityMDTO update(EntityMDTO entityMDTO) {
        log.debug("Request to save EntityM : {}", entityMDTO);
        EntityM entityM = entityMMapper.toEntity(entityMDTO);
        entityM = entityMRepository.save(entityM);
        return entityMMapper.toDto(entityM);
    }

    /**
     * Partially update a entityM.
     *
     * @param entityMDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<EntityMDTO> partialUpdate(EntityMDTO entityMDTO) {
        log.debug("Request to partially update EntityM : {}", entityMDTO);

        return entityMRepository
            .findById(entityMDTO.getId())
            .map(existingEntityM -> {
                entityMMapper.partialUpdate(existingEntityM, entityMDTO);

                return existingEntityM;
            })
            .map(entityMRepository::save)
            .map(entityMMapper::toDto);
    }

    /**
     * Get all the entityMS.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<EntityMDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EntityMS");
        return entityMRepository.findAll(pageable).map(entityMMapper::toDto);
    }

    /**
     * Get one entityM by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EntityMDTO> findOne(Long id) {
        log.debug("Request to get EntityM : {}", id);
        return entityMRepository.findById(id).map(entityMMapper::toDto);
    }

    /**
     * Delete the entityM by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete EntityM : {}", id);
        entityMRepository.deleteById(id);
    }
}
