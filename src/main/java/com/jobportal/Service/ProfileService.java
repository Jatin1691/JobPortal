package com.jobportal.Service;

import com.jobportal.DTO.ProfileDto;
import com.jobportal.Exception.JobPortalException;
import com.jobportal.Model.Profile;

public interface ProfileService {

    public Long createProfile(String email) throws JobPortalException;

    public ProfileDto getProfile(Long id) throws JobPortalException;

    public ProfileDto updateProfile(ProfileDto profileDto) throws JobPortalException;
}
