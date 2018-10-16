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
 * A UserChannelConfiguration.
 */
@Document(collection = "user_channel_configuration")
public class UserChannelConfiguration implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("mail")
    private String mail;

    @Field("slack_token_1")
    private String slackToken1;

    @Field("slack_token_2")
    private String slackToken2;

    @Field("slack_token_3")
    private String slackToken3;

    @DBRef
    @Field("applicationEvent")
    private Set<ApplicationEvent> applicationEvents = new HashSet<>();
    @DBRef
    @Field("systemEvent")
    private Set<SystemEvent> systemEvents = new HashSet<>();
    @DBRef
    @Field("userID")
    private Set<LdapUser> userIDS = new HashSet<>();
    @DBRef
    @Field("eventDeliveryStatus")
    @JsonIgnoreProperties("userChannelConfigurations")
    private EventDeliveryStatus eventDeliveryStatus;

    @DBRef
    @Field("deliveryChannel")
    @JsonIgnoreProperties("userChannelConfigurations")
    private DeliveryChannel deliveryChannel;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public UserChannelConfiguration mail(String mail) {
        this.mail = mail;
        return this;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getSlackToken1() {
        return slackToken1;
    }

    public UserChannelConfiguration slackToken1(String slackToken1) {
        this.slackToken1 = slackToken1;
        return this;
    }

    public void setSlackToken1(String slackToken1) {
        this.slackToken1 = slackToken1;
    }

    public String getSlackToken2() {
        return slackToken2;
    }

    public UserChannelConfiguration slackToken2(String slackToken2) {
        this.slackToken2 = slackToken2;
        return this;
    }

    public void setSlackToken2(String slackToken2) {
        this.slackToken2 = slackToken2;
    }

    public String getSlackToken3() {
        return slackToken3;
    }

    public UserChannelConfiguration slackToken3(String slackToken3) {
        this.slackToken3 = slackToken3;
        return this;
    }

    public void setSlackToken3(String slackToken3) {
        this.slackToken3 = slackToken3;
    }

    public Set<ApplicationEvent> getApplicationEvents() {
        return applicationEvents;
    }

    public UserChannelConfiguration applicationEvents(Set<ApplicationEvent> applicationEvents) {
        this.applicationEvents = applicationEvents;
        return this;
    }

    public UserChannelConfiguration addApplicationEvent(ApplicationEvent applicationEvent) {
        this.applicationEvents.add(applicationEvent);
        applicationEvent.setUserChannelConfiguration(this);
        return this;
    }

    public UserChannelConfiguration removeApplicationEvent(ApplicationEvent applicationEvent) {
        this.applicationEvents.remove(applicationEvent);
        applicationEvent.setUserChannelConfiguration(null);
        return this;
    }

    public void setApplicationEvents(Set<ApplicationEvent> applicationEvents) {
        this.applicationEvents = applicationEvents;
    }

    public Set<SystemEvent> getSystemEvents() {
        return systemEvents;
    }

    public UserChannelConfiguration systemEvents(Set<SystemEvent> systemEvents) {
        this.systemEvents = systemEvents;
        return this;
    }

    public UserChannelConfiguration addSystemEvent(SystemEvent systemEvent) {
        this.systemEvents.add(systemEvent);
        systemEvent.setUserChannelConfiguration(this);
        return this;
    }

    public UserChannelConfiguration removeSystemEvent(SystemEvent systemEvent) {
        this.systemEvents.remove(systemEvent);
        systemEvent.setUserChannelConfiguration(null);
        return this;
    }

    public void setSystemEvents(Set<SystemEvent> systemEvents) {
        this.systemEvents = systemEvents;
    }

    public Set<LdapUser> getUserIDS() {
        return userIDS;
    }

    public UserChannelConfiguration userIDS(Set<LdapUser> ldapUsers) {
        this.userIDS = ldapUsers;
        return this;
    }

    public UserChannelConfiguration addUserID(LdapUser ldapUser) {
        this.userIDS.add(ldapUser);
        ldapUser.setUserChannelConfiguration(this);
        return this;
    }

    public UserChannelConfiguration removeUserID(LdapUser ldapUser) {
        this.userIDS.remove(ldapUser);
        ldapUser.setUserChannelConfiguration(null);
        return this;
    }

    public void setUserIDS(Set<LdapUser> ldapUsers) {
        this.userIDS = ldapUsers;
    }

    public EventDeliveryStatus getEventDeliveryStatus() {
        return eventDeliveryStatus;
    }

    public UserChannelConfiguration eventDeliveryStatus(EventDeliveryStatus eventDeliveryStatus) {
        this.eventDeliveryStatus = eventDeliveryStatus;
        return this;
    }

    public void setEventDeliveryStatus(EventDeliveryStatus eventDeliveryStatus) {
        this.eventDeliveryStatus = eventDeliveryStatus;
    }

    public DeliveryChannel getDeliveryChannel() {
        return deliveryChannel;
    }

    public UserChannelConfiguration deliveryChannel(DeliveryChannel deliveryChannel) {
        this.deliveryChannel = deliveryChannel;
        return this;
    }

    public void setDeliveryChannel(DeliveryChannel deliveryChannel) {
        this.deliveryChannel = deliveryChannel;
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
        UserChannelConfiguration userChannelConfiguration = (UserChannelConfiguration) o;
        if (userChannelConfiguration.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userChannelConfiguration.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserChannelConfiguration{" +
            "id=" + getId() +
            ", mail='" + getMail() + "'" +
            ", slackToken1='" + getSlackToken1() + "'" +
            ", slackToken2='" + getSlackToken2() + "'" +
            ", slackToken3='" + getSlackToken3() + "'" +
            "}";
    }
}
