package com.multidata.notification.hub.service.mapper;

import com.multidata.notification.hub.domain.*;
import com.multidata.notification.hub.service.dto.LdapUserDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity LdapUser and its DTO LdapUserDTO.
 */
@Mapper(componentModel = "spring", uses = {UserChannelConfigurationMapper.class})
public interface LdapUserMapper extends EntityMapper<LdapUserDTO, LdapUser> {

    @Mapping(source = "userChannelConfiguration.id", target = "userChannelConfigurationId")
    LdapUserDTO toDto(LdapUser ldapUser);

    @Mapping(source = "userChannelConfigurationId", target = "userChannelConfiguration")
    LdapUser toEntity(LdapUserDTO ldapUserDTO);

    default LdapUser fromId(String id) {
        if (id == null) {
            return null;
        }
        LdapUser ldapUser = new LdapUser();
        ldapUser.setId(id);
        return ldapUser;
    }
}
