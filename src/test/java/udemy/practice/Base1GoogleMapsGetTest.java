package udemy.practice;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Base1GoogleMapsGetTest {
	
	@Test
	public void GoogleMapsGetTest() throws FileNotFoundException, IOException, ParseException {
		
		RestAssured.baseURI = "https://maps.googleapis.com/";
		
		Response res =
		
		given().
			param("location","16.989065,82.247465").
			param("radious","500").
			param("key","AIzaSyB54Wn3XTAF1OqpsEKPOejcTxZKS258pUo").log().all().
		when().
			get("/maps/api/place/nearbysearch/json").
		
		then().
			assertThat().statusCode(200).and().contentType(ContentType.JSON).and().extract().response();
		
		System.out.println(res.asString());
		
		//body("results[1].name", equalTo("Bank of India"));
		

	}

}
