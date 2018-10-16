package com.multidata.notification.hub.service.dto;

import java.io.Serializable;
import java.util.Objects;
import com.multidata.notification.hub.domain.enumeration.Channel;

/**
 * A DTO for the DeliveryChannel entity.
 */
public class DeliveryChannelDTO implements Serializable {

    private String id;

    private Channel type;

    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Channel getType() {
        return type;
    }

    public void setType(Channel type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DeliveryChannelDTO deliveryChannelDTO = (DeliveryChannelDTO) o;
        if (deliveryChannelDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), deliveryChannelDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DeliveryChannelDTO{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
