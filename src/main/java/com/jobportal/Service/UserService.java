package com.jobportal.Service;

import com.jobportal.DTO.LoginDto;
import com.jobportal.DTO.ResponseDto;
import com.jobportal.DTO.UserDto;
import com.jobportal.Exception.JobPortalException;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;

public interface UserService {

    public UserDto registerUser(UserDto userDto) throws JobPortalException;

    public UserDto getUserByEmail(String  email) throws JobPortalException;

   public UserDto loginUser( LoginDto loginDto) throws JobPortalException;

   public Boolean sendOtp(String email) throws Exception;

   public Boolean verifyOtp(String email, String otp) throws JobPortalException;

  public   ResponseDto changePassword(LoginDto loginDto) throws JobPortalException;
}
