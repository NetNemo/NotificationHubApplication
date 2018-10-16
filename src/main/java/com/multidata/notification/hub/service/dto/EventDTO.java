package com.multidata.notification.hub.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Event entity.
 */
public class EventDTO implements Serializable {

    private String id;

    private String description;

    private String text;

    private String systemEventId;

    private String applicationEventId;

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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSystemEventId() {
        return systemEventId;
    }

    public void setSystemEventId(String systemEventId) {
        this.systemEventId = systemEventId;
    }

    public String getApplicationEventId() {
        return applicationEventId;
    }

    public void setApplicationEventId(String applicationEventId) {
        this.applicationEventId = applicationEventId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EventDTO eventDTO = (EventDTO) o;
        if (eventDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), eventDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EventDTO{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", text='" + getText() + "'" +
            ", systemEvent=" + getSystemEventId() +
            ", applicationEvent=" + getApplicationEventId() +
            "}";
    }
}
