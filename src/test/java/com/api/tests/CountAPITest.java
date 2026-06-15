package com.api.tests;

import static com.api.utils.ConfigManager.getProperty;
import static com.api.utils.SpecUtil.requestSpecWithAuth;
import static com.api.utils.SpecUtil.responseSpec_OK;
import static com.api.utils.SpecUtil.responseSpec_TEXT;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.blankOrNullString;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.Test;

import com.api.constant.Role;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Job Management")
@Feature("Job Count")
public class CountAPITest {
	
	@Story("Job Count data is shown correctly")
	@Description("Verify if count api is giving correct response")
	@Severity(SeverityLevel.CRITICAL)
	@Test(description="Verify if the count api is giving correct response",groups= {"api","smoke","regression"})

	public void verifyCountAPIResponse() {
				
					given()
						.spec(requestSpecWithAuth(Role.FD))		
					.when()
						.get("dashboard/count")
					.then()
						.spec(responseSpec_OK())
						.body("message", equalTo("Success"))
						.body("data", notNullValue())
						.body("data.size()",equalTo(3))
						.body("data.count", everyItem(greaterThanOrEqualTo(0)))
						.body("data.label", not(blankOrNullString()))
						.body("data.key", containsInAnyOrder("pending_for_delivery","created_today","pending_fst_assignment"))
						.body(matchesJsonSchemaInClasspath("response-schema/CountResponseSchema.json"));	
			
	}
	
	@Story("Proper status code is shown for invalid token ")
	@Description("Verify if the count api is giving correct status code for invalid token")
	@Severity(SeverityLevel.CRITICAL)
	@Test(description="Verify if the count api is giving correct status code for invalid token",groups= {"api","negative","smoke","regression"})

	public void countAPITest_MissingAuthToken() {

		given()
			.baseUri(getProperty("BASE_URI"))
			.log().uri()
			.log().method()
			.log().headers()
		.when()
			.get("dashboard/count")
		.then()
			.spec(responseSpec_TEXT(401));
	}
}
