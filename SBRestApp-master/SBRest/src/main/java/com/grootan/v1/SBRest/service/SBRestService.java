package com.grootan.v1.SBRest.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import com.grootan.v1.SBRest.dto.QuestionAndAnswersTemplateDto;
import com.grootan.v1.SBRest.dto.User;

public interface SBRestService {

	public ResponseEntity<?> getRandomQuestions();

	public ResponseEntity<?> verifyQuestionsAndAnswers(List<QuestionAndAnswersTemplateDto> qAdto);

	public ResponseEntity<?> registerUser(User user, HttpServletRequest request);

	public ResponseEntity<?> verifyUser(String gridKey);

}
