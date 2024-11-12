package com.jobportal.Model;

import com.jobportal.DTO.AccountType;
import com.jobportal.DTO.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.annotation.Collation;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class User {

    @Id
    private String id;
    private String name;

    @Indexed(unique = true)
    private String email;
    private String password;

    private AccountType accountType;
    private Long profileId;

    public UserDto toDto(){
        return new UserDto(this.id,this.name,this.email,this.password,this.accountType,this.profileId);
    }
}
