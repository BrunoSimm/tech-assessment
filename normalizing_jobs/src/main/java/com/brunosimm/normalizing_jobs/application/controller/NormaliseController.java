package com.brunosimm.normalizing_jobs.application.controller;

import com.brunosimm.normalizing_jobs.application.dto.JobDTO;
import com.brunosimm.normalizing_jobs.application.dto.NormalisedInputDTO;
import com.brunosimm.normalizing_jobs.application.dto.PaginatedResponseDTO;
import com.brunosimm.normalizing_jobs.business.service.NormaliseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/normalise")
@RequiredArgsConstructor
public class NormaliseController {
    private final NormaliseService normaliseService;

    @GetMapping
    public PaginatedResponseDTO<List<JobDTO>> normalisedJobs(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "10") int size) {
        return normaliseService.normalisedJobs(page, size);
    }

    @PostMapping
    public NormalisedInputDTO normalise(@RequestParam String input) {
        return normaliseService.normalise(input);
    }
}
