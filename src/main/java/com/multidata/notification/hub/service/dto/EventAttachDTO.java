package com.multidata.notification.hub.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the EventAttach entity.
 */
public class EventAttachDTO implements Serializable {

    private String id;

    private byte[] file;
    private String fileContentType;

    private String eventId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EventAttachDTO eventAttachDTO = (EventAttachDTO) o;
        if (eventAttachDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), eventAttachDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EventAttachDTO{" +
            "id=" + getId() +
            ", file='" + getFile() + "'" +
            ", event=" + getEventId() +
            "}";
    }
}
