package com.jobportal.Repository;

import com.jobportal.Model.Job;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JobRepository extends MongoRepository<Job,Long> {
}
