package com.jobportal.Controller;

import com.jobportal.DTO.ProfileDto;
import com.jobportal.Exception.JobPortalException;
import com.jobportal.Service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Validated
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/get/{id}")
    public ResponseEntity<ProfileDto> getProfile(@PathVariable Long id) throws JobPortalException {

        return new ResponseEntity<>(profileService.getProfile(id), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<ProfileDto> updateProfile(@RequestBody ProfileDto profileDto) throws JobPortalException {

        return new ResponseEntity<>(profileService.updateProfile(profileDto), HttpStatus.OK);
    }
}
