package com.multidata.notification.hub.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the LdapUser entity.
 */
public class LdapUserDTO implements Serializable {

    private String id;

    private String userID;

    private String name;

    private String userChannelConfigurationId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

        LdapUserDTO ldapUserDTO = (LdapUserDTO) o;
        if (ldapUserDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ldapUserDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LdapUserDTO{" +
            "id=" + getId() +
            ", userID='" + getUserID() + "'" +
            ", name='" + getName() + "'" +
            ", userChannelConfiguration=" + getUserChannelConfigurationId() +
            "}";
    }
}
