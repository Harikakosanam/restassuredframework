package api.endpoints;


import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.when;

import api.paylOad.User;


public class UserEndpoints {
	
	 public static Response createUser(User payload)
	{
		 
		Response rep=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.when()
		.post(Routes.post_url);
		return rep;
		
	}
	 
	 
	 public static Response readUser(String userName)
		{
			Response rep=given()
			.pathParam("username",userName)
			.when()
			.get(Routes.get_url);
			return rep;
			
		}
	 public static Response updateUser(String userName,User payload)
		{
			  
			Response rep=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username",userName)
			.body(payload)
			.when()
			.put(Routes.update_url);
			return rep;
			
		}
	 public static Response deleteUser(String userName)
		{
			Response rep=given()
			.pathParam("username",userName)
			.when()
			.delete(Routes.delete_url);
			return rep;
			
		}
	

}
