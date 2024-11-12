package com.jobportal.Repository;

import com.jobportal.Model.OTP;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OtpRepository extends MongoRepository<OTP, String> {
   List<OTP> findByCreationTimeBefore(LocalDateTime expiry);
}
