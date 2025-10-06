package com.api.tests;

import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.utils.SpecUtil;

import groovyjarjarpicocli.CommandLine.Spec;

import static com.api.utils.AuthTokenProvider.*;
import static com.api.utils.ConfigManager.*;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class MasterAPITest {

	
	
	@Test
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
	
	@Test
	public void invalidTokenMasterAPITest() {
		given()
		.spec(SpecUtil.requestSpec())		
	.when()
		.post("master")
	.then()
		.spec(SpecUtil.responseSpec_TEXT(401));
	}
}
