package com.multidata.notification.hub.repository;

import com.multidata.notification.hub.domain.EventDeliveryStatus;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the EventDeliveryStatus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EventDeliveryStatusRepository extends MongoRepository<EventDeliveryStatus, String> {

}
