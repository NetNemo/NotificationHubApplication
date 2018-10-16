package com.multidata.notification.hub.service;

import com.multidata.notification.hub.service.dto.DeliveryChannelDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing DeliveryChannel.
 */
public interface DeliveryChannelService {

    /**
     * Save a deliveryChannel.
     *
     * @param deliveryChannelDTO the entity to save
     * @return the persisted entity
     */
    DeliveryChannelDTO save(DeliveryChannelDTO deliveryChannelDTO);

    /**
     * Get all the deliveryChannels.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<DeliveryChannelDTO> findAll(Pageable pageable);


    /**
     * Get the "id" deliveryChannel.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<DeliveryChannelDTO> findOne(String id);

    /**
     * Delete the "id" deliveryChannel.
     *
     * @param id the id of the entity
     */
    void delete(String id);
}
