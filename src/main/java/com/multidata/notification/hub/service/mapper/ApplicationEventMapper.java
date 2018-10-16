package com.multidata.notification.hub.service.mapper;

import com.multidata.notification.hub.domain.*;
import com.multidata.notification.hub.service.dto.ApplicationEventDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ApplicationEvent and its DTO ApplicationEventDTO.
 */
@Mapper(componentModel = "spring", uses = {UserChannelConfigurationMapper.class})
public interface ApplicationEventMapper extends EntityMapper<ApplicationEventDTO, ApplicationEvent> {

    @Mapping(source = "userChannelConfiguration.id", target = "userChannelConfigurationId")
    ApplicationEventDTO toDto(ApplicationEvent applicationEvent);

    @Mapping(target = "events", ignore = true)
    @Mapping(source = "userChannelConfigurationId", target = "userChannelConfiguration")
    ApplicationEvent toEntity(ApplicationEventDTO applicationEventDTO);

    default ApplicationEvent fromId(String id) {
        if (id == null) {
            return null;
        }
        ApplicationEvent applicationEvent = new ApplicationEvent();
        applicationEvent.setId(id);
        return applicationEvent;
    }
}
