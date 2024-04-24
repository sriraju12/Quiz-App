package com.hcl.gl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hcl.gl.model.Question;
import com.hcl.gl.repository.QuestionRepository;

@Service
public class QuestionService {

	@Autowired
	private QuestionRepository questionRepository;

	public ResponseEntity<List<Question>> getAllQuestions() {
		return new ResponseEntity<List<Question>>(questionRepository.findAll(), HttpStatus.OK);
	}

	public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
		return new ResponseEntity<List<Question>>(questionRepository.findByCategory(category), HttpStatus.OK);
	}

	public ResponseEntity<String> addQuestion(Question question) {
		questionRepository.save(question);
		return new ResponseEntity<String>("Success", HttpStatus.CREATED);
	}
}
