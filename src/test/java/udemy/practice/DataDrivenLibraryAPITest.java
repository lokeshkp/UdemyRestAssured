package udemy.practice;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utilities.Payload;

import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.*;

public class DataDrivenLibraryAPITest {
	
	private org.apache.logging.log4j.Logger log = LogManager.getLogger(DataDrivenLibraryAPITest.class.getName());
	
	@Test
	public void testLog4J() {
		log.debug("Rest Assured Debugging..");
		
		log.info("Checking log4j test info");
		log.error("Checking log4j error");
		log.fatal("Checking log4j fatal");
		
	}
	
	@Test(dataProvider="BooksData")
	public void addBooks(String bookName, String bookId) {
		
		RestAssured.baseURI = "http://216.10.245.166";
		
		Response respo = given().
			header("Content-Type", "application/json").
			body(Payload.AddBook(bookName,bookId)).
		when().
			post("/Library/Addbook.php").
		then().assertThat().statusCode(200).extract().response();
		
		log.info(respo.asString());
		
		JsonPath js = new JsonPath(respo.asString());
		
		log.info(js.get("ID"));
	}
	
	
	
	@Test(dataProvider="BooksData")
	public void deleteBooks(String bookName, String bookId) {
		RestAssured.baseURI = "http://216.10.245.166";
		
		Response res = given().
			header("Content-Type","application/json").
			body("{\n" + 
				"\"ID\" : \""+bookName+bookId+"\"\n" + 
				"}").
		when().
			post("/Library/DeleteBook.php").
		then().assertThat().statusCode(200).extract().response();
		
		JsonPath js = new JsonPath(res.asString());
		
		log.info(js.get("msg"));
	}
	
	
	
	@DataProvider(name ="BooksData")
	public Object[][] getData(){
		return new Object[][] {{"hanumRama","872"},{"LaxmanBharat","324"},{"RadheKrishna","545"}};
	}
	

}
