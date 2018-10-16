package com.multidata.notification.hub.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.multidata.notification.hub.service.ApplicationEventService;
import com.multidata.notification.hub.web.rest.errors.BadRequestAlertException;
import com.multidata.notification.hub.web.rest.util.HeaderUtil;
import com.multidata.notification.hub.web.rest.util.PaginationUtil;
import com.multidata.notification.hub.service.dto.ApplicationEventDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing ApplicationEvent.
 */
@RestController
@RequestMapping("/api")
public class ApplicationEventResource {

    private final Logger log = LoggerFactory.getLogger(ApplicationEventResource.class);

    private static final String ENTITY_NAME = "applicationEvent";

    private final ApplicationEventService applicationEventService;

    public ApplicationEventResource(ApplicationEventService applicationEventService) {
        this.applicationEventService = applicationEventService;
    }

    /**
     * POST  /application-events : Create a new applicationEvent.
     *
     * @param applicationEventDTO the applicationEventDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new applicationEventDTO, or with status 400 (Bad Request) if the applicationEvent has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/application-events")
    @Timed
    public ResponseEntity<ApplicationEventDTO> createApplicationEvent(@RequestBody ApplicationEventDTO applicationEventDTO) throws URISyntaxException {
        log.debug("REST request to save ApplicationEvent : {}", applicationEventDTO);
        if (applicationEventDTO.getId() != null) {
            throw new BadRequestAlertException("A new applicationEvent cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ApplicationEventDTO result = applicationEventService.save(applicationEventDTO);
        return ResponseEntity.created(new URI("/api/application-events/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /application-events : Updates an existing applicationEvent.
     *
     * @param applicationEventDTO the applicationEventDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated applicationEventDTO,
     * or with status 400 (Bad Request) if the applicationEventDTO is not valid,
     * or with status 500 (Internal Server Error) if the applicationEventDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/application-events")
    @Timed
    public ResponseEntity<ApplicationEventDTO> updateApplicationEvent(@RequestBody ApplicationEventDTO applicationEventDTO) throws URISyntaxException {
        log.debug("REST request to update ApplicationEvent : {}", applicationEventDTO);
        if (applicationEventDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ApplicationEventDTO result = applicationEventService.save(applicationEventDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, applicationEventDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /application-events : get all the applicationEvents.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of applicationEvents in body
     */
    @GetMapping("/application-events")
    @Timed
    public ResponseEntity<List<ApplicationEventDTO>> getAllApplicationEvents(Pageable pageable) {
        log.debug("REST request to get a page of ApplicationEvents");
        Page<ApplicationEventDTO> page = applicationEventService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/application-events");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /application-events/:id : get the "id" applicationEvent.
     *
     * @param id the id of the applicationEventDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the applicationEventDTO, or with status 404 (Not Found)
     */
    @GetMapping("/application-events/{id}")
    @Timed
    public ResponseEntity<ApplicationEventDTO> getApplicationEvent(@PathVariable String id) {
        log.debug("REST request to get ApplicationEvent : {}", id);
        Optional<ApplicationEventDTO> applicationEventDTO = applicationEventService.findOne(id);
        return ResponseUtil.wrapOrNotFound(applicationEventDTO);
    }

    /**
     * DELETE  /application-events/:id : delete the "id" applicationEvent.
     *
     * @param id the id of the applicationEventDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/application-events/{id}")
    @Timed
    public ResponseEntity<Void> deleteApplicationEvent(@PathVariable String id) {
        log.debug("REST request to delete ApplicationEvent : {}", id);
        applicationEventService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
