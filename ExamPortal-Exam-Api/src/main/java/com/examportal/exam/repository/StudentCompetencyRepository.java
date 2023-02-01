package com.examportal.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examportal.exam.model.Experience;
import com.examportal.exam.model.StudentCompetency;

public interface StudentCompetencyRepository extends JpaRepository<StudentCompetency, Long>{

}
