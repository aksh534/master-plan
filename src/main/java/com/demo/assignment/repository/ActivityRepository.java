package com.demo.assignment.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.demo.assignment.model.Activity;

@Repository
public interface ActivityRepository extends MongoRepository<Activity, String> {
	Activity findBySerialNo(String serialNumber);
}
