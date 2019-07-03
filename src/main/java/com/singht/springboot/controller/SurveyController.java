package com.singht.springboot.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.singht.springboot.model.Question;
import com.singht.springboot.service.SurveyService;

@RestController
public class SurveyController {
	
	@Autowired
	SurveyService surveyService;
	
	// GET - /surveys/{surveyId}/questions - show survey questions
	@GetMapping("/surveys/{surveyId}/questions")
	public List<Question> retrieveQuestionsFromSurvey(@PathVariable String surveyId){
		System.out.println("retrieveQuestionsFromSurvey : " + surveyId);
		return surveyService.retrieveQuestions(surveyId);
	}
	
	// POST- a new question to a survey.- /surveys/{surveyId}/questions
	// The new question to add comes in request body.SO to get that, 
	// we use @RequestBody annotation. It will be mapped to the Question object in the paramater below.
	// Return - URI of the new resource in Header.
	// Response status - created- 201. Use ResponseEntity.
	@PostMapping("/surveys/{surveyId}/questions")
	public ResponseEntity<Void> addQuestionToSurvey(
			@PathVariable String surveyId,
			@RequestBody Question newQuestion){
		
		System.out.println("addQuestionToSurvey : " + surveyId);
		
		Question question = surveyService.addQuestion(surveyId, newQuestion);
		
		if(question == null) {
			// 204 no content. 
			return ResponseEntity.noContent().build();
		}
		
		// Build URI of the new question.
		///surveys/{surveyId}/questions + /id of new ques.
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(question.getId()).toUri();
			
		// return status
		return ResponseEntity.created(uri).build();		
	}
	
	// GET - /surveys/{surveyId}/questions/{questionId} - show survey questions
	@GetMapping("/surveys/{surveyId}/questions/{questionId}")
	public Question retrieveDetailsForSurveyIdsQuestion(
			@PathVariable String surveyId,
			@PathVariable String questionId){
		System.out.println("retrieveQuestionsFromSurvey : " + surveyId+ "Q id:" + questionId);
		return surveyService.retrieveQuestion(surveyId, questionId);
	
	}
	

}
