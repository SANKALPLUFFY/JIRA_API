package Utilities;

import io.restassured.path.json.JsonPath;

public class Utilities 
{	
	
	public static String addCommentJiraBoday(String comment)
	{
		String body = "{\r\n" + 
				"		    \"body\":\r\n"+comment+" ,\r\n" + 
				"		    \"visibility\": {\r\n" + 
				"		        \"type\": \"role\",\r\n" + 
				"		        \"value\": \"Administrators\"\r\n" + 
				"		    }\r\n" + 
				"		}";
		
		return body;
	}
	
	public static String loginToJira(String userName, String Password)
	{
		String body = "{\r\n" + 
				"    \"username\": \r\n"+userName+",\r\n" + 
				"    \"password\": \r\n"+Password+"\r\n" + 
				"}";
		
		return body;
	}
	
	public static String SessionID(String json, String field)
	{
		JsonPath js = new JsonPath(json);
		
		String sessinID = js.get(field);
		
		return sessinID;
	}

}


