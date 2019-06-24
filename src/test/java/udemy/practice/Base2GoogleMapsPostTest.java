package udemy.practice;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utilities.GoogleAPIPayload;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class Base2GoogleMapsPostTest {

	Properties prop = new Properties();
	String placeId="";
	String baseURL ="";
	String addResource ="";
	String delResource ="";
	String keyVal = "";
	
	@BeforeTest
	public void getData() throws IOException {
		
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//eclipse-workspace//UdemyRestAssured//src//test//java//config//env.properties");
		prop.load(fis);
		
		baseURL = prop.getProperty("Loc_HOST");
		addResource = prop.getProperty("addReso");
		delResource = prop.getProperty("delReso");
		keyVal = prop.getProperty("key");
		
	}
	
	
	@Test
	public void postTest() throws InterruptedException {

		RestAssured.baseURI = baseURL;



		Response res = 
			given().
				queryParam("key", keyVal).
				body(GoogleAPIPayload.getPostData()).
			when().
					post(addResource).
			then().statusCode(200).extract().response();

		String response = res.asString();
		System.err.println(response);

		JsonPath js = new JsonPath(response);
		System.out.println(js.get("place_id"));
		placeId = js.get("place_id");

		
		
		// Deleting place 
		RestAssured.baseURI = baseURL;

		Response delRes = 
			given().
				queryParam("key",keyVal).
				body("{"+"\"place_id\":\""+ placeId+"\""+"}").
			when().
				post(delResource).
			then().statusCode(200).extract().response();
		
		System.out.println(delRes.asString());

	}


}
