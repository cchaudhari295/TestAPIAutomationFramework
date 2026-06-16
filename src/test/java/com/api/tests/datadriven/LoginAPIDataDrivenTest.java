package com.api.tests.datadriven;

import static com.api.utils.SpecUtil.requestSpec;
import static com.api.utils.SpecUtil.responseSpec_OK;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.request.model.UserCredentials;
import com.dataproviders.api.bean.UserBean;

import io.restassured.response.Response;

public class LoginAPIDataDrivenTest {

	
	
	@Test(description="Verify if the login api is working for FD user",
			groups= {"api","regression","datadriven"},
			dataProviderClass=com.dataproviders.DataProviderUtils.class,dataProvider="LoginAPIDataProvider")
	public void loginAPITest(UserBean userBean) {
		
	Response res=	given()
		 	.spec(requestSpec(userBean))		 	
		.when()
		 	.post("login")
		 .then()
		 	.spec(responseSpec_OK())
		 	.body("message",equalTo("Success"))
		 	.body("data.token", notNullValue())
		 	.body(matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"))
		 	.extract().response();
		 	
		System.out.println(res.asPrettyString());	
	}
}
