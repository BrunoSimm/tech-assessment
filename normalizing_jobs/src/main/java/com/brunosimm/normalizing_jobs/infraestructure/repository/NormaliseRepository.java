package com.brunosimm.normalizing_jobs.infraestructure.repository;


import com.brunosimm.normalizing_jobs.infraestructure.entity.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NormaliseRepository extends JpaRepository<JobEntity, String> {
}
