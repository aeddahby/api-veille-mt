package ma.iam.wissal.veille.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import ma.iam.wissal.veille.repository.EntityMRepository;
import ma.iam.wissal.veille.service.EntityMService;
import ma.iam.wissal.veille.service.HeaderUtil;
import ma.iam.wissal.veille.service.dto.EntityMDTO;
import ma.iam.wissal.veille.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link ma.iam.wissal.veille.domain.EntityM}.
 */
@RestController
@RequestMapping("/api")
public class EntityMResource {

    private final Logger log = LoggerFactory.getLogger(EntityMResource.class);

    private static final String ENTITY_NAME = "entityM";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EntityMService entityMService;

    private final EntityMRepository entityMRepository;

    public EntityMResource(EntityMService entityMService, EntityMRepository entityMRepository) {
        this.entityMService = entityMService;
        this.entityMRepository = entityMRepository;
    }

    /**
     * {@code POST  /entity-ms} : Create a new entityM.
     *
     * @param entityMDTO the entityMDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new entityMDTO, or with status {@code 400 (Bad Request)} if the entityM has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/entity-ms")
    public ResponseEntity<EntityMDTO> createEntityM(@RequestBody EntityMDTO entityMDTO) throws URISyntaxException {
        log.debug("REST request to save EntityM : {}", entityMDTO);
        if (entityMDTO.getId() != null) {
            throw new BadRequestAlertException("A new entityM cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EntityMDTO result = entityMService.save(entityMDTO);
        return ResponseEntity
            .created(new URI("/api/entity-ms/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /entity-ms/:id} : Updates an existing entityM.
     *
     * @param id the id of the entityMDTO to save.
     * @param entityMDTO the entityMDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated entityMDTO,
     * or with status {@code 400 (Bad Request)} if the entityMDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the entityMDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/entity-ms/{id}")
    public ResponseEntity<EntityMDTO> updateEntityM(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody EntityMDTO entityMDTO
    ) throws URISyntaxException {
        log.debug("REST request to update EntityM : {}, {}", id, entityMDTO);
        if (entityMDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, entityMDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!entityMRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        EntityMDTO result = entityMService.update(entityMDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, entityMDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /entity-ms/:id} : Partial updates given fields of an existing entityM, field will ignore if it is null
     *
     * @param id the id of the entityMDTO to save.
     * @param entityMDTO the entityMDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated entityMDTO,
     * or with status {@code 400 (Bad Request)} if the entityMDTO is not valid,
     * or with status {@code 404 (Not Found)} if the entityMDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the entityMDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/entity-ms/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<EntityMDTO> partialUpdateEntityM(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody EntityMDTO entityMDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update EntityM partially : {}, {}", id, entityMDTO);
        if (entityMDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, entityMDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!entityMRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<EntityMDTO> result = entityMService.partialUpdate(entityMDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, entityMDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /entity-ms} : get all the entityMS.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of entityMS in body.
     */
    @GetMapping("/entity-ms")
    public ResponseEntity<List<EntityMDTO>> getAllEntityMS(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of EntityMS");
        Page<EntityMDTO> page = entityMService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /entity-ms/:id} : get the "id" entityM.
     *
     * @param id the id of the entityMDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the entityMDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/entity-ms/{id}")
    public ResponseEntity<EntityMDTO> getEntityM(@PathVariable Long id) {
        log.debug("REST request to get EntityM : {}", id);
        Optional<EntityMDTO> entityMDTO = entityMService.findOne(id);
        return ResponseUtil.wrapOrNotFound(entityMDTO);
    }

    /**
     * {@code DELETE  /entity-ms/:id} : delete the "id" entityM.
     *
     * @param id the id of the entityMDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/entity-ms/{id}")
    public ResponseEntity<Void> deleteEntityM(@PathVariable Long id) {
        log.debug("REST request to delete EntityM : {}", id);
        entityMService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
