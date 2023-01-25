package com.examportal.exam.dto;

import java.time.LocalDateTime;
import java.util.List;

//import javax.persistence.Column;
//import javax.persistence.ElementCollection;
//import javax.persistence.EnumType;
//import javax.persistence.Enumerated;
//import javax.persistence.Lob;
//import javax.persistence.OneToOne;
//
//import org.hibernate.annotations.CreationTimestamp;

import com.examportal.exam.model.Severity;
import com.examportal.exam.model.Skill;

public class QuestionDTO {
	
	private String question;
	
	private String optA;
	
	private String optB;
	
	private String optC;
	
	private String optD;

    private List<String> expectedAnswers;
	
	private int marksPerQues;
	
	private int negMarksPerQues;
	
	private byte[] imageData;
	
	private LocalDateTime dateStamp;
	
	private Severity severity;
	private long skillId;
	
	private String fileName;

	public QuestionDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuestionDTO(String question, String optA, String optB, String optC, String optD,
			List<String> expectedAnswers, int marksPerQues, int negMarksPerQues, byte[] imageData,
			LocalDateTime dateStamp, Severity severity, long skillId, String fileName) {
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
		this.skillId = skillId;
		this.fileName = fileName;
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

	public long getSkillId() {
		return skillId;
	}

	public void setSkillId(long skillId) {
		this.skillId = skillId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
}
