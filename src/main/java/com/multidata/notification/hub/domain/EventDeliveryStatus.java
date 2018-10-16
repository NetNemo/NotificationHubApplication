package com.multidata.notification.hub.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A EventDeliveryStatus.
 */
@Document(collection = "event_delivery_status")
public class EventDeliveryStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("date")
    private LocalDate date;

    @Field("status")
    private String status;

    @DBRef
    @Field("userChannelConfiguration")
    private Set<UserChannelConfiguration> userChannelConfigurations = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public EventDeliveryStatus date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public EventDeliveryStatus status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<UserChannelConfiguration> getUserChannelConfigurations() {
        return userChannelConfigurations;
    }

    public EventDeliveryStatus userChannelConfigurations(Set<UserChannelConfiguration> userChannelConfigurations) {
        this.userChannelConfigurations = userChannelConfigurations;
        return this;
    }

    public EventDeliveryStatus addUserChannelConfiguration(UserChannelConfiguration userChannelConfiguration) {
        this.userChannelConfigurations.add(userChannelConfiguration);
        userChannelConfiguration.setEventDeliveryStatus(this);
        return this;
    }

    public EventDeliveryStatus removeUserChannelConfiguration(UserChannelConfiguration userChannelConfiguration) {
        this.userChannelConfigurations.remove(userChannelConfiguration);
        userChannelConfiguration.setEventDeliveryStatus(null);
        return this;
    }

    public void setUserChannelConfigurations(Set<UserChannelConfiguration> userChannelConfigurations) {
        this.userChannelConfigurations = userChannelConfigurations;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EventDeliveryStatus eventDeliveryStatus = (EventDeliveryStatus) o;
        if (eventDeliveryStatus.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), eventDeliveryStatus.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EventDeliveryStatus{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
