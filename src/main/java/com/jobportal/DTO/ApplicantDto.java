package com.jobportal.DTO;

import com.jobportal.Model.Applicant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Base64;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApplicantDto {

    private Long ApplicantID;
    private String name;
    private String email;
    private String phone;
    private String website;
    private String resume;
    private String coverLetter;
    private LocalDateTime timeStamp;
    private ApplicationStatus applicationStatus;

    public Applicant toEntity() {
        return new Applicant(this.ApplicantID,this.name,this.email,this.phone,this.website,
                this.resume!=null? Base64.getDecoder().decode(this.resume):null,this.coverLetter,this.timeStamp,this.applicationStatus
        );
    }
}
