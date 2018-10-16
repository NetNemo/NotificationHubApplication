package com.multidata.notification.hub.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the EventDeliveryStatus entity.
 */
public class EventDeliveryStatusDTO implements Serializable {

    private String id;

    private LocalDate date;

    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EventDeliveryStatusDTO eventDeliveryStatusDTO = (EventDeliveryStatusDTO) o;
        if (eventDeliveryStatusDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), eventDeliveryStatusDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EventDeliveryStatusDTO{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
