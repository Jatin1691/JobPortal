package com.jobportal.JWT;

import com.jobportal.DTO.UserDto;
import com.jobportal.Exception.JobPortalException;
import com.jobportal.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            UserDto dto=userService.getUserByEmail(username);
            return new CustomerUserDetails(dto.getId(),username,dto.getName(),dto.getPassword(),dto.getProfileId(),dto.getAccountType(),new ArrayList<>());
        } catch (JobPortalException e) {
            e.printStackTrace();
        }
        return null;
    }
}
