package com.examportal.exam.rest;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.examportal.exam.dto.AddQuestionDTO;
//import com.examportal.exam.dto.ApiResponse;
//import com.examportal.exam.dto.QuestionListDTO;
//import com.examportal.exam.dto.UpdateQuestionDTO;
//import com.examportal.exam.model.Question;
//import com.examportal.exam.service.IQuestionService;

@RestController
@RequestMapping("/question")
//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin("*")
@Validated
public class QuestionResource {
	@Autowired
	private IQuestionService quesService;
	
	@PreAuthorize("hasAuthority('EXAM_ADMIN')")
	@PostMapping("/add_quest")
	public ResponseEntity<?> addQuestion(@RequestBody AddQuestionDTO newQuestionDto) {
		quesService.addQuestion(new Question(newQuestionDto.getQuesNo(), newQuestionDto.getQuestion(),
				newQuestionDto.getOptA(), newQuestionDto.getOptB(), newQuestionDto.getOptC(), newQuestionDto.getOptD(),
				newQuestionDto.getAnswer()), newQuestionDto.getExamId());
		return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Question added   successfully"));
	}
	@PreAuthorize("hasAuthority('EXAM_ADMIN')")
	@PostMapping("/add_quest_list/{id}")
	public ResponseEntity<?> addQuestionList(@RequestBody List<QuestionListDTO> newQuestionDto, @PathVariable Long id) {
//		quesService.addQuestionList(new Question(newQuestionDto.get, newQuestionDto.getQuestion(),
//				newQuestionDto.getOptA(), newQuestionDto.getOptB(), newQuestionDto.getOptC(), newQuestionDto.getOptD(),
//				newQuestionDto.getAnswer()), newQuestionDto.getExamId());
		quesService.addQuestionList(newQuestionDto, id);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Question added   successfully"));
	}
	@PreAuthorize("hasAuthority('EXAM_ADMIN')")
	@PutMapping("/update_quest")
	public ResponseEntity<?> updateQuestion(@RequestBody UpdateQuestionDTO updateQuestionDto) {
		quesService.updateQuestion(new Question(updateQuestionDto.getId(), updateQuestionDto.getQuesNo(),
				updateQuestionDto.getQuestion(), updateQuestionDto.getOptA(), updateQuestionDto.getOptB(),
				updateQuestionDto.getOptC(), updateQuestionDto.getOptD(), updateQuestionDto.getAnswer()));
		return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Question Updated   successfully"));
	}
}
