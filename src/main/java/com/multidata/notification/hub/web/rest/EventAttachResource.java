package com.multidata.notification.hub.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.multidata.notification.hub.service.EventAttachService;
import com.multidata.notification.hub.web.rest.errors.BadRequestAlertException;
import com.multidata.notification.hub.web.rest.util.HeaderUtil;
import com.multidata.notification.hub.web.rest.util.PaginationUtil;
import com.multidata.notification.hub.service.dto.EventAttachDTO;
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
 * REST controller for managing EventAttach.
 */
@RestController
@RequestMapping("/api")
public class EventAttachResource {

    private final Logger log = LoggerFactory.getLogger(EventAttachResource.class);

    private static final String ENTITY_NAME = "eventAttach";

    private final EventAttachService eventAttachService;

    public EventAttachResource(EventAttachService eventAttachService) {
        this.eventAttachService = eventAttachService;
    }

    /**
     * POST  /event-attaches : Create a new eventAttach.
     *
     * @param eventAttachDTO the eventAttachDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new eventAttachDTO, or with status 400 (Bad Request) if the eventAttach has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/event-attaches")
    @Timed
    public ResponseEntity<EventAttachDTO> createEventAttach(@RequestBody EventAttachDTO eventAttachDTO) throws URISyntaxException {
        log.debug("REST request to save EventAttach : {}", eventAttachDTO);
        if (eventAttachDTO.getId() != null) {
            throw new BadRequestAlertException("A new eventAttach cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EventAttachDTO result = eventAttachService.save(eventAttachDTO);
        return ResponseEntity.created(new URI("/api/event-attaches/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /event-attaches : Updates an existing eventAttach.
     *
     * @param eventAttachDTO the eventAttachDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated eventAttachDTO,
     * or with status 400 (Bad Request) if the eventAttachDTO is not valid,
     * or with status 500 (Internal Server Error) if the eventAttachDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/event-attaches")
    @Timed
    public ResponseEntity<EventAttachDTO> updateEventAttach(@RequestBody EventAttachDTO eventAttachDTO) throws URISyntaxException {
        log.debug("REST request to update EventAttach : {}", eventAttachDTO);
        if (eventAttachDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EventAttachDTO result = eventAttachService.save(eventAttachDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, eventAttachDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /event-attaches : get all the eventAttaches.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of eventAttaches in body
     */
    @GetMapping("/event-attaches")
    @Timed
    public ResponseEntity<List<EventAttachDTO>> getAllEventAttaches(Pageable pageable) {
        log.debug("REST request to get a page of EventAttaches");
        Page<EventAttachDTO> page = eventAttachService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/event-attaches");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /event-attaches/:id : get the "id" eventAttach.
     *
     * @param id the id of the eventAttachDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the eventAttachDTO, or with status 404 (Not Found)
     */
    @GetMapping("/event-attaches/{id}")
    @Timed
    public ResponseEntity<EventAttachDTO> getEventAttach(@PathVariable String id) {
        log.debug("REST request to get EventAttach : {}", id);
        Optional<EventAttachDTO> eventAttachDTO = eventAttachService.findOne(id);
        return ResponseUtil.wrapOrNotFound(eventAttachDTO);
    }

    /**
     * DELETE  /event-attaches/:id : delete the "id" eventAttach.
     *
     * @param id the id of the eventAttachDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/event-attaches/{id}")
    @Timed
    public ResponseEntity<Void> deleteEventAttach(@PathVariable String id) {
        log.debug("REST request to delete EventAttach : {}", id);
        eventAttachService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
