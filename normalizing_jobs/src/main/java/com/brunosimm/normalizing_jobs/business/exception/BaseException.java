package com.brunosimm.normalizing_jobs.business.exception;

import com.brunosimm.normalizing_jobs.application.dto.ErrorDTO;
import org.springframework.http.HttpStatus;

public class BaseException extends RuntimeException {
    public final ErrorDTO error;

    public BaseException(HttpStatus status, String message) {
        super(message);
        this.error = new ErrorDTO(status, message);
    }
}
