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
import java.util.HashMap;


public class ConvertJsonToStringTest {
	
	//@Test
	public void addBooks() throws IOException {
		
		RestAssured.baseURI = "http://216.10.245.166";
		
		given().
			header("Content-Type", "application/json").log().all().
			body(ReusableMethods.convertStringFromJson(System.getProperty("user.dir")+"//src//test//java//testData//addBook.json")).
		when().
			post("/Library/Addbook.php").
		then().assertThat().statusCode(200);
	}

	//payload details sending via HashMap
	
	@Test
	public void addBookFromHashMap() {
		HashMap<String, Object> hmap = new HashMap<String, Object>();
		hmap.put("name", "Selenium Book1");
		hmap.put("isbn","LaxmanaHanuma");
		hmap.put("aisle","14");
		hmap.put("author", "SAIRAM");
		
		RestAssured.baseURI = "http://216.10.245.166";
		
		given().header("Content-Type", "application/json").body(hmap).when().post("/Library/Addbook.php").then().assertThat().statusCode(200);
		
	}
}
