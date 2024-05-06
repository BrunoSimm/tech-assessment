package com.brunosimm.normalizing_jobs.business.service;

import com.brunosimm.normalizing_jobs.application.dto.JobDTO;
import com.brunosimm.normalizing_jobs.application.dto.NormalisedInputDTO;
import com.brunosimm.normalizing_jobs.application.dto.PaginatedResponseDTO;
import com.brunosimm.normalizing_jobs.business.exception.NormalizedJobNotFoundException;
import com.brunosimm.normalizing_jobs.infraestructure.entity.JobEntity;
import com.brunosimm.normalizing_jobs.infraestructure.repository.NormaliseRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NormaliseService {

    private final NormaliseRepository normaliseRepository;

    public PaginatedResponseDTO<List<JobDTO>> normalisedJobs(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<JobEntity> response = normaliseRepository.findAll(pageable);
        return PaginatedResponseDTO.<List<JobDTO>>builder()
                .page(page)
                .size(size)
                .count(response.getNumberOfElements())
                .totalPages(response.getTotalPages())
                .totalCount(response.getTotalElements())
                .data(JobDTO.fromEntities(response.getContent()))
                .build();
    }

    public NormalisedInputDTO normalise(String input) {
        List<JobEntity> normalizedJobs = normaliseRepository.findAll();
        String normalisedInput = null;
        double maxSimilarityScore = 1.0;
        for (JobEntity normalizedJob : normalizedJobs) {
            double score = this.getTitleSimilarity(input.toLowerCase(), normalizedJob.getTitle().toLowerCase());
            if (score < maxSimilarityScore) {
                maxSimilarityScore = score;
                normalisedInput = normalizedJob.getTitle();
                if (score == 0.0) break;
            }
        }
        if (normalisedInput == null) {
            throw new NormalizedJobNotFoundException(HttpStatus.NOT_FOUND, "Not found any normalized job that matches the desired input");
        }
        return new NormalisedInputDTO(normalisedInput, input);
    }

    private double getTitleSimilarity(String input, String normalizedJobToCompare) {
        int distance = StringUtils.getLevenshteinDistance(input, normalizedJobToCompare);
        int maxLength = Math.max(input.length(), normalizedJobToCompare.length());
        return (double) distance / maxLength; // between 0 and 1
    }
}
