package com.jobportal.Model;

import com.jobportal.DTO.ApplicantDto;
import com.jobportal.DTO.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Base64;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
public class Applicant {

    private Long ApplicantID;
    private String name;
    private String email;
    private String phone;
    private String website;
    private byte[] resume;
    private String coverLetter;
    private LocalDateTime timeStamp;
    private ApplicationStatus applicationStatus;

    public ApplicantDto toDto() {
        return new ApplicantDto(this.ApplicantID, this.name, this.email, this.phone, this.website,
                this.resume!=null? Base64.getEncoder().encodeToString(this.resume):null, this.coverLetter, this.timeStamp, this.applicationStatus
        );
    }
}
