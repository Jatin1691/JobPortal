package com.jobportal.JWT;

import com.jobportal.DTO.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerUserDetails implements UserDetails {

    private Long id;
    private String username;
    private String name;
    private String password;
    private Long ProfileId;
    private AccountType accountType;
    private Collection<? extends GrantedAuthority> authorities;

}
