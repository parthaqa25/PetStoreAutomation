package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.*;

//UserEndPoints.java
//Created for to perform Create, Read, Update and Delete requests to the user API.

public class UserEndPoints2 {
	
	//method created for getting URL's from properties file
	static ResourceBundle getURL()
	{
		ResourceBundle routes=ResourceBundle.getBundle("routes");  //Load properties file (routes.properties) automatically
		return routes;                                            //and store into routes variable and return value
	}
	
	
	public static Response createUser(User payload)
	{
		String post_url=getURL().getString("post_url"); //calling getURL() method for get the URL from properties file
		
		
		
		Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				
				.when()
				    //.post(Routes.post_url); //get the URL from Routes.java
				    .post(post_url);          //get the URL from properties file
		
		       return response;
	}
	
	public static Response readUser(String userName)
	{
		String get_url=getURL().getString("get_url"); //calling getURL() method for get the URL from properties file
		
		
		Response response=given()
				            .pathParam("username", userName)
				
				.when()
				    //.get(Routes.get_url);  //get the URL from Routes.java
				    .get(get_url);           //get the URL from properties file
		
		       return response;
	}
	
	public static Response updateUser(String userName, User payload)
	{
		String update_url=getURL().getString("update_url"); //calling getURL() method for get the URL from properties file
		
		Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username", userName)
				.body(payload)
				
				.when()
				    //.put(Routes.update_url);   //get the URL from Routes.java
				    .put(update_url);            //get the URL from properties file
		
		       return response;
	}
	
	public static Response deleteUser(String userName)
	{
		String delete_url=getURL().getString("delete_url"); //calling getURL() method for get the URL from properties file
		
		Response response=given()
				            .pathParam("username", userName)
				
				.when()
				    //.delete(Routes.delete_url);   //get the URL from Routes.java
				    .delete(delete_url);            //get the URL from properties file
		
		       return response;
	} 

}
