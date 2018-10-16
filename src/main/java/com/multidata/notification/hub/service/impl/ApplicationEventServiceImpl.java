package com.multidata.notification.hub.service.impl;

import com.multidata.notification.hub.service.ApplicationEventService;
import com.multidata.notification.hub.domain.ApplicationEvent;
import com.multidata.notification.hub.repository.ApplicationEventRepository;
import com.multidata.notification.hub.service.dto.ApplicationEventDTO;
import com.multidata.notification.hub.service.mapper.ApplicationEventMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing ApplicationEvent.
 */
@Service
public class ApplicationEventServiceImpl implements ApplicationEventService {

    private final Logger log = LoggerFactory.getLogger(ApplicationEventServiceImpl.class);

    private final ApplicationEventRepository applicationEventRepository;

    private final ApplicationEventMapper applicationEventMapper;

    public ApplicationEventServiceImpl(ApplicationEventRepository applicationEventRepository, ApplicationEventMapper applicationEventMapper) {
        this.applicationEventRepository = applicationEventRepository;
        this.applicationEventMapper = applicationEventMapper;
    }

    /**
     * Save a applicationEvent.
     *
     * @param applicationEventDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ApplicationEventDTO save(ApplicationEventDTO applicationEventDTO) {
        log.debug("Request to save ApplicationEvent : {}", applicationEventDTO);

        ApplicationEvent applicationEvent = applicationEventMapper.toEntity(applicationEventDTO);
        applicationEvent = applicationEventRepository.save(applicationEvent);
        return applicationEventMapper.toDto(applicationEvent);
    }

    /**
     * Get all the applicationEvents.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    public Page<ApplicationEventDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ApplicationEvents");
        return applicationEventRepository.findAll(pageable)
            .map(applicationEventMapper::toDto);
    }


    /**
     * Get one applicationEvent by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Optional<ApplicationEventDTO> findOne(String id) {
        log.debug("Request to get ApplicationEvent : {}", id);
        return applicationEventRepository.findById(id)
            .map(applicationEventMapper::toDto);
    }

    /**
     * Delete the applicationEvent by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete ApplicationEvent : {}", id);
        applicationEventRepository.deleteById(id);
    }
}
