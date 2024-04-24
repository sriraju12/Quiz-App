package com.hcl.gl.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hcl.gl.model.Question;
import com.hcl.gl.model.QuestionDto;
import com.hcl.gl.model.Quiz;
import com.hcl.gl.model.Response;
import com.hcl.gl.repository.QuestionRepository;
import com.hcl.gl.repository.QuizRepository;

@Service
public class QuizService {

	@Autowired
	private QuizRepository quizRepository;

	@Autowired
	private QuestionRepository questionRepository;

	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

		List<Question> questions = questionRepository.findRandomQuestionsByCategory(category, numQ);

		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestions(questions);
		quizRepository.save(quiz);

		return new ResponseEntity<>("Success", HttpStatus.CREATED);
	}

	public ResponseEntity<List<QuestionDto>> getQuizQuestions(Integer id) {
		Optional<Quiz> quiz = quizRepository.findById(id);
		List<Question> questionFromDB = quiz.get().getQuestions();
		List<QuestionDto> questionForUser = new ArrayList<>();

		for (Question question : questionFromDB) {
			QuestionDto questionDto = new QuestionDto(question.getId(), question.getQuestionTitle(),
					question.getOption1(), question.getOption2(), question.getOption3(), question.getOption4());
			questionForUser.add(questionDto);
		}

		return new ResponseEntity<>(questionForUser, HttpStatus.OK);

	}

	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
		Quiz quiz = quizRepository.findById(id).get();
		List<Question> questions = quiz.getQuestions();
		int correctAnswers = 0;
		int i = 0;

		for (Response response : responses) {
			if (response.getResponse().equals(questions.get(i).getRightAnswer()))
				correctAnswers++;

			i++;
		}
		return new ResponseEntity<>(correctAnswers, HttpStatus.OK);
	}

}
