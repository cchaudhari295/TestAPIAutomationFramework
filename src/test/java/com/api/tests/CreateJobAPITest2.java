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
import com.github.javafaker.Faker;

import static com.api.utils.DateTimeUtil.*;
import static com.api.utils.SpecUtil.*;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;


public class CreateJobAPITest2 {
	private CreateJobPayload createJobPayload;
	private final static String COUNTRY="India";


	@BeforeMethod(description="Creating create job api request payload")
	public void setUp() {
		//create fake createJobAPI request payload
		//Create fake Customer Object
		
		Faker faker = new Faker(new Locale("en-IND"));
		
		//Fake Customer Details
		String firstName= faker.name().firstName();
		String lastName=faker.name().lastName();
		String mobileNumber=faker.numerify("970#######");
		String altMobileNumber=faker.numerify("970#######");
		String customerEmailAddress=faker.internet().emailAddress();
		String altCustomerEmailAddress=faker.internet().emailAddress();

		Customer customer=new Customer(firstName,lastName,mobileNumber,altMobileNumber,customerEmailAddress,altCustomerEmailAddress);
		System.out.println(customer);

		//Fake Customer Address
		String flatNumber=faker.numerify("###");
		String apartmentName=faker.address().streetName();
		String streetName=faker.address().streetName();
		String landmark=faker.address().streetName();
		String area=faker.address().streetName();
		String pinCode=faker.numerify("#####");
		String state=faker.address().state();

		CustomerAddress customerAddress=new CustomerAddress(flatNumber, apartmentName, streetName, landmark, area, pinCode, COUNTRY, state);
		System.out.println(customerAddress);
		
		
		//Customer Product Fake Object
		
		String dop= DateTimeUtil.getTimeWithDaysAgo(10);
		String imeiSerialNumber=faker.numerify("##############");
		String popurl=faker.internet().url();
		CustomerProduct customerProduct=new CustomerProduct(dop, imeiSerialNumber, imeiSerialNumber, imeiSerialNumber, popurl, 1, 1);
		
		System.out.println(customerProduct);

		//Fake Problems Data
		
		String fakeRemark=faker.lorem().sentence(5); //creates fake sentence of 5 words
		
		//Generate random number between 1 -27
		Random random=new Random();
		int problemId=random.nextInt(27)+1;//27 is exclusive. random number from 0-26
		
		Problems problems=new Problems(problemId,fakeRemark);
		List<Problems> problemsArray = new ArrayList<Problems>();
		problemsArray.add(problems);
		
		createJobPayload=new CreateJobPayload(1, 2, 1, 1, customer, customerAddress, customerProduct, problemsArray);
	
	}
	
	
@Test(description="Verify if the create job api is able to create inwarranty job",groups= {"api","smoke","regression"})

public void createJobAPI() {

	RestAssured.given()
		.spec(requestSpecWithAuth(Role.FD, createJobPayload))
		.when()
			.post("/job/create")
		.then()
			 .spec(responseSpec_OK())
			 .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
			 .body("message",equalTo("Job created successfully. "))
			 .body("mst_service_location_id", equalTo(ServiceLocation.SERVICE_LOCATION_A.getCode()))
			 .body("job_number",startsWith("JOB_"));
}
	
}
