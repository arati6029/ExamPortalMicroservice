package com.examportal.exam.rest;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.examportal.exam.dto.QuestionDTO;
import com.examportal.exam.model.QuestionBank;
import com.examportal.exam.model.Severity;
import com.examportal.exam.model.Skill;
import com.examportal.exam.service.IQuestionService;

@RestController
@RequestMapping("/questions")
public class QuestionResource {
	@Autowired
	private IQuestionService questionService;

//api to add question details to question_bank

@PostMapping("/question")
ResponseEntity<?> addQuestion(@RequestBody QuestionDTO questionDto){
	QuestionBank transientQuestion=new QuestionBank(questionDto.getQuestion(),questionDto.getOptA(),questionDto.getOptB(),questionDto.getOptC(),questionDto.getOptD(),questionDto.getExpectedAnswers(),questionDto.getMarksPerQues(),questionDto.getNegMarksPerQues(),questionDto.getSeverity());
	questionService.addQuestionToQuestionBank(transientQuestion,questionDto.getSkillId());
	return ResponseEntity.ok().body("Question added to QuestionBank...");
}
// get question from question bank by id
@GetMapping("/{id}/question")
ResponseEntity<?> getQuestionById(@PathVariable long id){
	QuestionBank question = questionService.getQuestionById(id);
	if(question!=null) {
		return ResponseEntity.ok().body(question);
	}else {
		return ResponseEntity.notFound().build();
	}
}

//update question details by id
@PutMapping("/{id}/question")
ResponseEntity<?> updateQuestionDetails(@RequestBody QuestionDTO questionDto,@PathVariable long id){
	QuestionBank transientQuestion= new QuestionBank(questionDto.getQuestion(),questionDto.getOptA(),questionDto.getOptB(),questionDto.getOptC(),questionDto.getOptD(),questionDto.getExpectedAnswers(),questionDto.getMarksPerQues(),questionDto.getNegMarksPerQues(),questionDto.getSeverity());
	QuestionBank question=questionService.updateQuestion(transientQuestion,id,questionDto.getSkillId());
	if(question!=null) {
		return ResponseEntity.ok().body(question);
	}else {
		return ResponseEntity.notFound().build();
	}
}
//delete question by id
@DeleteMapping("/{id}/question")
ResponseEntity<?> deleteQuestion(@PathVariable long id){
	questionService.deleteQuestionById(id);
	return ResponseEntity.ok().body("Question deleted from QuestionBank...");
}
//get questions by skill and severity
@GetMapping("/questions")
ResponseEntity<?> getQuestionBySkillAndSeverity(@RequestParam("skillId")long skillId,@RequestParam("severity")String severity){
	QuestionBank question = questionService.getQuestionsBySkillAndSeverity(skillId,severity);
	if(question!=null) {
		return ResponseEntity.ok().body(question);
	}else {
		return ResponseEntity.notFound().build();
	}
}

}
