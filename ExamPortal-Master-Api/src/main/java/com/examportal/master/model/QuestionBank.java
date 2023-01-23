package com.examportal.master.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.print.attribute.standard.Severity;

public class QuestionBank extends BaseEntity{

	@Column(nullable = false, length = 20)
	@Enumerated(EnumType.STRING)
	private Severity severity;
	@OneToMany
	private Question question;
	@Column(nullable = false)
	private int marksPerQues;
	@Column(nullable = false)
	private int negMarksPerQues;
	
}
