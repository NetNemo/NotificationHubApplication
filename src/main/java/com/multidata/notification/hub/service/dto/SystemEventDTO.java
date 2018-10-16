package com.multidata.notification.hub.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the SystemEvent entity.
 */
public class SystemEventDTO implements Serializable {

    private String id;

    private String description;

    private String userChannelConfigurationId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserChannelConfigurationId() {
        return userChannelConfigurationId;
    }

    public void setUserChannelConfigurationId(String userChannelConfigurationId) {
        this.userChannelConfigurationId = userChannelConfigurationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SystemEventDTO systemEventDTO = (SystemEventDTO) o;
        if (systemEventDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), systemEventDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SystemEventDTO{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", userChannelConfiguration=" + getUserChannelConfigurationId() +
            "}";
    }
}
