package com.multidata.notification.hub.service;

import com.multidata.notification.hub.service.dto.LdapUserDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing LdapUser.
 */
public interface LdapUserService {

    /**
     * Save a ldapUser.
     *
     * @param ldapUserDTO the entity to save
     * @return the persisted entity
     */
    LdapUserDTO save(LdapUserDTO ldapUserDTO);

    /**
     * Get all the ldapUsers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<LdapUserDTO> findAll(Pageable pageable);


    /**
     * Get the "id" ldapUser.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<LdapUserDTO> findOne(String id);

    /**
     * Delete the "id" ldapUser.
     *
     * @param id the id of the entity
     */
    void delete(String id);
}
