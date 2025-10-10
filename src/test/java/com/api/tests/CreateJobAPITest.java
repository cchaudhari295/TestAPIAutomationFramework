package com.api.tests;

import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.utils.SpecUtil;
import com.model.requests.CreateJobPayload;
import com.model.requests.Customer;
import com.model.requests.CustomerAddress;
import com.model.requests.CustomerProduct;
import com.model.requests.Problems;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPITest {

@Test
public void createJobAPI() {
	Customer customer = new Customer("Chats", "C", "9876543212", "", "testCreateJobAPI@gmail.com", "");
	CustomerAddress customerAddress = new CustomerAddress("A123","Nexus","Whitefield","","","560042","India","Karanataka");
	CustomerProduct customerProduct = new CustomerProduct("2024-07-23T18:30:00.000Z", "10721231924144", "10721231924144", "10721231924144", "2024-07-23T18:30:00.000Z", 1, 1);
	Problems problems = new Problems(1, "Battery Issue");
	List<Problems> problemsArray = new ArrayList<Problems>();
	
	problemsArray.add(problems);
	
	CreateJobPayload createJobPayload=new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemsArray);
	
	RestAssured.given()
		.spec(SpecUtil.requestSpecWithAuth(Role.FD, createJobPayload))
		.when()
			.post("/job/create")
		.then()
			 .spec(SpecUtil.responseSpec_OK())
			 .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
			 .body("message",equalTo("Job created successfully. "))
			 .body("mst_service_location_id", equalTo(1))
			 .body("job_number",startsWith("JOB_"));
}
	
	
	
	
	
	
	
}
