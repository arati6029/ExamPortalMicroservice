package com.examportal.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examportal.exam.model.Skill;

public interface SkillRepository extends JpaRepository<Skill, Long> {

}
