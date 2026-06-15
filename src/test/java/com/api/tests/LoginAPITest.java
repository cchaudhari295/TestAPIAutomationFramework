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

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.response.Response;



@Epic("User Management")
@Feature("Authentication")
public class LoginAPITest {
	private UserCredentials userCredentials;
	
	@BeforeMethod(description="Create the payload for login API")
	public void setUp() {
	 userCredentials = new UserCredentials("iamfd", "password");
	}
	
	@Story("Valid user should be able to login into the system")
	@Description("Verify if FD user is able to login via api")
	@Severity(SeverityLevel.BLOCKER)
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
