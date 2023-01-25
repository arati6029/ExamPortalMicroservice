package com.examportal.exam.service;

import com.examportal.exam.model.QuestionBank;

public interface IQuestionService {

	void addQuestionToQuestionBank(QuestionBank question,long skillId);

	QuestionBank getQuestionById(long id);

	QuestionBank updateQuestion(QuestionBank transientQuestion,long id, long skillId);

	void deleteQuestionById(long id);

	QuestionBank getQuestionsBySkillAndSeverity(long skillId, String severity);

}
