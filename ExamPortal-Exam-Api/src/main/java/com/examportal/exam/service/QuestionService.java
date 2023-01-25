package com.examportal.exam.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examportal.exam.model.QuestionBank;
import com.examportal.exam.model.Skill;
import com.examportal.exam.repository.QuestionBankRepository;
import com.examportal.exam.repository.SkillRepository;
@Service
@Transactional
public class QuestionService implements IQuestionService{
@Autowired
private QuestionBankRepository questionRepo;
@Autowired
private SkillRepository skillRepo;
 
	//api to add question details to question_bank
	@Override
	public void addQuestionToQuestionBank(QuestionBank question,long skillId) {
		Skill skill= skillRepo.findById(skillId).orElseThrow(()-> new RuntimeException("Invalid skill Id..."));
		question.setSkill(skill);
		questionRepo.save(question);
		
	}
	// get question from question bank by id
	@Override
	public QuestionBank getQuestionById(long id) {
		QuestionBank question= questionRepo.findById(id).orElseThrow(()-> new RuntimeException("Invalid question Id..."));
		return question;
	}
	//update question details by id
	@Override
	public QuestionBank updateQuestion(QuestionBank transientQuestion,long id,long skillId) {
		QuestionBank persistentQuestion=questionRepo.findById(id).orElseThrow(()->new RuntimeException("Invalid question Id..."));
		persistentQuestion.setQuestion(transientQuestion.getQuestion());
		persistentQuestion.setOptA(transientQuestion.getOptA());
		persistentQuestion.setOptB(transientQuestion.getOptB());
		persistentQuestion.setOptC(transientQuestion.getOptC());
		persistentQuestion.setOptD(transientQuestion.getOptD());
		persistentQuestion.setExpectedAnswers(transientQuestion.getExpectedAnswers());
		persistentQuestion.setImageData(transientQuestion.getImageData());
		persistentQuestion.setMarksPerQues(transientQuestion.getMarksPerQues());
		persistentQuestion.setNegMarksPerQues(transientQuestion.getMarksPerQues());
		persistentQuestion.setSeverity(transientQuestion.getSeverity());
		persistentQuestion.setSkill(skillRepo.findById(skillId).orElseThrow());
		persistentQuestion.setFileName(transientQuestion.getFileName());
		return persistentQuestion;
	}
	//delete question by id
	@Override
	public void deleteQuestionById(long id) {
		
		QuestionBank question= questionRepo.findById(id).orElseThrow(()-> new RuntimeException("Invalid question Id..."));
		questionRepo.delete(question);
	}
	//get questions by skill and severity
	@Override
	public QuestionBank getQuestionsBySkillAndSeverity(long skillId, String severity) {
		Skill skill= skillRepo.findById(skillId).orElseThrow(()-> new RuntimeException("Invalid skill Id..."));
		QuestionBank question= questionRepo.findBySkillIdAndSeverity( skill,  severity).orElseThrow(()-> new RuntimeException("Resource does not exist"));
		return question;
	}

}
