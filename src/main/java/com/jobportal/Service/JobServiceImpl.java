package com.jobportal.Service;

import com.jobportal.DTO.ApplicantDto;
import com.jobportal.DTO.ApplicationStatus;
import com.jobportal.DTO.JobDto;
import com.jobportal.DTO.ProfileDto;
import com.jobportal.Exception.JobPortalException;
import com.jobportal.Model.Applicant;
import com.jobportal.Model.Job;
import com.jobportal.Repository.JobRepository;
import com.jobportal.Utility.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service("jobService")
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    @Override
    public JobDto postJob(JobDto jobDto) throws JobPortalException {
        jobDto.setId(Utilities.getNextseq("jobs"));
        jobDto.setPostTime(LocalDateTime.now());
        return jobRepository.save(jobDto.toEntity()).toDto();
    }

    @Override
    public List<JobDto> getAllJobs() {
        return jobRepository.findAll().stream().map((x)->x.toDto()).toList();
    }

    @Override
    public JobDto getJob(Long id) throws JobPortalException {
        return jobRepository.findById(id).orElseThrow(()->new JobPortalException("Job NOT FOUND")).toDto();
    }

    @Override
    public void applyJob(Long id, ApplicantDto applicantDto) throws JobPortalException {
        Job job=jobRepository.findById(id).orElseThrow(()->new JobPortalException("Job Not Found"));
        List<Applicant> applicants=job.getApplicants();
        if(applicants==null){
            applicants=new ArrayList<>();
        }
        if(applicants.stream().filter((x)->x.getApplicantID()==applicantDto.getApplicantID()).toList().size()>0){
            throw new JobPortalException("Already Applied for this Job");
        }
        applicantDto.setApplicationStatus(ApplicationStatus.APPLIED);
        applicants.add(applicantDto.toEntity());
        job.setApplicants(applicants);
        jobRepository.save(job);

    }
}
