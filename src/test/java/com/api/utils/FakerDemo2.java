package com.api.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.github.javafaker.Faker;

public class FakerDemo2 {
	private final static String COUNTRY="India";
	
	
	
public static void main(String[] args) {
	
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
	
	CreateJobPayload payload=new CreateJobPayload(1, 2, 1, 1, customer, customerAddress, customerProduct, problemsArray);
	System.out.println(payload);
}
}
