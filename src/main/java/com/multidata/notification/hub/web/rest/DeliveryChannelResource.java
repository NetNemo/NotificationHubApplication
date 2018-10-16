package com.multidata.notification.hub.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.multidata.notification.hub.service.DeliveryChannelService;
import com.multidata.notification.hub.web.rest.errors.BadRequestAlertException;
import com.multidata.notification.hub.web.rest.util.HeaderUtil;
import com.multidata.notification.hub.web.rest.util.PaginationUtil;
import com.multidata.notification.hub.service.dto.DeliveryChannelDTO;
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
 * REST controller for managing DeliveryChannel.
 */
@RestController
@RequestMapping("/api")
public class DeliveryChannelResource {

    private final Logger log = LoggerFactory.getLogger(DeliveryChannelResource.class);

    private static final String ENTITY_NAME = "deliveryChannel";

    private final DeliveryChannelService deliveryChannelService;

    public DeliveryChannelResource(DeliveryChannelService deliveryChannelService) {
        this.deliveryChannelService = deliveryChannelService;
    }

    /**
     * POST  /delivery-channels : Create a new deliveryChannel.
     *
     * @param deliveryChannelDTO the deliveryChannelDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new deliveryChannelDTO, or with status 400 (Bad Request) if the deliveryChannel has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/delivery-channels")
    @Timed
    public ResponseEntity<DeliveryChannelDTO> createDeliveryChannel(@RequestBody DeliveryChannelDTO deliveryChannelDTO) throws URISyntaxException {
        log.debug("REST request to save DeliveryChannel : {}", deliveryChannelDTO);
        if (deliveryChannelDTO.getId() != null) {
            throw new BadRequestAlertException("A new deliveryChannel cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DeliveryChannelDTO result = deliveryChannelService.save(deliveryChannelDTO);
        return ResponseEntity.created(new URI("/api/delivery-channels/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /delivery-channels : Updates an existing deliveryChannel.
     *
     * @param deliveryChannelDTO the deliveryChannelDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated deliveryChannelDTO,
     * or with status 400 (Bad Request) if the deliveryChannelDTO is not valid,
     * or with status 500 (Internal Server Error) if the deliveryChannelDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/delivery-channels")
    @Timed
    public ResponseEntity<DeliveryChannelDTO> updateDeliveryChannel(@RequestBody DeliveryChannelDTO deliveryChannelDTO) throws URISyntaxException {
        log.debug("REST request to update DeliveryChannel : {}", deliveryChannelDTO);
        if (deliveryChannelDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DeliveryChannelDTO result = deliveryChannelService.save(deliveryChannelDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, deliveryChannelDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /delivery-channels : get all the deliveryChannels.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of deliveryChannels in body
     */
    @GetMapping("/delivery-channels")
    @Timed
    public ResponseEntity<List<DeliveryChannelDTO>> getAllDeliveryChannels(Pageable pageable) {
        log.debug("REST request to get a page of DeliveryChannels");
        Page<DeliveryChannelDTO> page = deliveryChannelService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/delivery-channels");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /delivery-channels/:id : get the "id" deliveryChannel.
     *
     * @param id the id of the deliveryChannelDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the deliveryChannelDTO, or with status 404 (Not Found)
     */
    @GetMapping("/delivery-channels/{id}")
    @Timed
    public ResponseEntity<DeliveryChannelDTO> getDeliveryChannel(@PathVariable String id) {
        log.debug("REST request to get DeliveryChannel : {}", id);
        Optional<DeliveryChannelDTO> deliveryChannelDTO = deliveryChannelService.findOne(id);
        return ResponseUtil.wrapOrNotFound(deliveryChannelDTO);
    }

    /**
     * DELETE  /delivery-channels/:id : delete the "id" deliveryChannel.
     *
     * @param id the id of the deliveryChannelDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/delivery-channels/{id}")
    @Timed
    public ResponseEntity<Void> deleteDeliveryChannel(@PathVariable String id) {
        log.debug("REST request to delete DeliveryChannel : {}", id);
        deliveryChannelService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
