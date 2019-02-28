package com.grootan.v1.SBRest.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grootan.v1.SBRest.dto.QuestionAndAnswersTemplateDto;
import com.grootan.v1.SBRest.dto.User;
import com.grootan.v1.SBRest.service.SBRestService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


/**
 * 
 * @author kranthi.kumar
 *
 */
@RestController
@Api(value="SBRest",description="Rest Api's",produces ="application/json")
@RequestMapping({ "/v1/rest" })
public class SBRestController {
		
	@Autowired
	private SBRestService sbRestService;
	
	
	/**
	 * Get Protocol for Getting Random Questions from Internal DataSource{Application Array}.
	 * @param Null
	 * @return JsonArray of Question With Empty Answers.
	 * 
	 */
	@ApiOperation(value = "Get Random Questions",notes="Get Protocol for Getting Random Questions from Internal DataSource{Application Array}",response = QuestionAndAnswersTemplateDto.class)
	@ApiResponses(value = {
	            @ApiResponse(code = 201, message = "Successfully retrieved list"),
	            @ApiResponse(code = 400, message = "BadRequest"),
	            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	    })
	@GetMapping(path = { "/questions" })
	public ResponseEntity<?> getQuestions() {

		return sbRestService.getRandomQuestions();
	}
	
	
	
	/**
	 * Post Protocol for Verifying the Answers of Random Questions from Previous Get Protocol.
	 * @param Template of Questions with Answers.
	 * @return Boolean for each Question whether it is correct or not.
	 * 
	 */
	@ApiOperation(value = "Verify Answers",notes="Post Protocol for Verifying the Answers of Random Questions Responses from Previous Get Protocol",response=List.class)
	@ApiResponses(value = {
	            @ApiResponse(code = 201, message = "Successfully retrieved list"),
	            @ApiResponse(code = 400, message = "BadRequest"),
	            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	    })
	@PostMapping(path = { "/questions/verify" })
	public ResponseEntity<?> verifyQuestionsAndAnswers(@RequestBody List<QuestionAndAnswersTemplateDto> QAdto) {

		return sbRestService.verifyQuestionsAndAnswers(QAdto);
	}
	
	
	
	/**
	 * Post Protocol for Register User and Sent an Email 
	 * @param User Object
	 * @return User Object with password Hash.
	 * 
	 */
	@ApiOperation(value="Register User",notes = "Post Protocol for Register User and Sent an Email ",response = User.class)
	@ApiResponses(value = {
	            @ApiResponse(code = 201, message = "User Created and Mail Sent Successfully !!"),
	            @ApiResponse(code = 204, message = "User Body is Missing !!"),
	            @ApiResponse(code = 206, message = "Paritial User Body is Missing !!"),
	            @ApiResponse(code = 400, message = "BadRequest"),
	            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	    })
	@PostMapping(path = { "/register" })
	public ResponseEntity<?> registerUser(HttpServletRequest request,@RequestBody User user) {

		return sbRestService.registerUser(user,request);
	}
	
	
	/**
	 * Get Protocol for Verifying the User with the grid key
	 * @param gridKey
	 * @return Boolean Message,
	 * 
	 */
	@ApiOperation(value="Verify EmailId", notes= "Get Protocol for Verifying the User EmailId with the grid key",response=String.class)
	@ApiResponses(value = {
	            @ApiResponse(code = 201, message = "User Verified Successfully !!"),
	            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	    })
	@GetMapping(path = { "/user/verify/{gridKey}" })
	public ResponseEntity<?> verifyUser(@PathVariable("gridKey") String gridKey) {
		
		return sbRestService.verifyUser(gridKey);
	}

}
