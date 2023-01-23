package com.examportal.exam.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * Questions

ques_id(pk)(un)
ex_id(un)(fk)
ques_no
question
op_A
op_B
op_C
op_D
ans

 */

@ToString(exclude = {"exam", "dateStamp"})

@Entity
public class Question extends BaseEntity {
	
	public Question(int quesNo, String question, String optA, String optB, String optC, String optD, String answer) {
		super();
		this.quesNo = quesNo;
		this.question = question;
		this.optA = optA;
		this.optB = optB;
		this.optC = optC;
		this.optD = optD;
		this.answer = answer;
	}
	
	public Question(int quesNo, String question, String optA, String optB, String optC, String optD, String answer, Exam exam) {
		super();
		this.quesNo = quesNo;
		this.question = question;
		this.optA = optA;
		this.optB = optB;
		this.optC = optC;
		this.optD = optD;
		this.answer = answer;
		this.exam = exam;
	}

	public Question(Long id, int quesNo, String question, String optA, String optB, String optC, String optD,
			String answer) {
		super(id);
		this.quesNo = quesNo;
		this.question = question;
		this.optA = optA;
		this.optB = optB;
		this.optC = optC;
		this.optD = optD;
		this.answer = answer;
		
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public int getQuesNo() {
		return quesNo;
	}

	public void setQuesNo(int quesNo) {
		this.quesNo = quesNo;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getOptA() {
		return optA;
	}

	public void setOptA(String optA) {
		this.optA = optA;
	}

	public String getOptB() {
		return optB;
	}

	public void setOptB(String optB) {
		this.optB = optB;
	}

	public String getOptC() {
		return optC;
	}

	public void setOptC(String optC) {
		this.optC = optC;
	}

	public String getOptD() {
		return optD;
	}

	public void setOptD(String optD) {
		this.optD = optD;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public LocalDateTime getDateStamp() {
		return dateStamp;
	}

	public void setDateStamp(LocalDateTime dateStamp) {
		this.dateStamp = dateStamp;
	}

	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Question(Long id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ex_id", nullable = false)
	private Exam exam;
	
	@Column(nullable = false)
	private int quesNo;
	
	@Column(nullable = false, length = 400)
	private String question;
	
	@Column(nullable = false, length = 400)
	private String optA;
	
	@Column(nullable = false, length = 400)
	private String optB;
	
	@Column(nullable = false, length = 400)
	private String optC;
	
	@Column(nullable = false, length = 400)
	private String optD;
	
	@Column(nullable = false, length = 20)
	private String answer;
	
	@Column(nullable = false)
	@CreationTimestamp
	private LocalDateTime dateStamp;
	
}

