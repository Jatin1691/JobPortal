package com.jobportal.Model;

import com.jobportal.DTO.Certification;
import com.jobportal.DTO.Experience;
import com.jobportal.DTO.ProfileDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Base64;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "profiles")
public class Profile {

    @Id
    private Long id;
    private String email;
    private String jobTitle;
    private String company;
    private String location;
    private String about;
    private byte[] picture;
    private List<String> skills;
    private List<Experience> experiences;
    private List<Certification> certifications;
    private List<Long> savedJobs;

    public ProfileDto toDto() {
        return new ProfileDto(this.id,this.email,this.jobTitle,
                this.company,this.location,this.about, this.picture!=null? Base64.getEncoder().encodeToString(this.picture):null, this.skills,this.experiences,this.certifications,this.savedJobs);
    }
}
