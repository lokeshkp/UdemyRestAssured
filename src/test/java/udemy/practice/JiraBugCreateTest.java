package udemy.practice;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utilities.ReusableMethods;

public class JiraBugCreateTest {
	
	String baseURL = "";
	String sessionId ="";
	String bugId ="";
	String commentId ="";
	
	// Session ID genrating
	@BeforeTest
	public String jiraAuthentication() throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("//Users//lokesh//eclipse-workspace//UdemyRestAssured//src//test//java//config//env.properties");
		prop.load(fis);
		
		baseURL = prop.getProperty("Jira_Host");
	
		RestAssured.baseURI = baseURL ;
		
		Response resp = given().
			header("Content-Type", "application/json").
			body("{ \"username\": \"kplokesh\", \"password\": \"sairam123\" }").
		when().
			post("/rest/auth/1/session").
		then().
			statusCode(200).extract().response();
		
		
		JsonPath js = ReusableMethods.rawToJson(resp);
		sessionId = js.get("session.value");
		
		return sessionId;
	}
	
	
	// Creating Bug in Jira via Rest API
	@Test()
	public void createJiraBug() throws IOException {
		
		RestAssured.baseURI = baseURL ;
		
		Response resp = given().
			header("Content-Type", "application/json").header("Cookie","JSESSIONID="+sessionId+"").
			body(ReusableMethods.convertStringFromJson("//Users//lokesh//eclipse-workspace//UdemyRestAssured//src//test//java//testData//createJiraBug.json")).
		when().
			post("/rest/api/2/issue").
		then().
			statusCode(201).extract().response();
		

		JsonPath js = ReusableMethods.rawToJson(resp);
		bugId = js.get("id");
	}
	
	
	
	// Adding comment to above created Jira Bug
	
	@Test(dependsOnMethods = { "createJiraBug" })
	public void addCommentToBug() {
		
		RestAssured.baseURI = baseURL;
		
		Response res = given().header("Content-Type", "application/json").header("Cookie","JSESSIONID="+sessionId+"").
			body("{\n" + 
				"    \"body\": \"This is a first comment to add my existing bug\",\n" + 
				"    \"visibility\": {\n" + 
				"        \"type\": \"role\",\n" + 
				"        \"value\": \"Administrators\"\n" + 
				"    }\n" + 
				"}").
		when().post("/rest/api/2/issue/"+bugId+"/comment/").
		//when().post("rest/api/2/issue/10100/comment/").
		
		then().statusCode(201).extract().response();
		
		JsonPath js = ReusableMethods.rawToJson(res);
		commentId = js.get("id");
		System.err.println(js.get("updateAuthor.name"));

	}
	
	
	
	// Updating above comment 
	//@Test(dependsOnMethods = { "createJiraBug", "addCommentToBug" })
	public void updateComment() {
				
		String resource = "/rest/api/2/issue/"+bugId+"/comment/"+commentId+"";
		//String resource = "/rest/api/2/issue/10119/comment/10118/";
				
		RestAssured.baseURI = baseURL;
		
		Response res = given().header("Content-Type", "application/json").header("Cookie","JSESSIONID="+sessionId+"").
				body("{\n" + 
						"    \"body\": \"This is a updating comment to my existing comment 14-June-2019\",\n" + 
						"    \"visibility\": {\n" + 
						"        \"type\": \"role\",\n" + 
						"        \"value\": \"Administrators\"\n" + 
						"    }\n" + 
						"}").
				when().put("resource").
				then().statusCode(200).extract().response();
		
		System.out.println(res.asString());
				
	}
	
	
	// Deleting first created Jira Bug
	
	@Test(dependsOnMethods = { "createJiraBug", "addCommentToBug" })
	public void deleteBug() throws InterruptedException {
		
		Thread.sleep(20000);
		RestAssured.baseURI = baseURL;
		
		given().header("Content-Type", "application/json").header("Cookie","JSESSIONID="+sessionId+"").
			when().delete("/rest/api/2/issue/"+bugId+"").
			then().statusCode(204);
	}

}
