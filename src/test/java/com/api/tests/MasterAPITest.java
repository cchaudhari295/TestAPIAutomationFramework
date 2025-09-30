package com.api.tests;

import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.constant.Role;
import static com.api.utils.AuthTokenProvider.*;
import static com.api.utils.ConfigManager.*;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class MasterAPITest {

	
	
	@Test
	public void verifyMasterAPITest(){
		
					given()
						.baseUri(readProperty("BASE_URI"))
						.header("Authorization",getToken(Role.FD))
						.contentType("")
						.log().uri()
						.log().headers()
						.log().body()
					.when()
						.post("master")
						
					.then()
						.statusCode(200)
						.time(lessThan(1000L))
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
	
	@Test
	public void invalidTokenMasterAPITest() {
		given()
		.baseUri(readProperty("BASE_URI"))
		.header("Authorization","")
		.contentType("")
		.log().uri()
		.log().headers()
		.log().body()
	.when()
		.post("master")
	.then()
		.statusCode(401)
		.log().all();
	}
}
