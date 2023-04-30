package api.test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.endpoints.UserEndpoints2;
import api.paylOad.User;

import io.restassured.response.Response;

public class UserTest2 {

	Faker faker;
	User userpayload;
	public Logger logger;

	@BeforeClass
	public void setup() {
		faker = new Faker();
		userpayload = new User();
		userpayload.setId(faker.idNumber().hashCode());
		userpayload.setUsername(faker.name().username());
		userpayload.setFirstname(faker.name().firstName());
		userpayload.setLastname(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		userpayload.setPwd(faker.internet().password(5, 10));
		userpayload.setPhone(faker.phoneNumber().cellPhone());

		// logs

		logger = LogManager.getLogger(this.getClass());
		logger.debug("debugging");
	}

	@Test(priority = 1)
	public void testpostuser() {
		logger.info("============creating user========");
		Response response = UserEndpoints2.createUser(userpayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("============user created===========");
	}

	@Test(priority = 2)

	public void testGetUserByName() {
		logger.info("==========reading user info=========");
		Response response = UserEndpoints2.readUser(this.userpayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("========== user info is displayed=========");

	}

	@Test(priority = 3)
	public void testUpdateUserByName() {
		logger.info("==========updating user=========");

		userpayload.setFirstname(faker.name().firstName());
		userpayload.setLastname(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());

		Response response = UserEndpoints2.updateUser(this.userpayload.getUsername(), userpayload);
		response.then().log().body().statusCode(200);
		Assert.assertEquals(response.getStatusCode(), 200);

		Response responseAfterupdate = UserEndpoints.readUser(this.userpayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(responseAfterupdate.getStatusCode(), 200);
		logger.info("==========Updated user info diaplayed=========");

	}

	@Test(priority = 4)
	public void testDeleteUserByname() {
		logger.info("==========deleting user=========");

		Response response = UserEndpoints2.deleteUser(this.userpayload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("========== user deleted=========");

	}
}
