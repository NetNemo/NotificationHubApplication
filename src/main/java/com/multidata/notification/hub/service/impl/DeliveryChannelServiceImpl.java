package com.multidata.notification.hub.service.impl;

import com.multidata.notification.hub.service.DeliveryChannelService;
import com.multidata.notification.hub.domain.DeliveryChannel;
import com.multidata.notification.hub.repository.DeliveryChannelRepository;
import com.multidata.notification.hub.service.dto.DeliveryChannelDTO;
import com.multidata.notification.hub.service.mapper.DeliveryChannelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing DeliveryChannel.
 */
@Service
public class DeliveryChannelServiceImpl implements DeliveryChannelService {

    private final Logger log = LoggerFactory.getLogger(DeliveryChannelServiceImpl.class);

    private final DeliveryChannelRepository deliveryChannelRepository;

    private final DeliveryChannelMapper deliveryChannelMapper;

    public DeliveryChannelServiceImpl(DeliveryChannelRepository deliveryChannelRepository, DeliveryChannelMapper deliveryChannelMapper) {
        this.deliveryChannelRepository = deliveryChannelRepository;
        this.deliveryChannelMapper = deliveryChannelMapper;
    }

    /**
     * Save a deliveryChannel.
     *
     * @param deliveryChannelDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public DeliveryChannelDTO save(DeliveryChannelDTO deliveryChannelDTO) {
        log.debug("Request to save DeliveryChannel : {}", deliveryChannelDTO);

        DeliveryChannel deliveryChannel = deliveryChannelMapper.toEntity(deliveryChannelDTO);
        deliveryChannel = deliveryChannelRepository.save(deliveryChannel);
        return deliveryChannelMapper.toDto(deliveryChannel);
    }

    /**
     * Get all the deliveryChannels.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    public Page<DeliveryChannelDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DeliveryChannels");
        return deliveryChannelRepository.findAll(pageable)
            .map(deliveryChannelMapper::toDto);
    }


    /**
     * Get one deliveryChannel by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Optional<DeliveryChannelDTO> findOne(String id) {
        log.debug("Request to get DeliveryChannel : {}", id);
        return deliveryChannelRepository.findById(id)
            .map(deliveryChannelMapper::toDto);
    }

    /**
     * Delete the deliveryChannel by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete DeliveryChannel : {}", id);
        deliveryChannelRepository.deleteById(id);
    }
}
