package com.api.tests;

import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constant.Model;
import com.api.constant.OEM;
import com.api.constant.Platform;
import com.api.constant.Problem;
import com.api.constant.Product;
import com.api.constant.Role;
import com.api.constant.ServiceLocation;
import com.api.constant.Warranty_Status;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.api.utils.DateTimeUtil;
import com.api.utils.FakerDataGenerator;
import com.github.javafaker.Faker;

import static com.api.utils.DateTimeUtil.*;
import static com.api.utils.SpecUtil.*;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;


public class CreateJobAPITestWithFakeData{

	
@Test(description="Verify if the create job api is able to create inwarranty job",groups= {"api","smoke","regression"},dataProviderClass=com.dataproviders.DataProviderUtils.class,dataProvider="CreateJobAPIFakerDataProvider")

public void createJobAPI(CreateJobPayload createJobPayload) {

	RestAssured.given()
		.spec(requestSpecWithAuth(Role.FD, createJobPayload))
		.when()
			.post("/job/create")
		.then()
			 .spec(responseSpec_OK())
			 .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
			 .body("message",equalTo("Job created successfully. "))
			 .body("data.mst_service_location_id", equalTo(ServiceLocation.SERVICE_LOCATION_A.getCode()))
			 .body("data.job_number",startsWith("JOB_"));
}
	
}
