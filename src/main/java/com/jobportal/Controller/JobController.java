package com.jobportal.Controller;

import com.jobportal.DTO.*;
import com.jobportal.Exception.JobPortalException;
import com.jobportal.Service.JobService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("jobs")
@Validated
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping("/post")
    public ResponseEntity<JobDto> postJob(@RequestBody @Valid JobDto jobDto) throws JobPortalException {
        return new ResponseEntity<>(jobService.postJob(jobDto), HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<JobDto>> getAllJobs() throws JobPortalException {

        return new ResponseEntity<>(jobService.getAllJobs(), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<JobDto> getJob(@PathVariable Long id) throws JobPortalException {

        return new ResponseEntity<>(jobService.getJob(id), HttpStatus.OK);
    }
    @PostMapping("/apply/{id}")
    public ResponseEntity<ResponseDto> applyJob(@PathVariable Long id,@RequestBody ApplicantDto applicantDto) throws JobPortalException {
        jobService.applyJob(id,applicantDto);
        return new ResponseEntity<ResponseDto>(new ResponseDto("Applied Successfully"), HttpStatus.OK);
    }
}
