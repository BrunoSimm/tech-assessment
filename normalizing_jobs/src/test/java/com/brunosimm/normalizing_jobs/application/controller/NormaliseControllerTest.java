package com.brunosimm.normalizing_jobs.application.controller;

import com.brunosimm.normalizing_jobs.application.dto.NormalisedInputDTO;
import com.brunosimm.normalizing_jobs.application.dto.PaginatedResponseDTO;
import com.brunosimm.normalizing_jobs.business.service.NormaliseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = NormaliseController.class)
@RunWith(SpringRunner.class)
class NormaliseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NormaliseService normaliseService;

    @Test
    void normalisedJobs() throws Exception {
        // Arrange
        when(normaliseService.normalisedJobs(0, 10)).thenReturn(new PaginatedResponseDTO<>(0, 10, 0, 0, 0, null));

        // Act and Assert
        mockMvc.perform(
                MockMvcRequestBuilders.get("/normalise?page=0&size=10")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void normalise() throws Exception {
        // Arrange
        when(normaliseService.normalise("Java engineer"))
                .thenReturn(new NormalisedInputDTO("Software engineer", "Java engineer"));

        // Act and Assert
        mockMvc.perform(
                MockMvcRequestBuilders.post("/normalise?input=Java engineer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void normalise_shouldValidateThatTheInputIsPresent() throws Exception {
        // Arrange

        // Act and Assert
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/normalise")
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("utf-8")
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}