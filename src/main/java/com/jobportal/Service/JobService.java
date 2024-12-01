package com.jobportal.Service;

import com.jobportal.DTO.ApplicantDto;
import com.jobportal.DTO.JobDto;
import com.jobportal.DTO.ProfileDto;
import com.jobportal.Exception.JobPortalException;
import jakarta.validation.Valid;

import java.util.List;

public interface JobService {

   public JobDto postJob(@Valid JobDto jobDto) throws JobPortalException;

   public List<JobDto> getAllJobs();

   public JobDto getJob(Long id) throws JobPortalException;

  public void applyJob(Long id, ApplicantDto applicantDto) throws JobPortalException;
}
