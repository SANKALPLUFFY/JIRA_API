package testCases;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;

import Pojo.addPlaceResponse;
import Pojo.addPlaces;
import Pojo.addPlacesLocation;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class specBuilder 
{

	public static void main(String a[])
	{
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		addPlaces addPlacesObj = new addPlaces();
		
		ArrayList<String> list = new ArrayList<String>();
		
		addPlacesLocation addPlacesLocationObj = new addPlacesLocation();
		
		addPlacesLocationObj.setLat(-58.383494);
		
		addPlacesLocationObj.setLng(43.427362);
		
		list.add("one");
		
		list.add("two");
		
		addPlacesObj.setAccuracy(52);
		
		addPlacesObj.setLanguage("Russian-IN");
		
		addPlacesObj.setWebsite("http://facebook.com/");
		
		addPlacesObj.setAddress("sadasadasdas ads zzx bvbv");
		
		addPlacesObj.setPhone_number("1234567850");
		
		addPlacesObj.setName("John Cena");
		
		addPlacesObj.setTypes(list);
		
		addPlacesObj.setLocation(addPlacesLocationObj);
		
		// Object return type for RequestSpecBuilder is RequestSpecification
		
		RequestSpecification spec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123").build();
		
		ResponseSpecification Resp = new ResponseSpecBuilder().expectStatusCode(200).build();
		
		RequestSpecification req = given().spec(spec)
		.body(addPlacesObj);
		
		Response response = 
		req.when().post("/maps/api/place/add/json")
		.then().spec(Resp).extract().response();
		
		addPlaceResponse Response = response.as(addPlaceResponse.class);
		
		//addPlaceResponse response //as(addPlaceResponse.class)
		
		System.out.println("Place ID for nely added location is: "+Response.getPlace_id());
		
		//System.out.println(response.asString());
	}
}
