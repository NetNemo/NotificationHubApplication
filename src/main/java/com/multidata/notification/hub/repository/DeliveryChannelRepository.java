package com.multidata.notification.hub.repository;

import com.multidata.notification.hub.domain.DeliveryChannel;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the DeliveryChannel entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DeliveryChannelRepository extends MongoRepository<DeliveryChannel, String> {

}
