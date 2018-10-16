package com.multidata.notification.hub.service.mapper;

import com.multidata.notification.hub.domain.*;
import com.multidata.notification.hub.service.dto.DeliveryChannelDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity DeliveryChannel and its DTO DeliveryChannelDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DeliveryChannelMapper extends EntityMapper<DeliveryChannelDTO, DeliveryChannel> {


    @Mapping(target = "userChannelConfigurations", ignore = true)
    DeliveryChannel toEntity(DeliveryChannelDTO deliveryChannelDTO);

    default DeliveryChannel fromId(String id) {
        if (id == null) {
            return null;
        }
        DeliveryChannel deliveryChannel = new DeliveryChannel();
        deliveryChannel.setId(id);
        return deliveryChannel;
    }
}
