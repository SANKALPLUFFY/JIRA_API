package Utilities;

import io.restassured.path.json.JsonPath;

public class Utilities 
{	
	
	public static String addCommentJiraBoday(String comment)
	{
		String body = "{\r\n" + 
				"		    \"body\":\""+comment+"\" ,\r\n" + 
				"		    \"visibility\": {\r\n" + 
				"		        \"type\": \"role\",\r\n" + 
				"		        \"value\": \"Administrators\"\r\n" + 
				"		    }\r\n" + 
				"		}";
		
		return body;
	}
	
	public static String loginToJira(String UserName, String Password) 
	{
		String body = "{\r\n" + 
				"    \"username\": \""+UserName+"\",\r\n" + 
				"    \"password\": \""+Password+"\"\r\n" + 
				"}";
		
		return body;
	}
	
	public static String createIssue()
	{
		String s = "    { \"fields\": {\n" + 
		  		"            \"project\":  {\n" + 
		  		"                \"key\": \"JIR\"\n" + 
		  		"            },\n" + 
		  		"             \"summary\":\"something new...\",\n" + 
		  		"              \"description\":\"descriptionhgghg\",\n" + 
		  		"               \"issuetype\": {\n" + 
		  		"                \"name\":\"Bug\"\n" + 
		  		"            }\n" + 
		  		"       }\n" + 
		  		"    }\n" + 
		  		" ";
		
		return s;
	}
	
	public static String SessionID(String json, String field)
	{
		JsonPath js = new JsonPath(json);
		
		String sessinID = js.get(field);
		
		return sessinID;
	}

}


