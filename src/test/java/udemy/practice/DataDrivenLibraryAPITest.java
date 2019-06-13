package udemy.practice;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utilities.Payload;

import static io.restassured.RestAssured.given;


public class DataDrivenLibraryAPITest {
	
	
	@Test(dataProvider="BooksData")
	public void addBooks(String bookName, String bookId) {
		
		RestAssured.baseURI = "http://216.10.245.166";
		
		Response respo = given().
			header("Content-Type", "application/json").
			body(Payload.AddBook(bookName,bookId)).
		when().
			post("/Library/Addbook.php").
		then().assertThat().statusCode(200).extract().response();
		
		System.out.println(respo.asString());
		
		JsonPath js = new JsonPath(respo.asString());
		
		System.err.println(js.get("ID"));
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
		
		System.err.println(js.get("msg"));
	}
	
	
	
	@DataProvider(name ="BooksData")
	public Object[][] getData(){
		return new Object[][] {{"hanumRama","872"},{"LaxmanBharat","324"},{"RadheKrishna","545"}};
	}
	

}
