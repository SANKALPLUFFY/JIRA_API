package testCases;

import org.testng.annotations.Test;

import Utilities.Utilities;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;

public class DefectRaising 

{
	
	@Test
	public void loginToJIRA() 
	{
		RestAssured.baseURI = "http://localhost:8080/";
		
		String sessionIDPath = "session.value";
		
		String SessionIDPOST = given()
				
				.header("Content-Type", "application/json")
				
				.body(Utilities.loginToJira("boradkarsankalp6", "@NEp|E(E1995")).log().all().
		
		when().post("rest/auth/1/session")
		
		.then().extract().response().asString();
		
		String SessionID = Utilities.SessionID(SessionIDPOST,sessionIDPath);
		
		
		given().log().all().pathParam("issueId", "12345")
		
		.header("Content-Type", "application/json")
		
		.body(Utilities.addCommentJiraBoday("adding a comment"))
		
		.when().post("rest/api/2/issue/{issueId}/comment");
	}
}
