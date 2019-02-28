package com.grootan.v1.SBRest.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.grootan.v1.SBRest.constants.SBRestConstants;
import com.grootan.v1.SBRest.dto.QuestionAndAnswersTemplateDto;
import com.grootan.v1.SBRest.dto.User;

/**
 * 
 * @author kranthi.kumar
 *
 */

public class SBRestUtilis {
	
	

	/**
	 * Loading MasterData From SBRestConstants of 2D String Array 
	 * @param null
	 * @return List of QuestionAndAnswersTemplateDto Objects.
	 * 
	 */
	public static List<QuestionAndAnswersTemplateDto> loadMasterData() {
		
		AtomicInteger counter = new AtomicInteger(1);
		
		List<QuestionAndAnswersTemplateDto> QuestionAndAnswersList = 
				new ArrayList<QuestionAndAnswersTemplateDto>();
		
		for(String[] s : SBRestConstants.QuestionsAndAnswers) {

			QuestionAndAnswersList.add(new QuestionAndAnswersTemplateDto(counter.getAndIncrement(), s[0], s[1]));
			
		}
		
		return QuestionAndAnswersList;
		
		
	}
	
	
	
	
	/**
	 * Loading MasterData as Map from masterData() function
	 * @param null
	 * @return Map{Key as ID and Value as Object Data }
	 * 
	 */
	public static Map<Integer, List<QuestionAndAnswersTemplateDto>> loadMasterDataAsMap() {
		
		return ListtoMap(loadMasterData());
	}
	
	
	

	/**
	 * Converting List to Map Function
	 * @param list
	 * @return map
	 */
	private static Map<Integer, List<QuestionAndAnswersTemplateDto>> ListtoMap(List<QuestionAndAnswersTemplateDto> list) {
		
		  return list
				.stream()
				.collect(Collectors.groupingBy(QuestionAndAnswersTemplateDto::getId));

		}

    /**
     * 
     * @param javaMailSender
     * @param request 
     * @param user
     * @return Boolean of Mail Sent !!
     * 
     */
	public static Boolean sendConfirmtionEmail(JavaMailSender javaMailSender, HttpServletRequest request, User user) {
		try {
			
			 MimeMessage message = javaMailSender.createMimeMessage();
			 MimeMessageHelper helper = new MimeMessageHelper(message, true);
			 
			 helper.setTo(user.getEmail());
			 helper.setSubject("Confirm Account");
			 helper.setText(getEmailBody(user,request), true);
			 
			 javaMailSender.send(message);
			
		} catch (Exception e) {
			return false;
		}
		return true;
	}




	private static String getEmailBody(User user, HttpServletRequest request) {
		String url = request.getRequestURL().toString();
		String baseURL = url.substring(0, url.length() - request.getRequestURI().length()) + request.getContextPath() + "/";
		StringBuilder sb = new StringBuilder();
		sb.append("<h3>Dear "+user.getFirstName()+", </h3>")
		.append("<h4>To confirm your account please click the below link :  </h4>")
		.append(baseURL+"v1/rest/user/verify/"+SBRestConstants.getGridKey);
		
		return sb.toString();
	}


}
