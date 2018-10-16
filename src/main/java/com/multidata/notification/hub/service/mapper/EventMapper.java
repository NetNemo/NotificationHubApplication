package com.multidata.notification.hub.service.mapper;

import com.multidata.notification.hub.domain.*;
import com.multidata.notification.hub.service.dto.EventDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Event and its DTO EventDTO.
 */
@Mapper(componentModel = "spring", uses = {SystemEventMapper.class, ApplicationEventMapper.class})
public interface EventMapper extends EntityMapper<EventDTO, Event> {

    @Mapping(source = "systemEvent.id", target = "systemEventId")
    @Mapping(source = "applicationEvent.id", target = "applicationEventId")
    EventDTO toDto(Event event);

    @Mapping(target = "eventAttaches", ignore = true)
    @Mapping(source = "systemEventId", target = "systemEvent")
    @Mapping(source = "applicationEventId", target = "applicationEvent")
    Event toEntity(EventDTO eventDTO);

    default Event fromId(String id) {
        if (id == null) {
            return null;
        }
        Event event = new Event();
        event.setId(id);
        return event;
    }
}
