package com.brunosimm.normalizing_jobs.application.dto;

import com.brunosimm.normalizing_jobs.infraestructure.entity.JobEntity;

import java.util.List;
import java.util.stream.Collectors;

public record JobDTO(String title) {

    public static List<JobDTO> fromEntities(List<JobEntity> jobs) {
        return jobs.stream()
                .map(job -> new JobDTO(job.getTitle()))
                .collect(Collectors.toList());
    }
}
