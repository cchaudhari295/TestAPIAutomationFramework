package com.api.tests;

import static com.api.constant.Role.FD;
import static com.api.utils.SpecUtil.requestSpecWithAuth;
import static com.api.utils.SpecUtil.responseSpec_OK;
import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

@Epic("User Management")
@Feature("User Details")

public class UserDetailsAPITest {
	

	@Story("UserDetails should be shown")
	@Description("Verify if the Userdetials API response is shown correctly")
	@Severity(SeverityLevel.CRITICAL)
	@Test(description="Verify if the user details api response is shown correctly",groups= {"api","smoke","regression"})
	public void userDetailsAPITest() {
				
		given()
			.spec(requestSpecWithAuth(FD))
		.when()
			.get("userdetails")
		.then()
			.spec(responseSpec_OK())
			.body(matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"));		
	}
}
