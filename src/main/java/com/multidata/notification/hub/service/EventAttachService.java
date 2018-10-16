package com.multidata.notification.hub.service;

import com.multidata.notification.hub.service.dto.EventAttachDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing EventAttach.
 */
public interface EventAttachService {

    /**
     * Save a eventAttach.
     *
     * @param eventAttachDTO the entity to save
     * @return the persisted entity
     */
    EventAttachDTO save(EventAttachDTO eventAttachDTO);

    /**
     * Get all the eventAttaches.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<EventAttachDTO> findAll(Pageable pageable);


    /**
     * Get the "id" eventAttach.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<EventAttachDTO> findOne(String id);

    /**
     * Delete the "id" eventAttach.
     *
     * @param id the id of the entity
     */
    void delete(String id);
}
