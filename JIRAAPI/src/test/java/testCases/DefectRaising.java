package testCases;


import Utilities.Utilities;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;

public class DefectRaising 

{
	
	public String SessionID;
	
	@Test
	public void loginToJIRA() 
	{
		//login
		
		
			RestAssured.baseURI = "http://localhost:8080/";
			
			String sessionIDPath = "session.value";
			
			SessionFilter session = new SessionFilter();
			
			//Initiating SessionFIlter class stores new created session which can be passed to any required functionality
			
			String SessionIDPOST = given().log().all()
					
			.header("Content-Type", "application/json")
					
			.body(Utilities.loginToJira("boradkarsankalp6","@NEp|E(E1995")).log().all()
			
			.filter(session)
			
			// here session will get stored such that we can pass thge session as "logged in user"
			// to next required method which accepts generated session ID
			// without parsing json response and extracting session ID
			
			//Need to use filter(SessionFilterVeriableName) before given()
			
			.when().log().all().post("rest/auth/1/session")
			
			.then().log().all().extract().response().asString();
			
			// create session ID
			
			//SessionID = Utilities.SessionID(SessionIDPOST,sessionIDPath);
			
			//System.out.println(SessionID);
			 
					given()
			.pathParam("ID", "10202")
			.header("Content-type","application/json")
			//.header("Cookie",SessionID)
			.body("{\n" + 
					"    \"body\": \"Adding comment to the issue\",\n" + 
					"    \"visibility\": {\n" + 
					"        \"type\": \"role\",\n" + 
					"        \"value\": \"Administrators\"\n" + 
					"    }\n" + 
					"}")
			.filter(session)
			.when().log().all()
			.post("rest/api/2/issue/{ID}/comment")
			.then().log().all().assertThat().statusCode(201);
			
			// Creating issue from generated session ID
			
			/*String CreattIssuePost = given().log().all().
					
					header("Content-type","application/json")
					
					.header("Cookie",SessionID)
					
					.body(Utilities.createIssue())
					
					.when().post("rest/api/2/issue")
					
					.then().log().all().extract().response().asString();
			
			String IssueID = Utilities.SessionID(CreattIssuePost, "id");
			
			System.out.println(IssueID);*/
			
			
			
		
		
		/*given().log().all().pathParam("issueId", "12345")
		
		.header("Content-Type", "application/json")
		
		.body(Utilities.addCommentJiraBoday("adding a comment"))
		
		.when().post("rest/api/2/issue/{issueId}/comment");*/
	}
}
