package com.api.tests;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.github.fge.jsonschema.messages.JsonSchemaValidationBundle;
import com.model.requests.UserCredentials;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class LoginApiTest {

	UserCredentials userCredentials = new UserCredentials("iamfd", "password");
	
	@Test
	
	public void loginAPITest() {
		
		
	Response res=	given()
		 	.baseUri("http://64.227.160.186:9000/v1")
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
