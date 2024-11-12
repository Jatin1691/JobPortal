package com.jobportal.Controller;

import com.jobportal.DTO.LoginDto;
import com.jobportal.DTO.ResponseDto;
import com.jobportal.DTO.UserDto;
import com.jobportal.Exception.JobPortalException;
import com.jobportal.Service.UserService;
import com.jobportal.Service.UserServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Validated
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody @Valid UserDto userDto) throws JobPortalException {
        UserDto userDto1=userService.registerUser(userDto);
        return new ResponseEntity<>(userDto1, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> registerUser(@RequestBody @Valid LoginDto loginDto) throws JobPortalException {
        UserDto userDto=userService.loginUser(loginDto);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping("/sendOtp/{email}")
    public ResponseEntity<ResponseDto> sendOtp(@PathVariable @Email(message = "Email is invalid") String email) throws Exception {
        userService.sendOtp(email);
        return new ResponseEntity<>(new ResponseDto("Otp Sent Successfully"),HttpStatus.OK);
    }

    @GetMapping("/verifyOtp/{email}/{otp}")
    public ResponseEntity<ResponseDto> sendOtp(@PathVariable @Email(message = "Email is Invalid") String email,@PathVariable @Pattern(regexp = "^[0-9]{6}$", message = "Otp is invalid") String otp) throws JobPortalException {
        userService.verifyOtp(email,otp);
        return new ResponseEntity<>(new ResponseDto("Otp Verified Successfully"),HttpStatus.OK);
    }

    @PostMapping("/changePassword")
    public ResponseEntity<ResponseDto> changePassword(@RequestBody  LoginDto loginDto) throws JobPortalException {

        return new ResponseEntity<>(userService.changePassword(loginDto), HttpStatus.OK);
    }


}
