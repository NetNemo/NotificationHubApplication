package com.multidata.notification.hub.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A SystemEvent.
 */
@Document(collection = "system_event")
public class SystemEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("description")
    private String description;

    @DBRef
    @Field("event")
    private Set<Event> events = new HashSet<>();
    @DBRef
    @Field("userChannelConfiguration")
    @JsonIgnoreProperties("systemEvents")
    private UserChannelConfiguration userChannelConfiguration;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public SystemEvent description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public SystemEvent events(Set<Event> events) {
        this.events = events;
        return this;
    }

    public SystemEvent addEvent(Event event) {
        this.events.add(event);
        event.setSystemEvent(this);
        return this;
    }

    public SystemEvent removeEvent(Event event) {
        this.events.remove(event);
        event.setSystemEvent(null);
        return this;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    public UserChannelConfiguration getUserChannelConfiguration() {
        return userChannelConfiguration;
    }

    public SystemEvent userChannelConfiguration(UserChannelConfiguration userChannelConfiguration) {
        this.userChannelConfiguration = userChannelConfiguration;
        return this;
    }

    public void setUserChannelConfiguration(UserChannelConfiguration userChannelConfiguration) {
        this.userChannelConfiguration = userChannelConfiguration;
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
        SystemEvent systemEvent = (SystemEvent) o;
        if (systemEvent.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), systemEvent.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SystemEvent{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
