package com.apiAutomation.LearningApiTesting;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.ITestContext;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class CreateIssueApiTest {

	String requestBody = "{\r\n"
			+ "  \"fields\": {\r\n"
			+ "    \"summary\": \"Test Summary\",\r\n"
			+ "    \"project\":\r\n"
			+ "    {\r\n"
			+ "      \"id\": \"10000\"\r\n"
			+ "    },\r\n"
			+ "    \"reporter\": {\r\n"
			+ "      \"id\": \"63039afca4f57644346ab426\"\r\n"
			+ "    },\r\n"
			+ "    \"description\":{\"type\": \"doc\",\r\n"
			+ "      \"version\": 1,\r\n"
			+ "      \"content\": [\r\n"
			+ "        {\r\n"
			+ "          \"type\": \"paragraph\",\r\n"
			+ "          \"content\": [\r\n"
			+ "            {\r\n"
			+ "              \"text\": \"Creating issue from rest assured\",\r\n"
			+ "              \"type\": \"text\"\r\n"
			+ "            }\r\n"
			+ "          ]\r\n"
			+ "        }\r\n"
			+ "      ]}\r\n"
			+ "    ,\r\n"
			+ "    \"issuetype\": {\r\n"
			+ "      \"id\": 10001\r\n"
			+ "    }\r\n"
			+ "  }\r\n"
			+ "}";
	
	
	@Test @Ignore
	public void createIssueTest() {
		given().header("Authorization","Basic a2F0ZXBxYUBnbWFpbC5jb206RUd3OTZjTTRKcTlYN2Y4MHlrd2s5QzNC").header("Content-Type","application/json").body(requestBody).
		baseUri("https://katepivot.atlassian.net/rest/api/3/issue/").log().all().post().then().log().all().statusCode(201). 
		assertThat().body("key", notNullValue());
	}
	
	@Test
	public void createIssueTestExtractValue(ITestContext testContext) {
	
		Response response = given().header("Authorization","Basic a2F0ZXBxYUBnbWFpbC5jb206RUd3OTZjTTRKcTlYN2Y4MHlrd2s5QzNC").header("Content-Type","application/json").body(requestBody).
				baseUri("https://katepivot.atlassian.net/rest/api/3/issue/").post();
		
		String key = response.getBody().jsonPath().get("key");
		System.out.println("key : "+key);
		
		//Setting the extracted value in TestContext
		testContext.setAttribute("storyId", key);
	}
	
	@Test @Ignore
	public void createIssueTestExtractValue2() {
		
		String key =given().header("Authorization","Basic a2F0ZXBxYUBnbWFpbC5jb206RUd3OTZjTTRKcTlYN2Y4MHlrd2s5QzNC").header("Content-Type","application/json").body(requestBody).
				baseUri("https://katepivot.atlassian.net/rest/api/3/issue/").post().then().extract().body().jsonPath().get("key");
		
		System.out.println("key : "+key);
	}
}
