package com.brunosimm.normalizing_jobs.business.exception;

import org.springframework.http.HttpStatus;

public class NormalizedJobNotFoundException extends BaseException {
    public NormalizedJobNotFoundException(HttpStatus status, String message) {
        super(status, message);
    }
}
