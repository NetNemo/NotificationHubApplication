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
 * A Event.
 */
@Document(collection = "event")
public class Event implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("description")
    private String description;

    @Field("text")
    private String text;

    @DBRef
    @Field("eventAttach")
    private Set<EventAttach> eventAttaches = new HashSet<>();
    @DBRef
    @Field("systemEvent")
    @JsonIgnoreProperties("events")
    private SystemEvent systemEvent;

    @DBRef
    @Field("applicationEvent")
    @JsonIgnoreProperties("events")
    private ApplicationEvent applicationEvent;

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

    public Event description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getText() {
        return text;
    }

    public Event text(String text) {
        this.text = text;
        return this;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Set<EventAttach> getEventAttaches() {
        return eventAttaches;
    }

    public Event eventAttaches(Set<EventAttach> eventAttaches) {
        this.eventAttaches = eventAttaches;
        return this;
    }

    public Event addEventAttach(EventAttach eventAttach) {
        this.eventAttaches.add(eventAttach);
        eventAttach.setEvent(this);
        return this;
    }

    public Event removeEventAttach(EventAttach eventAttach) {
        this.eventAttaches.remove(eventAttach);
        eventAttach.setEvent(null);
        return this;
    }

    public void setEventAttaches(Set<EventAttach> eventAttaches) {
        this.eventAttaches = eventAttaches;
    }

    public SystemEvent getSystemEvent() {
        return systemEvent;
    }

    public Event systemEvent(SystemEvent systemEvent) {
        this.systemEvent = systemEvent;
        return this;
    }

    public void setSystemEvent(SystemEvent systemEvent) {
        this.systemEvent = systemEvent;
    }

    public ApplicationEvent getApplicationEvent() {
        return applicationEvent;
    }

    public Event applicationEvent(ApplicationEvent applicationEvent) {
        this.applicationEvent = applicationEvent;
        return this;
    }

    public void setApplicationEvent(ApplicationEvent applicationEvent) {
        this.applicationEvent = applicationEvent;
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
        Event event = (Event) o;
        if (event.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), event.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Event{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", text='" + getText() + "'" +
            "}";
    }
}
