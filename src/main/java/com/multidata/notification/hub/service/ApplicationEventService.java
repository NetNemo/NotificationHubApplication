package com.multidata.notification.hub.service;

import com.multidata.notification.hub.service.dto.ApplicationEventDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing ApplicationEvent.
 */
public interface ApplicationEventService {

    /**
     * Save a applicationEvent.
     *
     * @param applicationEventDTO the entity to save
     * @return the persisted entity
     */
    ApplicationEventDTO save(ApplicationEventDTO applicationEventDTO);

    /**
     * Get all the applicationEvents.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ApplicationEventDTO> findAll(Pageable pageable);


    /**
     * Get the "id" applicationEvent.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ApplicationEventDTO> findOne(String id);

    /**
     * Delete the "id" applicationEvent.
     *
     * @param id the id of the entity
     */
    void delete(String id);
}
