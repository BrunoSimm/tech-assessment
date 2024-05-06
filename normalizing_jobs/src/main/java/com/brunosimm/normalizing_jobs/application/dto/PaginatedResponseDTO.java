package com.brunosimm.normalizing_jobs.application.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PaginatedResponseDTO<T> {
    private final int page;
    private final int size;
    private final int count;
    private final int totalPages;
    private final long totalCount;
    private final T data;

    public PaginatedResponseDTO(int page, int size, int count, int totalPages, long totalCount, T data) {
        this.page = page;
        this.size = size;
        this.count = count;
        this.totalPages = totalPages;
        this.totalCount = totalCount;
        this.data = data;
    }
}
