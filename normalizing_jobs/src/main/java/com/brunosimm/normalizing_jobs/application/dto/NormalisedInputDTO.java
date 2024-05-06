package com.brunosimm.normalizing_jobs.application.dto;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class NormalisedInputDTO {
    private String normalizedInput;
    private String input;

    public NormalisedInputDTO(String normalizedInput, String input) {
        this.normalizedInput = normalizedInput;
        this.input = input;
    }
}
