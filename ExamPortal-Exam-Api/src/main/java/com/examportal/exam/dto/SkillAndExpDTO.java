package com.examportal.exam.dto;

import java.util.List;

public class SkillAndExpDTO {
	private long studentId;
	private List<Long> skillId;
	private List<Long> experienceId;
	
	public SkillAndExpDTO(long studentId, List<Long> skillId, List<Long> experienceId) {
		super();
		this.studentId = studentId;
		this.skillId = skillId;
		this.experienceId = experienceId;
	}
	public SkillAndExpDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getStudentId() {
		return studentId;
	}
	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}
	public List<Long> getSkillId() {
		return skillId;
	}
	public void setSkillId(List<Long> skillId) {
		this.skillId = skillId;
	}
	public List<Long> getExperienceId() {
		return experienceId;
	}
	public void setExperienceId(List<Long> experienceId) {
		this.experienceId = experienceId;
	}
	
	
	
}
