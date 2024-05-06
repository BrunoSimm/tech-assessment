package com.brunosimm.normalizing_jobs.infraestructure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity(name = "normalized_jobs")
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class JobEntity {
    @Id
    String title;
}
