package com.api.tests;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.utils.SpecUtil;

import static com.api.utils.ConfigManager.*;

import static com.api.utils.AuthTokenProvider.*;

import java.io.IOException;

import static io.restassured.RestAssured.*;
import io.restassured.http.Header;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class CountAPITest {

	
	
	@Test
	public void verifyCountAPIResponse() {
				
					given()
						.spec(SpecUtil.requestSpecWithAuth(Role.FD))		
					.when()
						.get("dashboard/count")
					.then()
						.spec(SpecUtil.responseSpec_OK())
						.body("message", equalTo("Success"))
						.body("data", notNullValue())
						.body("data.size()",equalTo(3))
						.body("data.count", everyItem(greaterThanOrEqualTo(0)))
						.body("data.label", not(blankOrNullString()))
						.body("data.key", containsInAnyOrder("pending_for_delivery","created_today","pending_fst_assignment"))
						.body(matchesJsonSchemaInClasspath("response-schema/CountResponseSchema.json"));
			
			
	}
	
	
	@Test
	public void countAPITest_MissingAuthToken() {

		given()
			.baseUri(readProperty("BASE_URI"))
			.log().uri()
			.log().method()
			.log().headers()
		.when()
			.get("dashboard/count")
		.then()
			.spec(SpecUtil.responseSpec_TEXT(401));
	}
}
