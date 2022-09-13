package ma.iam.wissal.veille.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import ma.iam.wissal.veille.repository.DirectionRegionaleRepository;
import ma.iam.wissal.veille.service.DirectionRegionaleService;
import ma.iam.wissal.veille.service.HeaderUtil;
import ma.iam.wissal.veille.service.dto.DirectionRegionaleDTO;
import ma.iam.wissal.veille.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link ma.iam.wissal.veille.domain.DirectionRegionale}.
 */
@RestController
@RequestMapping("/api")
public class DirectionRegionaleResource {

    private final Logger log = LoggerFactory.getLogger(DirectionRegionaleResource.class);

    private static final String ENTITY_NAME = "directionRegionale";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DirectionRegionaleService directionRegionaleService;

    private final DirectionRegionaleRepository directionRegionaleRepository;

    public DirectionRegionaleResource(
        DirectionRegionaleService directionRegionaleService,
        DirectionRegionaleRepository directionRegionaleRepository
    ) {
        this.directionRegionaleService = directionRegionaleService;
        this.directionRegionaleRepository = directionRegionaleRepository;
    }

    /**
     * {@code POST  /direction-regionales} : Create a new directionRegionale.
     *
     * @param directionRegionaleDTO the directionRegionaleDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new directionRegionaleDTO, or with status {@code 400 (Bad Request)} if the directionRegionale has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/direction-regionales")
    public ResponseEntity<DirectionRegionaleDTO> createDirectionRegionale(@RequestBody DirectionRegionaleDTO directionRegionaleDTO)
        throws URISyntaxException {
        log.debug("REST request to save DirectionRegionale : {}", directionRegionaleDTO);
        if (directionRegionaleDTO.getId() != null) {
            throw new BadRequestAlertException("A new directionRegionale cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DirectionRegionaleDTO result = directionRegionaleService.save(directionRegionaleDTO);
        return ResponseEntity
            .created(new URI("/api/direction-regionales/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /direction-regionales/:id} : Updates an existing directionRegionale.
     *
     * @param id the id of the directionRegionaleDTO to save.
     * @param directionRegionaleDTO the directionRegionaleDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated directionRegionaleDTO,
     * or with status {@code 400 (Bad Request)} if the directionRegionaleDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the directionRegionaleDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/direction-regionales/{id}")
    public ResponseEntity<DirectionRegionaleDTO> updateDirectionRegionale(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DirectionRegionaleDTO directionRegionaleDTO
    ) throws URISyntaxException {
        log.debug("REST request to update DirectionRegionale : {}, {}", id, directionRegionaleDTO);
        if (directionRegionaleDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, directionRegionaleDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!directionRegionaleRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        DirectionRegionaleDTO result = directionRegionaleService.update(directionRegionaleDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, directionRegionaleDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /direction-regionales/:id} : Partial updates given fields of an existing directionRegionale, field will ignore if it is null
     *
     * @param id the id of the directionRegionaleDTO to save.
     * @param directionRegionaleDTO the directionRegionaleDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated directionRegionaleDTO,
     * or with status {@code 400 (Bad Request)} if the directionRegionaleDTO is not valid,
     * or with status {@code 404 (Not Found)} if the directionRegionaleDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the directionRegionaleDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/direction-regionales/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DirectionRegionaleDTO> partialUpdateDirectionRegionale(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DirectionRegionaleDTO directionRegionaleDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update DirectionRegionale partially : {}, {}", id, directionRegionaleDTO);
        if (directionRegionaleDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, directionRegionaleDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!directionRegionaleRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DirectionRegionaleDTO> result = directionRegionaleService.partialUpdate(directionRegionaleDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, directionRegionaleDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /direction-regionales} : get all the directionRegionales.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of directionRegionales in body.
     */
    @GetMapping("/direction-regionales")
    public ResponseEntity<List<DirectionRegionaleDTO>> getAllDirectionRegionales(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of DirectionRegionales");
        Page<DirectionRegionaleDTO> page = directionRegionaleService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /direction-regionales/:id} : get the "id" directionRegionale.
     *
     * @param id the id of the directionRegionaleDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the directionRegionaleDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/direction-regionales/{id}")
    public ResponseEntity<DirectionRegionaleDTO> getDirectionRegionale(@PathVariable Long id) {
        log.debug("REST request to get DirectionRegionale : {}", id);
        Optional<DirectionRegionaleDTO> directionRegionaleDTO = directionRegionaleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(directionRegionaleDTO);
    }

    /**
     * {@code DELETE  /direction-regionales/:id} : delete the "id" directionRegionale.
     *
     * @param id the id of the directionRegionaleDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/direction-regionales/{id}")
    public ResponseEntity<Void> deleteDirectionRegionale(@PathVariable Long id) {
        log.debug("REST request to delete DirectionRegionale : {}", id);
        directionRegionaleService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
