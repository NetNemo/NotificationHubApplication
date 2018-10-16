package com.multidata.notification.hub.service.impl;

import com.multidata.notification.hub.service.LdapUserService;
import com.multidata.notification.hub.domain.LdapUser;
import com.multidata.notification.hub.repository.LdapUserRepository;
import com.multidata.notification.hub.service.dto.LdapUserDTO;
import com.multidata.notification.hub.service.mapper.LdapUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing LdapUser.
 */
@Service
public class LdapUserServiceImpl implements LdapUserService {

    private final Logger log = LoggerFactory.getLogger(LdapUserServiceImpl.class);

    private final LdapUserRepository ldapUserRepository;

    private final LdapUserMapper ldapUserMapper;

    public LdapUserServiceImpl(LdapUserRepository ldapUserRepository, LdapUserMapper ldapUserMapper) {
        this.ldapUserRepository = ldapUserRepository;
        this.ldapUserMapper = ldapUserMapper;
    }

    /**
     * Save a ldapUser.
     *
     * @param ldapUserDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public LdapUserDTO save(LdapUserDTO ldapUserDTO) {
        log.debug("Request to save LdapUser : {}", ldapUserDTO);

        LdapUser ldapUser = ldapUserMapper.toEntity(ldapUserDTO);
        ldapUser = ldapUserRepository.save(ldapUser);
        return ldapUserMapper.toDto(ldapUser);
    }

    /**
     * Get all the ldapUsers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    public Page<LdapUserDTO> findAll(Pageable pageable) {
        log.debug("Request to get all LdapUsers");
        return ldapUserRepository.findAll(pageable)
            .map(ldapUserMapper::toDto);
    }


    /**
     * Get one ldapUser by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Optional<LdapUserDTO> findOne(String id) {
        log.debug("Request to get LdapUser : {}", id);
        return ldapUserRepository.findById(id)
            .map(ldapUserMapper::toDto);
    }

    /**
     * Delete the ldapUser by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete LdapUser : {}", id);
        ldapUserRepository.deleteById(id);
    }
}
