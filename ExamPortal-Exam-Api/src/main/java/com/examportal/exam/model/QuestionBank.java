package com.examportal.exam.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import com.examportal.exam.model.Severity;

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
@Table(name="question_bank")
public class QuestionBank extends BaseEntity {
	
	
	
	
	@Column(nullable = false, length = 400,name="question")
	private String question;
	
	@Column(nullable = false, length = 400,name="optA")
	private String optA;
	
	@Column(nullable = false, length = 400,name="optB")
	private String optB;
	
	@Column(nullable = false, length = 400,name="optC")
	private String optC;
	
	@Column(nullable = false, length = 400,name="optD")
	private String optD;

	@Column(name="expected_ans")
	 @ElementCollection(targetClass=String.class)
    private List<String> expectedAnswers;
	
	@Column(nullable = false,name="marks_per_ques")
	private int marksPerQues;
	
	@Column(nullable = false,name="neg_marks_per_ques")
	private int negMarksPerQues;
	
	//To upload image  against question if required
	@Lob
	@Column(name = "image_data")
	private byte[] imageData;
	@Column(nullable = false,name="date_stamp")
	@CreationTimestamp
	private LocalDateTime dateStamp;
	@Column(name="severity")
	@Enumerated(EnumType.STRING)
	private Severity severity;
	
	
//	@Column(name="skill_id",nullable = false)
	@ManyToOne
	private Skill skill;
	@Column(name="file_name")
	private String fileName;
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
	
	public List<String> getExpectedAnswers() {
		return expectedAnswers;
	}
	public void setExpectedAnswers(List<String> expectedAnswers) {
		this.expectedAnswers = expectedAnswers;
	}

	public int getMarksPerQues() {
		return marksPerQues;
	}
	public void setMarksPerQues(int marksPerQues) {
		this.marksPerQues = marksPerQues;
	}
	public int getNegMarksPerQues() {
		return negMarksPerQues;
	}
	public void setNegMarksPerQues(int negMarksPerQues) {
		this.negMarksPerQues = negMarksPerQues;
	}
	public byte[] getImageData() {
		return imageData;
	}
	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}
	public LocalDateTime getDateStamp() {
		return dateStamp;
	}
	public void setDateStamp(LocalDateTime dateStamp) {
		this.dateStamp = dateStamp;
	}

	public Severity getSeverity() {
		return severity;
	}
	public void setSeverity(Severity severity) {
		this.severity = severity;
	}
	public Skill getSkill() {
		return skill;
	}
	public void setSkill(Skill skill) {
		this.skill = skill;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public QuestionBank() {
		super();
		// TODO Auto-generated constructor stub
	}
	public QuestionBank(Long id) {
		super(id);
		// TODO Auto-generated constructor stub
	}
	public QuestionBank(String question, String optA, String optB, String optC, String optD, List<String> expectedAnswers,
			int marksPerQues, int negMarksPerQues, byte[] imageData, LocalDateTime dateStamp, Severity severity,
			Skill skill) {
		super();
		this.question = question;
		this.optA = optA;
		this.optB = optB;
		this.optC = optC;
		this.optD = optD;
		this.expectedAnswers = expectedAnswers;
		this.marksPerQues = marksPerQues;
		this.negMarksPerQues = negMarksPerQues;
		this.imageData = imageData;
		this.dateStamp = dateStamp;
		this.severity = severity;
		this.skill = skill;
		
	}
	public QuestionBank(String question, String optA, String optB, String optC, String optD,
			List<String> expectedAnswers, int marksPerQues, int negMarksPerQues, Severity severity
			) {
		
		
			this.question = question;
			this.optA = optA;
			this.optB = optB;
			this.optC = optC;
			this.optD = optD;
			this.expectedAnswers = expectedAnswers;
			this.marksPerQues = marksPerQues;
			this.negMarksPerQues = negMarksPerQues;
			
			
			this.severity = severity;
			
	}
	public QuestionBank(String question, String optA, String optB, String optC, String optD,
			List<String> expectedAnswers, int marksPerQues, int negMarksPerQues, Severity severity, Skill skill) {
		this.question = question;
		this.optA = optA;
		this.optB = optB;
		this.optC = optC;
		this.optD = optD;
		this.expectedAnswers = expectedAnswers;
		this.marksPerQues = marksPerQues;
		this.negMarksPerQues = negMarksPerQues;
		
		
		this.severity = severity;
	}
	
	

	
	
}

