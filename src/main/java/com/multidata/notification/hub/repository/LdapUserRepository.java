package com.multidata.notification.hub.repository;

import com.multidata.notification.hub.domain.LdapUser;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the LdapUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LdapUserRepository extends MongoRepository<LdapUser, String> {

}
