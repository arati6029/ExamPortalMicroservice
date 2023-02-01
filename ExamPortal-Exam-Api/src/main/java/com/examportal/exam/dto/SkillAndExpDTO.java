package com.examportal.exam.dto;

import java.util.List;

import com.examportal.exam.model.Experience;
import com.examportal.exam.model.Skill;

public class SkillAndExpDTO {
	private long studentId;
	private List<Skill> skillId;
	private List<Experience> experienceId;
	public SkillAndExpDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SkillAndExpDTO(long studentId, List<Skill> skillId, List<Experience> experienceId) {
		super();
		this.studentId = studentId;
		this.skillId = skillId;
		this.experienceId = experienceId;
	}
	public long getStudentId() {
		return studentId;
	}
	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}
	public List<Skill> getSkillId() {
		return skillId;
	}
	public void setSkillId(List<Skill> skillId) {
		this.skillId = skillId;
	}
	public List<Experience> getExperienceId() {
		return experienceId;
	}
	public void setExperienceId(List<Experience> experienceId) {
		this.experienceId = experienceId;
	}
	
	
	
}
