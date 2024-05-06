package com.brunosimm.normalizing_jobs.application.exception;

import com.brunosimm.normalizing_jobs.application.dto.ErrorDTO;
import com.brunosimm.normalizing_jobs.business.exception.BaseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.MockitoAnnotations.openMocks;

class ApplicationExceptionHandlerTest {

    @Mock
    private WebRequest request;
    @InjectMocks
    private ApplicationExceptionHandler applicationExceptionHandler;

    @BeforeEach
    void setup() {
        openMocks(this);
    }

    @Test
    @DisplayName("ApplicationExceptionHandler BaseException should return correct status and body")
    void application_exception_handler_base_exception_should_return_correct_status() {
        // Arrange
        BaseException exception = new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, "exception");

        //Act
        ResponseEntity<ErrorDTO> responseEntity = applicationExceptionHandler.handleBaseException(exception);

        //Assert
        assertThat(HttpStatus.INTERNAL_SERVER_ERROR).isEqualTo(responseEntity.getStatusCode());
        assertThat(responseEntity.getBody())
                .isNotNull();
        assertThat(responseEntity.getBody().getMessage())
                .isNotNull()
                .isEqualTo("exception");
    }
}