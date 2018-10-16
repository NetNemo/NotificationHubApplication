package com.multidata.notification.hub.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.multidata.notification.hub.service.SystemEventService;
import com.multidata.notification.hub.web.rest.errors.BadRequestAlertException;
import com.multidata.notification.hub.web.rest.util.HeaderUtil;
import com.multidata.notification.hub.web.rest.util.PaginationUtil;
import com.multidata.notification.hub.service.dto.SystemEventDTO;
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
 * REST controller for managing SystemEvent.
 */
@RestController
@RequestMapping("/api")
public class SystemEventResource {

    private final Logger log = LoggerFactory.getLogger(SystemEventResource.class);

    private static final String ENTITY_NAME = "systemEvent";

    private final SystemEventService systemEventService;

    public SystemEventResource(SystemEventService systemEventService) {
        this.systemEventService = systemEventService;
    }

    /**
     * POST  /system-events : Create a new systemEvent.
     *
     * @param systemEventDTO the systemEventDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new systemEventDTO, or with status 400 (Bad Request) if the systemEvent has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/system-events")
    @Timed
    public ResponseEntity<SystemEventDTO> createSystemEvent(@RequestBody SystemEventDTO systemEventDTO) throws URISyntaxException {
        log.debug("REST request to save SystemEvent : {}", systemEventDTO);
        if (systemEventDTO.getId() != null) {
            throw new BadRequestAlertException("A new systemEvent cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SystemEventDTO result = systemEventService.save(systemEventDTO);
        return ResponseEntity.created(new URI("/api/system-events/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /system-events : Updates an existing systemEvent.
     *
     * @param systemEventDTO the systemEventDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated systemEventDTO,
     * or with status 400 (Bad Request) if the systemEventDTO is not valid,
     * or with status 500 (Internal Server Error) if the systemEventDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/system-events")
    @Timed
    public ResponseEntity<SystemEventDTO> updateSystemEvent(@RequestBody SystemEventDTO systemEventDTO) throws URISyntaxException {
        log.debug("REST request to update SystemEvent : {}", systemEventDTO);
        if (systemEventDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SystemEventDTO result = systemEventService.save(systemEventDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, systemEventDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /system-events : get all the systemEvents.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of systemEvents in body
     */
    @GetMapping("/system-events")
    @Timed
    public ResponseEntity<List<SystemEventDTO>> getAllSystemEvents(Pageable pageable) {
        log.debug("REST request to get a page of SystemEvents");
        Page<SystemEventDTO> page = systemEventService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/system-events");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /system-events/:id : get the "id" systemEvent.
     *
     * @param id the id of the systemEventDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the systemEventDTO, or with status 404 (Not Found)
     */
    @GetMapping("/system-events/{id}")
    @Timed
    public ResponseEntity<SystemEventDTO> getSystemEvent(@PathVariable String id) {
        log.debug("REST request to get SystemEvent : {}", id);
        Optional<SystemEventDTO> systemEventDTO = systemEventService.findOne(id);
        return ResponseUtil.wrapOrNotFound(systemEventDTO);
    }

    /**
     * DELETE  /system-events/:id : delete the "id" systemEvent.
     *
     * @param id the id of the systemEventDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/system-events/{id}")
    @Timed
    public ResponseEntity<Void> deleteSystemEvent(@PathVariable String id) {
        log.debug("REST request to delete SystemEvent : {}", id);
        systemEventService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
