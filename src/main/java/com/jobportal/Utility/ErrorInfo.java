package com.jobportal.Utility;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorInfo {

    private String message;
    private HttpStatus errorCode;
    private LocalDateTime timestamp;
}
