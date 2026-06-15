package com.api.utils;

import java.util.Locale;

import com.github.javafaker.Faker;

public class FakerDemo {
public static void main(String[] args) {
	
	
	Locale locale=new Locale("en-IND"); //Data in indian form
	Faker faker=new Faker(locale);

	 //Faker faker=new Faker(); 
	 String firstName=faker.name().firstName();
	 String lastName=faker.name().lastName();
	 System.out.println(firstName);
	 System.out.println(lastName);
	 
	 System.out.println(faker.address().buildingNumber()); //fluent style of writing the code. Design Pattern: Fluent Interface Pattern
	 System.out.println(faker.address().streetAddress());
	 System.out.println(faker.address().streetName());
	 System.out.println(faker.address().city());
	 System.out.println(faker.number().digit()); //7
	 System.out.println(faker.number().digits(10)); //9876543234
	 System.out.println(faker.numerify("970#######")); //9701234567
	 
	 System.out.println(faker.internet().emailAddress()); //9701234567
	 
	 System.out.println(faker.phoneNumber().cellPhone()); //Dont use it. It contains dot.. 369.237.9927


}
}
