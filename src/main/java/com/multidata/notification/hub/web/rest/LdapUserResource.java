package com.multidata.notification.hub.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.multidata.notification.hub.service.LdapUserService;
import com.multidata.notification.hub.web.rest.errors.BadRequestAlertException;
import com.multidata.notification.hub.web.rest.util.HeaderUtil;
import com.multidata.notification.hub.web.rest.util.PaginationUtil;
import com.multidata.notification.hub.service.dto.LdapUserDTO;
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
 * REST controller for managing LdapUser.
 */
@RestController
@RequestMapping("/api")
public class LdapUserResource {

    private final Logger log = LoggerFactory.getLogger(LdapUserResource.class);

    private static final String ENTITY_NAME = "ldapUser";

    private final LdapUserService ldapUserService;

    public LdapUserResource(LdapUserService ldapUserService) {
        this.ldapUserService = ldapUserService;
    }

    /**
     * POST  /ldap-users : Create a new ldapUser.
     *
     * @param ldapUserDTO the ldapUserDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new ldapUserDTO, or with status 400 (Bad Request) if the ldapUser has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ldap-users")
    @Timed
    public ResponseEntity<LdapUserDTO> createLdapUser(@RequestBody LdapUserDTO ldapUserDTO) throws URISyntaxException {
        log.debug("REST request to save LdapUser : {}", ldapUserDTO);
        if (ldapUserDTO.getId() != null) {
            throw new BadRequestAlertException("A new ldapUser cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LdapUserDTO result = ldapUserService.save(ldapUserDTO);
        return ResponseEntity.created(new URI("/api/ldap-users/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ldap-users : Updates an existing ldapUser.
     *
     * @param ldapUserDTO the ldapUserDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated ldapUserDTO,
     * or with status 400 (Bad Request) if the ldapUserDTO is not valid,
     * or with status 500 (Internal Server Error) if the ldapUserDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ldap-users")
    @Timed
    public ResponseEntity<LdapUserDTO> updateLdapUser(@RequestBody LdapUserDTO ldapUserDTO) throws URISyntaxException {
        log.debug("REST request to update LdapUser : {}", ldapUserDTO);
        if (ldapUserDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LdapUserDTO result = ldapUserService.save(ldapUserDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ldapUserDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ldap-users : get all the ldapUsers.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of ldapUsers in body
     */
    @GetMapping("/ldap-users")
    @Timed
    public ResponseEntity<List<LdapUserDTO>> getAllLdapUsers(Pageable pageable) {
        log.debug("REST request to get a page of LdapUsers");
        Page<LdapUserDTO> page = ldapUserService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/ldap-users");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /ldap-users/:id : get the "id" ldapUser.
     *
     * @param id the id of the ldapUserDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the ldapUserDTO, or with status 404 (Not Found)
     */
    @GetMapping("/ldap-users/{id}")
    @Timed
    public ResponseEntity<LdapUserDTO> getLdapUser(@PathVariable String id) {
        log.debug("REST request to get LdapUser : {}", id);
        Optional<LdapUserDTO> ldapUserDTO = ldapUserService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ldapUserDTO);
    }

    /**
     * DELETE  /ldap-users/:id : delete the "id" ldapUser.
     *
     * @param id the id of the ldapUserDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ldap-users/{id}")
    @Timed
    public ResponseEntity<Void> deleteLdapUser(@PathVariable String id) {
        log.debug("REST request to delete LdapUser : {}", id);
        ldapUserService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
