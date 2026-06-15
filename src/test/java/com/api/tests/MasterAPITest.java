package com.api.tests;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.utils.SpecUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Job Management")
@Feature("Master API")
public class MasterAPITest {
	
	
	@Story("Master API should bring OEM details, Problem Type, Warranty Status")
	@Description("Verify if master api is giving correct response")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description="Verify if the master api is giving correct response",groups= {"api","smoke","regression"})

	public void verifyMasterAPITest(){
		
					given()
						.spec(SpecUtil.requestSpecWithAuth(Role.FD))						
					.when()
						.post("master")
						
					.then()
						.spec(SpecUtil.responseSpec_OK())
						.body("message", equalTo("Success"))
						.body("data", notNullValue())
						.body("data", hasKey("mst_oem"))
						.body("data", hasKey("mst_model"))
						.body("$", hasKey("message"))
						.body("$", hasKey("data"))
						.body("data.mst_oem.size()", equalTo(2))
						.body("data.mst_oem.size()", greaterThan(0))
						.body("data.mst_oem.id",everyItem(notNullValue()))
						.body("data.mst_oem.name", everyItem(notNullValue()))
						.body(matchesJsonSchemaInClasspath("response-schema/MasterResponseSchema.json"))
						.log().all();
		
	}
	
	@Story("Master API should give correct status code for invalid token")
	@Description("Verify if master api is giving status code for invalid token")
	@Severity(SeverityLevel.BLOCKER)
	
	@Test(description="Verify if the master api is giving correct status code for invalid token",groups= {"api","negative","smoke","regression"})
	public void invalidTokenMasterAPITest() {
		given()
		.spec(SpecUtil.requestSpec())		
	.when()
		.post("master")
	.then()
		.spec(SpecUtil.responseSpec_TEXT(401));
	}
}
