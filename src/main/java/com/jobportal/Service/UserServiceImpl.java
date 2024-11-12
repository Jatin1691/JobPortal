package com.jobportal.Service;

import com.jobportal.DTO.LoginDto;
import com.jobportal.DTO.ResponseDto;
import com.jobportal.DTO.UserDto;
import com.jobportal.Exception.JobPortalException;
import com.jobportal.Model.OTP;
import com.jobportal.Model.User;
import com.jobportal.Repository.OtpRepository;
import com.jobportal.Repository.UserRepository;
import com.jobportal.Utility.Data;
import com.jobportal.Utility.Utilities;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private OtpRepository otpRepository;

    @Override
    public UserDto registerUser(UserDto userDto) throws JobPortalException {
        Optional<User> optional=userRepository.findByEmail(userDto.getEmail());
        if(optional.isPresent()) {
            throw new JobPortalException("Email already Exist");
        }
        userDto.setProfileId(profileService.createProfile(userDto.getEmail()));
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user=userDto.toEntity();
        userRepository.save(user);
        return user.toDto();
    }

    @Override
    public UserDto loginUser(LoginDto loginDto) throws JobPortalException {
        User user=userRepository.findByEmail(loginDto.getEmail()).orElseThrow(()->new JobPortalException("User is Not Registered"));
        if(!passwordEncoder.matches(loginDto.getPassword(),user.getPassword())) {
            throw new JobPortalException("Wrong Password");
        }

        return user.toDto();

    }

    @Override
    public Boolean sendOtp(String email) throws Exception {
        User user=userRepository.findByEmail(email).orElseThrow(()->new JobPortalException("User is Not Registered"));
        MimeMessage mm=mailSender.createMimeMessage();
        MimeMessageHelper message=new MimeMessageHelper(mm,true);
        message.setTo(email);
        message.setSubject("Your OTP Code");
        String genOtp= Utilities.generateOtp();
        OTP otp=new OTP(email,genOtp, LocalDateTime.now());
        otpRepository.save(otp);
        message.setText(Data.emailSender(genOtp),true);
        mailSender.send(mm);
        return true;
    }

    @Override
    public Boolean verifyOtp(String email, String otp) throws JobPortalException {
     OTP otpEntity= otpRepository.findById(email).orElseThrow(()->new JobPortalException("OTP not found"));
        if(!otpEntity.getOtpCode().equals(otp)) {
            throw new JobPortalException("Wrong OTP Code");
        }
        return true;
    }

    @Override
    public ResponseDto changePassword(LoginDto loginDto) throws JobPortalException {
        User user=userRepository.findByEmail(loginDto.getEmail()).orElseThrow(()->new JobPortalException("User not found"));
        user.setPassword(passwordEncoder.encode(loginDto.getPassword()));
        userRepository.save(user);
        return new ResponseDto("Successfully Changed Password");
    }

    @Scheduled(fixedRate = 60000)
    public void RemoveExpiryOtps(){
        LocalDateTime expiry=LocalDateTime.now().minusMinutes(5);
        List<OTP> expiryotp=otpRepository.findByCreationTimeBefore(expiry);
        if(!expiryotp.isEmpty()){
            otpRepository.deleteAll();
            System.out.println("Removed Expired OTPs "+expiryotp.size());
        }
    }

}
