package com.api.tests;

import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

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
import static com.api.utils.DateTimeUtil.*;
import com.api.utils.SpecUtil;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPITest {

@Test
public void createJobAPI() {
	Customer customer = new Customer("Chats", "C", "9876543212", "", "testCreateJobAPI@gmail.com", "");
	CustomerAddress customerAddress = new CustomerAddress("A123","Nexus","Whitefield","","","560042","India","Karanataka");
	CustomerProduct customerProduct = new CustomerProduct(getTimeWithDaysAgo(10), "10721231923141", "10721231924141", "10721231924141", getTimeWithDaysAgo(10), Product.NEXUS_2.getCode(), Model.NEXUS_2_BLUE.getCode());
	Problems problems = new Problems(Problem.OVERHEATING.getCode(), "Battery Issue");
	List<Problems> problemsArray = new ArrayList<Problems>();
	
	problemsArray.add(problems);
	
	CreateJobPayload createJobPayload=new CreateJobPayload(ServiceLocation.SERVICE_LOCATION_A.getCode(), Platform.FRONT_DESK.getCode(), Warranty_Status.IN_WARRANTY.getCode(), OEM.APPLE.getCode(), customer, customerAddress, customerProduct, problemsArray);
	
	RestAssured.given()
		.spec(SpecUtil.requestSpecWithAuth(Role.FD, createJobPayload))
		.when()
			.post("/job/create")
		.then()
			 .spec(SpecUtil.responseSpec_OK())
			 .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
			 .body("message",equalTo("Job created successfully. "))
			 .body("mst_service_location_id", equalTo(ServiceLocation.SERVICE_LOCATION_A.getCode()))
			 .body("job_number",startsWith("JOB_"));
}
	
	
	
	
	
	
	
}
