package com.multidata.notification.hub.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the UserChannelConfiguration entity.
 */
public class UserChannelConfigurationDTO implements Serializable {

    private String id;

    private String mail;

    private String slackToken1;

    private String slackToken2;

    private String slackToken3;

    private String eventDeliveryStatusId;

    private String deliveryChannelId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getSlackToken1() {
        return slackToken1;
    }

    public void setSlackToken1(String slackToken1) {
        this.slackToken1 = slackToken1;
    }

    public String getSlackToken2() {
        return slackToken2;
    }

    public void setSlackToken2(String slackToken2) {
        this.slackToken2 = slackToken2;
    }

    public String getSlackToken3() {
        return slackToken3;
    }

    public void setSlackToken3(String slackToken3) {
        this.slackToken3 = slackToken3;
    }

    public String getEventDeliveryStatusId() {
        return eventDeliveryStatusId;
    }

    public void setEventDeliveryStatusId(String eventDeliveryStatusId) {
        this.eventDeliveryStatusId = eventDeliveryStatusId;
    }

    public String getDeliveryChannelId() {
        return deliveryChannelId;
    }

    public void setDeliveryChannelId(String deliveryChannelId) {
        this.deliveryChannelId = deliveryChannelId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserChannelConfigurationDTO userChannelConfigurationDTO = (UserChannelConfigurationDTO) o;
        if (userChannelConfigurationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userChannelConfigurationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserChannelConfigurationDTO{" +
            "id=" + getId() +
            ", mail='" + getMail() + "'" +
            ", slackToken1='" + getSlackToken1() + "'" +
            ", slackToken2='" + getSlackToken2() + "'" +
            ", slackToken3='" + getSlackToken3() + "'" +
            ", eventDeliveryStatus=" + getEventDeliveryStatusId() +
            ", deliveryChannel=" + getDeliveryChannelId() +
            "}";
    }
}
