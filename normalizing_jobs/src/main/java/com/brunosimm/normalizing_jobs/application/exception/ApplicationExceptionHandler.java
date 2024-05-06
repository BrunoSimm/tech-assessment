package com.brunosimm.normalizing_jobs.application.exception;

import com.brunosimm.normalizing_jobs.application.dto.ErrorDTO;
import com.brunosimm.normalizing_jobs.business.exception.BaseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorDTO> handleBaseException(BaseException exception) {
        return ResponseEntity
                .status(exception.error.getCode())
                .body(exception.error);
    }

}
