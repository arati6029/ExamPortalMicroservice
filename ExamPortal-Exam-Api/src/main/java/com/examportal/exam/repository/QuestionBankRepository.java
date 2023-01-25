package com.examportal.exam.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.examportal.exam.model.QuestionBank;
import com.examportal.exam.model.Skill;

public interface QuestionBankRepository extends JpaRepository<QuestionBank, Long> {
@Query("select q from QuestionBank q where q.skill=?1 and q.severity=?2")
	Optional<QuestionBank> findBySkillIdAndSeverity(Skill skill, String severity);

}
