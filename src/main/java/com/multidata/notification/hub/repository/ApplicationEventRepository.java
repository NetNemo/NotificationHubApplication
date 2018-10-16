package com.multidata.notification.hub.repository;

import com.multidata.notification.hub.domain.ApplicationEvent;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the ApplicationEvent entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ApplicationEventRepository extends MongoRepository<ApplicationEvent, String> {

}
