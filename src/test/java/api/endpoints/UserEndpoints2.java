package api.endpoints;


import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.when;

import java.util.ResourceBundle;

import api.paylOad.User;


public class UserEndpoints2 {
	
	static ResourceBundle getURL(){
		ResourceBundle routes=ResourceBundle.getBundle("routes");
		return routes;
	}
	
	 public static Response createUser(User payload)
	{
		  String post_url=getURL().getString("post_url");
		Response rep=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.when()
		.post(post_url);
		return rep;
		
	}
	 
	 
	 public static Response readUser(String userName)
		{
		  String get_url=getURL().getString("get_url");

			Response rep=given()
			.pathParam("username",userName)
			.when()
			.get(get_url);
			return rep;
			
		}
	 public static Response updateUser(String userName,User payload)
		{
		  String update_url=getURL().getString("update_url");

			Response rep=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username",userName)
			.body(payload)
			.when()
			.put(update_url);
			return rep;
			
		}
	 public static Response deleteUser(String userName)
		{
		  String delete_url=getURL().getString("delete_url");

			Response rep=given()
			.pathParam("username",userName)
			.when()
			.delete(delete_url);
			return rep;
			
		}
	

}
