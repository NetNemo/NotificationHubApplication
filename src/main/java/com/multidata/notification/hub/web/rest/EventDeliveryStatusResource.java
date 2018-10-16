package com.multidata.notification.hub.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.multidata.notification.hub.service.EventDeliveryStatusService;
import com.multidata.notification.hub.web.rest.errors.BadRequestAlertException;
import com.multidata.notification.hub.web.rest.util.HeaderUtil;
import com.multidata.notification.hub.web.rest.util.PaginationUtil;
import com.multidata.notification.hub.service.dto.EventDeliveryStatusDTO;
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
 * REST controller for managing EventDeliveryStatus.
 */
@RestController
@RequestMapping("/api")
public class EventDeliveryStatusResource {

    private final Logger log = LoggerFactory.getLogger(EventDeliveryStatusResource.class);

    private static final String ENTITY_NAME = "eventDeliveryStatus";

    private final EventDeliveryStatusService eventDeliveryStatusService;

    public EventDeliveryStatusResource(EventDeliveryStatusService eventDeliveryStatusService) {
        this.eventDeliveryStatusService = eventDeliveryStatusService;
    }

    /**
     * POST  /event-delivery-statuses : Create a new eventDeliveryStatus.
     *
     * @param eventDeliveryStatusDTO the eventDeliveryStatusDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new eventDeliveryStatusDTO, or with status 400 (Bad Request) if the eventDeliveryStatus has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/event-delivery-statuses")
    @Timed
    public ResponseEntity<EventDeliveryStatusDTO> createEventDeliveryStatus(@RequestBody EventDeliveryStatusDTO eventDeliveryStatusDTO) throws URISyntaxException {
        log.debug("REST request to save EventDeliveryStatus : {}", eventDeliveryStatusDTO);
        if (eventDeliveryStatusDTO.getId() != null) {
            throw new BadRequestAlertException("A new eventDeliveryStatus cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EventDeliveryStatusDTO result = eventDeliveryStatusService.save(eventDeliveryStatusDTO);
        return ResponseEntity.created(new URI("/api/event-delivery-statuses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /event-delivery-statuses : Updates an existing eventDeliveryStatus.
     *
     * @param eventDeliveryStatusDTO the eventDeliveryStatusDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated eventDeliveryStatusDTO,
     * or with status 400 (Bad Request) if the eventDeliveryStatusDTO is not valid,
     * or with status 500 (Internal Server Error) if the eventDeliveryStatusDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/event-delivery-statuses")
    @Timed
    public ResponseEntity<EventDeliveryStatusDTO> updateEventDeliveryStatus(@RequestBody EventDeliveryStatusDTO eventDeliveryStatusDTO) throws URISyntaxException {
        log.debug("REST request to update EventDeliveryStatus : {}", eventDeliveryStatusDTO);
        if (eventDeliveryStatusDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EventDeliveryStatusDTO result = eventDeliveryStatusService.save(eventDeliveryStatusDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, eventDeliveryStatusDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /event-delivery-statuses : get all the eventDeliveryStatuses.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of eventDeliveryStatuses in body
     */
    @GetMapping("/event-delivery-statuses")
    @Timed
    public ResponseEntity<List<EventDeliveryStatusDTO>> getAllEventDeliveryStatuses(Pageable pageable) {
        log.debug("REST request to get a page of EventDeliveryStatuses");
        Page<EventDeliveryStatusDTO> page = eventDeliveryStatusService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/event-delivery-statuses");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /event-delivery-statuses/:id : get the "id" eventDeliveryStatus.
     *
     * @param id the id of the eventDeliveryStatusDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the eventDeliveryStatusDTO, or with status 404 (Not Found)
     */
    @GetMapping("/event-delivery-statuses/{id}")
    @Timed
    public ResponseEntity<EventDeliveryStatusDTO> getEventDeliveryStatus(@PathVariable String id) {
        log.debug("REST request to get EventDeliveryStatus : {}", id);
        Optional<EventDeliveryStatusDTO> eventDeliveryStatusDTO = eventDeliveryStatusService.findOne(id);
        return ResponseUtil.wrapOrNotFound(eventDeliveryStatusDTO);
    }

    /**
     * DELETE  /event-delivery-statuses/:id : delete the "id" eventDeliveryStatus.
     *
     * @param id the id of the eventDeliveryStatusDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/event-delivery-statuses/{id}")
    @Timed
    public ResponseEntity<Void> deleteEventDeliveryStatus(@PathVariable String id) {
        log.debug("REST request to delete EventDeliveryStatus : {}", id);
        eventDeliveryStatusService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
