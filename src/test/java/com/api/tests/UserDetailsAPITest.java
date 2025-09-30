package com.api.tests;

import static com.api.utils.ConfigManager.readProperty;
import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static com.api.constant.Role.*;

import static com.api.utils.AuthTokenProvider.*;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

public class UserDetailsAPITest {

	
	
	@Test
	public void userDetailsAPITest() {
		
		Header authHeader = new Header("Authorization",getToken(FD));
		
		given()
			.baseUri(readProperty("BASE_URI"))
			.header(authHeader)
			.accept(ContentType.JSON)
			.log().uri()
			.log().method()
			.log().body()
			.log().headers()
		.when()
			.get("userdetails")
		.then()
			.statusCode(200)
			.time(Matchers.lessThan(1500L))
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"));
			
		
		
	}
}
