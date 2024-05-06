package com.brunosimm.normalizing_jobs.infraestructure.repository;

import com.brunosimm.normalizing_jobs.infraestructure.entity.JobEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@RunWith(SpringRunner.class)
class NormaliseRepositoryTest {

    @Autowired
    private NormaliseRepository normaliseRepository;

    @BeforeEach
    public void setUp() {
    }

    @Test
    void databaseShouldStartWithDefaultJobs() {
        // Arrange
        // Act
        List<JobEntity> jobs = normaliseRepository.findAll();
        // Assert
        assertThat(jobs)
                .isNotEmpty()
                .hasSize(4)
                .usingRecursiveComparison()
                .ignoringCollectionOrder()
                .isEqualTo(List.of(
                        new JobEntity("Architect"),
                        new JobEntity("Software engineer"),
                        new JobEntity("Quantity surveyor"),
                        new JobEntity("Accountant")
                ));
    }
}