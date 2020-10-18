

import static io.restassured.RestAssured.*;

import java.util.ArrayList;

import Pojo.addPlaceResponse;
import Pojo.addPlaces;
import Pojo.addPlacesLocation;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class googleMapSerilization 
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
		
		addPlaceResponse response = given().queryParam("key", "qaclick123")
		.body(addPlacesObj)
		.when().post("/maps/api/place/add/json")
		.then().extract().response().as(addPlaceResponse.class);
		
		//addPlaceResponse obj = new addPlaceResponse();
		
		System.out.println("Place ID for nely added location is: "+response.getPlace_id());
		
		//System.out.println(response.asString());
	}
}
