package com.brunosimm.normalizing_jobs.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ErrorDTO {
    private HttpStatus code;
    private String message;
}
