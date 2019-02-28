package com.grootan.v1.SBRest.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.grootan.v1.SBRest.constants.SBRestConstants;
import com.grootan.v1.SBRest.dto.QuestionAndAnswersTemplateDto;
import com.grootan.v1.SBRest.dto.User;
import com.grootan.v1.SBRest.service.SBRestService;
import com.grootan.v1.SBRest.utils.PasswordUtilis;
import com.grootan.v1.SBRest.utils.SBRestUtilis;



@Service
public class SBRestServiceImpl implements SBRestService {
	

	/*
	 * The Spring Framework provides an easy abstraction for sending email by using
	 * the JavaMailSender interface, and Spring Boot provides auto-configuration for
	 * it as well as a starter module.
	 */
	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public ResponseEntity<?> getRandomQuestions() {
		
		try {
			List<QuestionAndAnswersTemplateDto> masterData = SBRestUtilis.loadMasterData();
			List<QuestionAndAnswersTemplateDto> randomListofQA = new ArrayList<QuestionAndAnswersTemplateDto>();
			
			//Shuffling the List
			Collections.shuffle(masterData);
			
			//getting 10 Random Questions From MasterData List with empty Answers !!
			for(int index=0;index<10;index++) {
				
				QuestionAndAnswersTemplateDto QA = masterData.get(index);
			    randomListofQA.add(new QuestionAndAnswersTemplateDto(QA.getId(), QA.getQuestion(), SBRestConstants.EMPTY_STRING));
			    
			}
			
			return new ResponseEntity<List<QuestionAndAnswersTemplateDto>>(randomListofQA, HttpStatus.OK);
		} catch (Exception e) {
			
		}
		return new ResponseEntity<String>("Internal Error Bad Request", HttpStatus.BAD_REQUEST);
	}
	
	

	@Override
	public ResponseEntity<?> verifyQuestionsAndAnswers(List<QuestionAndAnswersTemplateDto> qAdto) {
		
		try {
			if(qAdto.isEmpty()) {
				return new ResponseEntity<String>("QAList was Empty",HttpStatus.NO_CONTENT);
			}
			else {
				Map<Integer, List<QuestionAndAnswersTemplateDto>> loadMasterDataAsMap = SBRestUtilis.loadMasterDataAsMap();
				
				List<Boolean> statusList = new ArrayList<Boolean>();
				
				for(QuestionAndAnswersTemplateDto QA : qAdto) {
					if(loadMasterDataAsMap.containsKey(QA.getId())) {
						
						QuestionAndAnswersTemplateDto QaObject = loadMasterDataAsMap.get(QA.getId()).get(0);
						
						if(QaObject.getQuestion().equalsIgnoreCase(QA.getQuestion().trim()) && 
								QaObject.getAnswer().equalsIgnoreCase(QA.getAnswer().trim())) {
							
							statusList.add(SBRestConstants.TRUE);
						}
						else {
							
							statusList.add(SBRestConstants.FALSE);
						}
					}
					else {
						
						statusList.add(SBRestConstants.FALSE);
					} 
				}
				return new ResponseEntity<List<Boolean>>(statusList, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<String>("Internal Error Bad Request", HttpStatus.BAD_REQUEST);
	}



	@Override
	public ResponseEntity<?> registerUser(User user,HttpServletRequest request) {
		
		try {
			
			if(Objects.nonNull(user)) {
				if(Objects.nonNull(user.getFirstName()) && Objects.nonNull(user.getEmail()) && Objects.nonNull(user.getPassword()) ) {
					System.out.println("uuuuuu------->>"+request.getContextPath());
					if(SBRestUtilis.sendConfirmtionEmail(javaMailSender,request,user)) {
					user.setStatus(SBRestConstants.TRUE);
					user.setComment("User Created Successfully & Confirmation Mail Sent to his/her EmailId !!");
					}
					else {
						user.setStatus(SBRestConstants.FALSE);
						user.setComment("User Created Successfully & Error Occured while sending the Confirmation mail!!");
					}
					user.setPassword(PasswordUtilis.generateSecurePassword(user.getPassword(), PasswordUtilis.getSalt(30)));
									
					return new ResponseEntity<User>(user, HttpStatus.OK);

				}
				else {
					
					StringBuffer requiredFields = new StringBuffer();
					
					if(!Objects.nonNull(user.getFirstName())) {requiredFields.append("FirstName was Missing\n"); }
					if(!Objects.nonNull(user.getEmail())) {requiredFields.append("Email was Missing\n");  }
					if(!Objects.nonNull(user.getPassword())) {requiredFields.append("Password was Missing\n"); }

					return new ResponseEntity<String>(requiredFields.toString(), HttpStatus.PARTIAL_CONTENT);
				}
				
			}
			else {
				return new ResponseEntity<String>("User Object Was Empty", HttpStatus.NO_CONTENT);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("Internal Error Bad Request", HttpStatus.BAD_REQUEST);
	}



	@Override
	public ResponseEntity<?> verifyUser(String gridKey) {
		
		try {
			if(gridKey.equals(SBRestConstants.getGridKey)) {
				return new ResponseEntity<String>("Email Verified Successfully", HttpStatus.OK);
			}
			else {
				return new ResponseEntity<String>("Email Not Verified", HttpStatus.OK);

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return null;
	}
	
	
	

}
