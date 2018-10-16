package com.multidata.notification.hub.repository;

import com.multidata.notification.hub.domain.SystemEvent;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the SystemEvent entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SystemEventRepository extends MongoRepository<SystemEvent, String> {

}
