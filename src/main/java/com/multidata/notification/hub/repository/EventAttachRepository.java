package com.multidata.notification.hub.repository;

import com.multidata.notification.hub.domain.EventAttach;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the EventAttach entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EventAttachRepository extends MongoRepository<EventAttach, String> {

}
