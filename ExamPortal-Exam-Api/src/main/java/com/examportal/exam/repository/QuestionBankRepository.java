package com.examportal.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examportal.exam.model.QuestionBank;

public interface QuestionBankRepository extends JpaRepository<QuestionBank, Long> {

}
