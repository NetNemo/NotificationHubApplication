package com.multidata.notification.hub.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.multidata.notification.hub.service.UserChannelConfigurationService;
import com.multidata.notification.hub.web.rest.errors.BadRequestAlertException;
import com.multidata.notification.hub.web.rest.util.HeaderUtil;
import com.multidata.notification.hub.web.rest.util.PaginationUtil;
import com.multidata.notification.hub.service.dto.UserChannelConfigurationDTO;
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
 * REST controller for managing UserChannelConfiguration.
 */
@RestController
@RequestMapping("/api")
public class UserChannelConfigurationResource {

    private final Logger log = LoggerFactory.getLogger(UserChannelConfigurationResource.class);

    private static final String ENTITY_NAME = "userChannelConfiguration";

    private final UserChannelConfigurationService userChannelConfigurationService;

    public UserChannelConfigurationResource(UserChannelConfigurationService userChannelConfigurationService) {
        this.userChannelConfigurationService = userChannelConfigurationService;
    }

    /**
     * POST  /user-channel-configurations : Create a new userChannelConfiguration.
     *
     * @param userChannelConfigurationDTO the userChannelConfigurationDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new userChannelConfigurationDTO, or with status 400 (Bad Request) if the userChannelConfiguration has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/user-channel-configurations")
    @Timed
    public ResponseEntity<UserChannelConfigurationDTO> createUserChannelConfiguration(@RequestBody UserChannelConfigurationDTO userChannelConfigurationDTO) throws URISyntaxException {
        log.debug("REST request to save UserChannelConfiguration : {}", userChannelConfigurationDTO);
        if (userChannelConfigurationDTO.getId() != null) {
            throw new BadRequestAlertException("A new userChannelConfiguration cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserChannelConfigurationDTO result = userChannelConfigurationService.save(userChannelConfigurationDTO);
        return ResponseEntity.created(new URI("/api/user-channel-configurations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /user-channel-configurations : Updates an existing userChannelConfiguration.
     *
     * @param userChannelConfigurationDTO the userChannelConfigurationDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated userChannelConfigurationDTO,
     * or with status 400 (Bad Request) if the userChannelConfigurationDTO is not valid,
     * or with status 500 (Internal Server Error) if the userChannelConfigurationDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/user-channel-configurations")
    @Timed
    public ResponseEntity<UserChannelConfigurationDTO> updateUserChannelConfiguration(@RequestBody UserChannelConfigurationDTO userChannelConfigurationDTO) throws URISyntaxException {
        log.debug("REST request to update UserChannelConfiguration : {}", userChannelConfigurationDTO);
        if (userChannelConfigurationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UserChannelConfigurationDTO result = userChannelConfigurationService.save(userChannelConfigurationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, userChannelConfigurationDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /user-channel-configurations : get all the userChannelConfigurations.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of userChannelConfigurations in body
     */
    @GetMapping("/user-channel-configurations")
    @Timed
    public ResponseEntity<List<UserChannelConfigurationDTO>> getAllUserChannelConfigurations(Pageable pageable) {
        log.debug("REST request to get a page of UserChannelConfigurations");
        Page<UserChannelConfigurationDTO> page = userChannelConfigurationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/user-channel-configurations");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /user-channel-configurations/:id : get the "id" userChannelConfiguration.
     *
     * @param id the id of the userChannelConfigurationDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the userChannelConfigurationDTO, or with status 404 (Not Found)
     */
    @GetMapping("/user-channel-configurations/{id}")
    @Timed
    public ResponseEntity<UserChannelConfigurationDTO> getUserChannelConfiguration(@PathVariable String id) {
        log.debug("REST request to get UserChannelConfiguration : {}", id);
        Optional<UserChannelConfigurationDTO> userChannelConfigurationDTO = userChannelConfigurationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userChannelConfigurationDTO);
    }

    /**
     * DELETE  /user-channel-configurations/:id : delete the "id" userChannelConfiguration.
     *
     * @param id the id of the userChannelConfigurationDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/user-channel-configurations/{id}")
    @Timed
    public ResponseEntity<Void> deleteUserChannelConfiguration(@PathVariable String id) {
        log.debug("REST request to delete UserChannelConfiguration : {}", id);
        userChannelConfigurationService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
