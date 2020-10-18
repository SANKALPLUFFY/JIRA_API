package testCases;


import Utilities.Utilities;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertTrue;

import java.io.File;

import org.testng.Assert;
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
			
			/*Adding comment to existing jira*/
			
			String comment = "Update six";
			 
				String CommentID=	
						given()
			.pathParam("ID", "10202")
			.header("Content-type","application/json")
			//.header("Cookie",SessionID)
			.body("{\n" + 
					"    \"body\": \""+comment+"\",\n" + 
					"    \"visibility\": {\n" + 
					"        \"type\": \"role\",\n" + 
					"        \"value\": \"Administrators\"\n" + 
					"    }\n" + 
					"}")
			.filter(session)
			.when().log().all()
			.post("rest/api/2/issue/{ID}/comment")
			.then().log().all().extract().asString();
				
				String commentID = Utilities.SessionID(CommentID, "id");
			
			
			// Adding attachment using "multiPart" restAssured method
					
			// We are using post method to add attachment without any json body using curl statement for attachment
			// So while sending header we need to give header as ("Content-Type","multipart/form-data")
					
			given()
			.pathParam("ID", "10202")
			.header("X-Atlassian-Token","no-check")
			.header("Content-Type","multipart/form-data")
			.filter(session).multiPart("file", new File(System.getProperty("user.dir")+"\\src\\test\\resources\\JIRAAttachments\\attachment.txt"))
			.when().post("rest/api/2/issue/{ID}/attachments")
			.then().log().all().assertThat().statusCode(200);
			
			// Get isssue details
			
			String issueDetails = 
					given()
			.pathParam("ID","10202")
			.queryParam("fields", "comment")
			.filter(session)
			.when().get("rest/api/2/issue/{ID}")
			.then().log().all().extract().response().asString();
			
			
			String fetchedRecentComment = Utilities.arraySize(issueDetails, "fields.comment.comments", commentID);
			
			if(fetchedRecentComment.equalsIgnoreCase(comment))
			{
				System.out.println("Pass");
				
				System.out.println("RecentComment is:"+" "+comment +" , "+"and fetched comment is:"+" "+fetchedRecentComment);
			}
			else
			{
				Assert.assertTrue(false, "Wrong comment");
			}
			
			
	}
}
