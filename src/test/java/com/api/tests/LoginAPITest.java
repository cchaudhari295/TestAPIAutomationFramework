package com.api.tests;

import static com.api.utils.SpecUtil.requestSpec;
import static com.api.utils.SpecUtil.responseSpec_OK;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.request.model.UserCredentials;

import io.restassured.response.Response;

public class LoginAPITest {
	private UserCredentials userCredentials;
	
	@BeforeMethod(description="Create the payload for login API")
	public void setUp() {
	 userCredentials = new UserCredentials("iamfd", "password");
	}
	
	@Test(description="Verify if the login api is working for FD user",groups= {"api","regression","smoke"})
	public void loginAPITest() {
		
	Response res=	given()
		 	.spec(requestSpec(userCredentials))		 	
		.when()
		 	.post("login")
		 .then()
		 	.spec(responseSpec_OK())
		 	.body("message",equalTo("Success"))
		 	.body("data.token", notNullValue())
		 	.body(matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"))
		 	.extract().response();
		 	
		System.out.println(res.asPrettyString());
		
		
	}
	
	
}
