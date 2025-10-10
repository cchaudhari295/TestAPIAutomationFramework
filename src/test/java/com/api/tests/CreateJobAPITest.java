package com.api.tests;

import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.utils.SpecUtil;
import com.model.requests.CreateJobPayload;
import com.model.requests.Customer;
import com.model.requests.CustomerAddress;
import com.model.requests.CustomerProduct;
import com.model.requests.Problems;

import io.restassured.RestAssured;

public class CreateJobAPITest {

@Test
public void createJobAPI() {
	Customer customer = new Customer("Chats", "C", "9876543212", "", "testCreateJobAPI@gmail.com", "");
	CustomerAddress customerAddress = new CustomerAddress("A123","Nexus","Whitefield","","","560042","India","Karanataka");
	CustomerProduct customerProduct = new CustomerProduct("2024-07-23T18:30:00.000Z", "10721231924172", "10721231924172", "10721231924172", "2024-07-23T18:30:00.000Z", 1, 1);
	Problems problems = new Problems(1, "Battery Issue");
	Problems[] problemsArray = new Problems[1];
	problemsArray[0]=problems;
	
	CreateJobPayload createJobPayload=new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemsArray);
	
	RestAssured.given()
		.spec(SpecUtil.requestSpecWithAuth(Role.FD, createJobPayload))
		.when()
			.post("/job/create")
		.then()
			 .spec(SpecUtil.responseSpec_OK());
}
	
	
	
	
	
	
	
}
