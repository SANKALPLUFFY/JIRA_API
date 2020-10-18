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
	
	public static String arraySize(String json, String path, String recentID)
	{
		JsonPath js1 = new JsonPath(json);
		
		String recentComment = null;
		
		int sessinIDSize = js1.getInt(path+".size()");
		
		for(int i=0;i<sessinIDSize;i++)
		{
			String ID = js1.get(path+"["+i+"].id").toString();
			
			System.out.println(ID);
			
			if(ID.contentEquals(recentID))
			{
				recentComment = js1.get(path+"["+i+"].body").toString();
			}
		}
		
		return recentComment;
	}

}


