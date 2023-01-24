package com.examportal.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examportal.exam.model.Experience;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {

}
