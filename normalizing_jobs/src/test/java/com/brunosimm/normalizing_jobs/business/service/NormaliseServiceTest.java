package com.brunosimm.normalizing_jobs.business.service;

import com.brunosimm.normalizing_jobs.application.dto.JobDTO;
import com.brunosimm.normalizing_jobs.application.dto.NormalisedInputDTO;
import com.brunosimm.normalizing_jobs.application.dto.PaginatedResponseDTO;
import com.brunosimm.normalizing_jobs.business.exception.NormalizedJobNotFoundException;
import com.brunosimm.normalizing_jobs.infraestructure.entity.JobEntity;
import com.brunosimm.normalizing_jobs.infraestructure.repository.NormaliseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class NormaliseServiceTest {

    @Mock
    private NormaliseRepository normaliseRepository;

    @InjectMocks
    private NormaliseService normaliseService;

    private final List<JobEntity> jobEntities = Arrays.asList(
            new JobEntity("Software engineer"),
            new JobEntity("Accountant"),
            new JobEntity("Architect"),
            new JobEntity("Quantity surveyor")
    );

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void normalisedJobs() {
        // Arrange
        Page<JobEntity> page = new PageImpl<>(jobEntities);
        when(normaliseRepository.findAll(PageRequest.of(0, jobEntities.size()))).thenReturn(page);

        // Act
        PaginatedResponseDTO<List<JobDTO>> result = normaliseService.normalisedJobs(0, jobEntities.size());

        // Assert
        assertEquals(0, result.getPage());
        assertEquals(jobEntities.size(), result.getSize());
        assertEquals(jobEntities.size(), result.getCount());
        assertEquals(jobEntities.size(), result.getData().size());
        assertEquals(jobEntities.size(), result.getTotalCount());
    }

    @ParameterizedTest
    @MethodSource("expectedNormalizedResults")
    void normalise(String expectedJobTitle, String inputJobTitle) {
        // Arrange
        when(normaliseRepository.findAll()).thenReturn(jobEntities);
        // Act
        NormalisedInputDTO result = normaliseService.normalise(inputJobTitle);

        // Assert
        assertEquals(expectedJobTitle, result.getNormalizedInput());
        assertEquals(inputJobTitle, result.getInput());
    }

    @Test
    void normalise_whenInputDontMatchAnyNormalizedJobThenShouldThrowAnException() {
        // Arrange
        when(normaliseRepository.findAll()).thenReturn(jobEntities);
        // Act and Assert
        assertThrows(NormalizedJobNotFoundException.class, () -> normaliseService.normalise("Z"));
    }

    static Stream<Arguments> expectedNormalizedResults() {
        return Stream.of(
                // expected - input
                Arguments.of("Software engineer", "Java engineer"),
                Arguments.of("Software engineer", "C# engineer"),
                Arguments.of("Accountant", "Accountant"),
                Arguments.of("Accountant", "Chief Accountant"),
                Arguments.of("Software engineer", "swneer"),
                Arguments.of("Software engineer", "Aoxftware enygineerw"),
                Arguments.of("Accountant", "tant"),
                Arguments.of("Accountant", "Brcounhant"),
                Arguments.of("Architect", "Arch"),
                Arguments.of("Architect", "Architect"),
                Arguments.of("Architect", "Architectural"),
                Arguments.of("Architect", "Brtect")
        );
    }
}