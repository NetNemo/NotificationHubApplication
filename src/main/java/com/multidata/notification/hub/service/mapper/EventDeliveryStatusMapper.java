package com.multidata.notification.hub.service.mapper;

import com.multidata.notification.hub.domain.*;
import com.multidata.notification.hub.service.dto.EventDeliveryStatusDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity EventDeliveryStatus and its DTO EventDeliveryStatusDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EventDeliveryStatusMapper extends EntityMapper<EventDeliveryStatusDTO, EventDeliveryStatus> {


    @Mapping(target = "userChannelConfigurations", ignore = true)
    EventDeliveryStatus toEntity(EventDeliveryStatusDTO eventDeliveryStatusDTO);

    default EventDeliveryStatus fromId(String id) {
        if (id == null) {
            return null;
        }
        EventDeliveryStatus eventDeliveryStatus = new EventDeliveryStatus();
        eventDeliveryStatus.setId(id);
        return eventDeliveryStatus;
    }
}
