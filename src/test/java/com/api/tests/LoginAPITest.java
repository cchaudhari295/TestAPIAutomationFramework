package com.api.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.Test;

import static com.api.utils.SpecUtil.*;

import com.api.request.model.UserCredentials;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class LoginAPITest {

	UserCredentials userCredentials = new UserCredentials("iamfd", "password");

	@Test
	public void loginAPITest() {
		
	Response res=	given()
		 	.spec(requestSpec(userCredentials))		 	
		.when()
		 	.post("login")
		 .then()
		 	.spec(responseSpec_OK())
		 	.body("message",equalTo("Success"))
		 	.body("data.token", notNullValue())
		 	.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"))
		 	.extract().response();
		 	
		System.out.println(res.asPrettyString());
		
		
	}
	
	
}
