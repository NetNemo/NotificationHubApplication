package com.multidata.notification.hub.service;

import com.multidata.notification.hub.service.dto.EventDeliveryStatusDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing EventDeliveryStatus.
 */
public interface EventDeliveryStatusService {

    /**
     * Save a eventDeliveryStatus.
     *
     * @param eventDeliveryStatusDTO the entity to save
     * @return the persisted entity
     */
    EventDeliveryStatusDTO save(EventDeliveryStatusDTO eventDeliveryStatusDTO);

    /**
     * Get all the eventDeliveryStatuses.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<EventDeliveryStatusDTO> findAll(Pageable pageable);


    /**
     * Get the "id" eventDeliveryStatus.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<EventDeliveryStatusDTO> findOne(String id);

    /**
     * Delete the "id" eventDeliveryStatus.
     *
     * @param id the id of the entity
     */
    void delete(String id);
}
