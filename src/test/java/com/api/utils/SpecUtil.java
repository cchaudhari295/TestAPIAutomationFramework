package com.api.utils;

import java.util.concurrent.TimeUnit;

import org.hamcrest.Matchers;

import com.api.constant.Role;
import com.model.requests.UserCredentials;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecUtil {

	public static RequestSpecification requestSpec() {
		//To take care of common request sections
		RequestSpecification requestSpecification= new RequestSpecBuilder()
		 .setBaseUri(ConfigManager.readProperty("BASE_URI"))
		 .setContentType(ContentType.JSON)
		 .setAccept(ContentType.JSON)
		 .log(LogDetail.URI)
		 .log(LogDetail.METHOD)
		 .log(LogDetail.HEADERS)
		 .log(LogDetail.BODY)
		 .build();
		return requestSpecification;

	}
	
	//POST,PUT,PATCH 
	public static RequestSpecification requestSpec(Object payload) {
		//To take care of common request sections
		RequestSpecification requestSpecification= new RequestSpecBuilder()
		 .setBaseUri(ConfigManager.readProperty("BASE_URI"))
		 .setContentType(ContentType.JSON)
		 .setAccept(ContentType.JSON)
		 .setBody(payload)
		 .log(LogDetail.URI)
		 .log(LogDetail.METHOD)
		 .log(LogDetail.HEADERS)
		 .log(LogDetail.BODY)
		 .build();
		return requestSpecification;

	}
	
	public static RequestSpecification requestSpecWithAuth(Role role) {
		//To take care of common request sections
		RequestSpecification requestSpecification= new RequestSpecBuilder()
		 .setBaseUri(ConfigManager.readProperty("BASE_URI"))
		 .setContentType(ContentType.JSON)
		 .setAccept(ContentType.JSON)
		 .addHeader("Authorization", AuthTokenProvider.getToken(role))
		 .log(LogDetail.URI)
		 .log(LogDetail.METHOD)
		 .log(LogDetail.HEADERS)
		 .log(LogDetail.BODY)
		 .build();
		return requestSpecification;

	}
	
	public static ResponseSpecification responseSpec_OK() {
		ResponseSpecification responseSpecification= new ResponseSpecBuilder()
		.expectStatusCode(200)
		.expectContentType(ContentType.JSON)
		.expectResponseTime(Matchers.lessThan(1000L))
		.log(LogDetail.ALL)
		.build();	
		return responseSpecification;
	}
	
	public static ResponseSpecification responseSpec_JSON(int statusCode) {
		ResponseSpecification responseSpecification= new ResponseSpecBuilder()
		.expectStatusCode(200)
		.expectContentType(ContentType.JSON)
		.expectResponseTime(Matchers.lessThan(1000L))
		.log(LogDetail.ALL)
		.build();	
		return responseSpecification;
	}
	
	public static ResponseSpecification responseSpec_TEXT(int statusCode) {
		ResponseSpecification responseSpecification= new ResponseSpecBuilder()
		.expectStatusCode(statusCode)
		.expectResponseTime(Matchers.lessThan(1000L))
		.log(LogDetail.ALL)
		.build();	
		return responseSpecification;
	}
}
