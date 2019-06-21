package udemy.practice;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import utilities.ReusableMethods;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class ConvertJsonToStringTest {
	
	@Test
	public void addBooks() throws IOException {
		
		RestAssured.baseURI = "http://216.10.245.166";
		
		given().
			header("Content-Type", "application/json").log().all().
			body(ReusableMethods.convertStringFromJson("//Users//lokesh//eclipse-workspace//UdemyRestAssured//src//test//java//testData//addBook.json")).
		when().
			post("/Library/Addbook.php").
		then().assertThat().statusCode(200);
	}

}
