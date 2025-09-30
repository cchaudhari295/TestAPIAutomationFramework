package com.api.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

import java.io.IOException;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import static com.api.utils.ConfigManager.*;
import com.model.requests.UserCredentials;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class LoginAPITest {

	UserCredentials userCredentials = new UserCredentials("iamfd", "password");

	@Test
	public void loginAPITest() {
		
	Response res=	given()
		 	.baseUri(readProperty("BASE_URI"))
		 	.contentType(ContentType.JSON)
		 	.accept(ContentType.JSON)
		 	.body(userCredentials)
		 	.log().uri()
		 	.log().method()
		 	.log().body()
		 	.log().headers()
		 	
		.when()
		 	.post("login")
		 .then()
		 	.statusCode(200)
		 	.time(lessThan(1500L))
		 	.body("message",equalTo("Success"))
		 	.body("data.token", notNullValue())
		 	.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"))
		 	.log().ifValidationFails()
		 	.extract().response();
		 	
		System.out.println(res.asPrettyString());
		
		
	}
	
	
}
