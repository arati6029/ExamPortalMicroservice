package com.examportal.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examportal.exam.model.Result;

public interface ResultRepository extends JpaRepository<Result, Long> {

}
