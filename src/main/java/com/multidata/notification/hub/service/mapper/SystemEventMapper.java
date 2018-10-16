package com.multidata.notification.hub.service.mapper;

import com.multidata.notification.hub.domain.*;
import com.multidata.notification.hub.service.dto.SystemEventDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity SystemEvent and its DTO SystemEventDTO.
 */
@Mapper(componentModel = "spring", uses = {UserChannelConfigurationMapper.class})
public interface SystemEventMapper extends EntityMapper<SystemEventDTO, SystemEvent> {

    @Mapping(source = "userChannelConfiguration.id", target = "userChannelConfigurationId")
    SystemEventDTO toDto(SystemEvent systemEvent);

    @Mapping(target = "events", ignore = true)
    @Mapping(source = "userChannelConfigurationId", target = "userChannelConfiguration")
    SystemEvent toEntity(SystemEventDTO systemEventDTO);

    default SystemEvent fromId(String id) {
        if (id == null) {
            return null;
        }
        SystemEvent systemEvent = new SystemEvent();
        systemEvent.setId(id);
        return systemEvent;
    }
}
