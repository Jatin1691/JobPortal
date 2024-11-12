package com.jobportal.Service;

import com.jobportal.DTO.ProfileDto;
import com.jobportal.Exception.JobPortalException;
import com.jobportal.Model.Profile;
import com.jobportal.Repository.ProfileRepository;
import com.jobportal.Utility.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class profileServiceImp implements ProfileService{

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public Long createProfile(String email) throws JobPortalException {
        Profile profile = new Profile();
        profile.setId(Utilities.getNextseq("profiles"));
        profile.setEmail(email);
        profile.setCertifications(new ArrayList<>());
        profile.setSkills(new ArrayList<>());
        profile.setExperiences(new ArrayList<>());
        profileRepository.save(profile);
        return profile.getId();
    }

    @Override
    public ProfileDto getProfile(Long id) throws JobPortalException {
        return profileRepository.findById(id).orElseThrow(()->new JobPortalException("Profile Not Found")).toDto();
    }

    @Override
    public ProfileDto updateProfile(ProfileDto profileDto) throws JobPortalException {
         profileRepository.findById(profileDto.getId()).orElseThrow(()->new JobPortalException("Profile Not Found"));
         profileRepository.save(profileDto.toEntity());
         return profileDto;
    }
}
