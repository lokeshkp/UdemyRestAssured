package utilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ReusableMethods {

	public static XmlPath rawToXML(Response r){

		String respon=r.asString();
		XmlPath x=new XmlPath(respon);
		return x;

	}


	public static JsonPath rawToJson(Response r){ 
		String respon=r.asString();
		JsonPath x=new JsonPath(respon);
		return x;
	}
	
	
	// the method will convert json object into bytes format
	
		public static String convertStringFromJson(String filePath) throws IOException {
			
			return new String(Files.readAllBytes(Paths.get(filePath)));
			
		}

}
