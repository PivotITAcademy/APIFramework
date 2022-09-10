package com.apiAutomation.LearningApiTesting;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;


import io.restassured.response.Response;


public class GetJiraIssueApiTest {

	
	
	@Test
	public void getJiraIssue(ITestContext testContext) {

		/* Gherkin Syntax - Cucumber
		 * given i have provided required parameters in header and the base url preconditions
		 * when i invoke the api - Execute actual test
		 * then i get the response - Get the response and validate
		 * */
		String storyId = (String) testContext.getAttribute("storyId");
		
		System.out.println("storyId:"+storyId);
		
		given().header("Authorization","Basic a2F0ZXBxYUBnbWFpbC5jb206RUd3OTZjTTRKcTlYN2Y4MHlrd2s5QzNC").
		baseUri("https://katepivot.atlassian.net/rest/api/3/issue/").log().all().get(storyId).
		then().log().all().statusCode(200);
		
		
	}
	
	@Test @Ignore
	public void validateResponseBody() {
		
		given().header("Authorization","Basic a2F0ZXBxYUBnbWFpbC5jb206RUd3OTZjTTRKcTlYN2Y4MHlrd2s5QzNC").
		baseUri("https://katepivot.atlassian.net/rest/api/3/issue/").log().all().get("TEST-22"). 
		then().assertThat().body("key",equalTo("TEST-22")).body("fields.description.content[0].content[0].text", equalTo("Creating issue using jira api from postman"));
	}
}
