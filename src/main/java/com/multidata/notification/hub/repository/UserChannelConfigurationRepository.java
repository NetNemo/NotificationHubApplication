package com.multidata.notification.hub.repository;

import com.multidata.notification.hub.domain.UserChannelConfiguration;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the UserChannelConfiguration entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserChannelConfigurationRepository extends MongoRepository<UserChannelConfiguration, String> {

}
