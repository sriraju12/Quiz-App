package com.hcl.service;

import com.hcl.feign.QuizInterface;
import com.hcl.model.QuestionDto;
import com.hcl.model.Quiz;
import com.hcl.model.Response;
import com.hcl.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuizInterface quizInterface;


    public ResponseEntity<String> createQuiz(String categoryName, Integer numQuestions, String title) {
      List<Integer> questions =  quizInterface.getQuestionsForQuiz(categoryName,numQuestions).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionsIds(questions);
        quizRepository.save(quiz);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }


    public ResponseEntity<List<QuestionDto>> getQuizQuestions(Integer id) {
        Quiz quiz = quizRepository.findById(id).get();
         List<Integer> questionIds =  quiz.getQuestionsIds();
         ResponseEntity<List<QuestionDto>> questions = quizInterface.getQuestionsFromId(questionIds);
         return questions;
    }


    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {

        ResponseEntity<Integer> score = quizInterface.getScore(responses);
        return score;
    }
}
