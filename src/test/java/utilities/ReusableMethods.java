package utilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ReusableMethods {

	public static XmlPath rawToXML(Response res){

		String respon=res.asString();
		XmlPath xp=new XmlPath(respon);
		return xp;

	}


	public static JsonPath rawToJson(Response res){ 
		String respon=res.asString();
		JsonPath jp=new JsonPath(respon);
		return jp;
	}
	
	
	// the method will convert json object into bytes format
	
		public static String convertStringFromJson(String filePath) throws IOException {
			
			return new String(Files.readAllBytes(Paths.get(filePath)));
			
		}

}
