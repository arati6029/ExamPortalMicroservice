package com.examportal.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examportal.exam.model.StudentResponse;

public interface StudentResponseRepository extends JpaRepository<StudentResponse, Long> {

}
