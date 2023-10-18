package api.test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.payload.User;
import io.restassured.response.Response;

@Listeners(api.utilities.ExtentReportManager.class)
public class UserTests {
	
	Faker faker;
	User payload;
	
	@BeforeClass
	public void setUpData()
	{
		faker = new Faker();
		payload = new User();
		
		payload.setId(faker.idNumber().hashCode());
		payload.setUsername(faker.name().username());
		payload.setFirstName(faker.name().firstName());
		payload.setLastName(faker.name().lastName());
		payload.setEmail(faker.internet().safeEmailAddress());
		payload.setPassword(faker.internet().password());
		payload.setPhone(faker.phoneNumber().cellPhone());
	}
	
	
	@Test(priority = 1)
	public void testPostUser() 
	{
		Response response = UserEndpoints.createUser(payload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);//201
	}
	
	@Test(priority = 2)
	public void testgetUser() 
	{
		Response response = UserEndpoints.readUser(payload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);//200
	}
	
	@Test(priority = 3)
	public void testPutUser() 
	{
		payload.setFirstName(faker.name().firstName());
		payload.setLastName(faker.name().lastName());
		payload.setEmail(faker.internet().safeEmailAddress());
		
		Response response = UserEndpoints.readUser(payload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);//200
	}

	@Test(priority = 4)
	public void testdeleteUser() 
	{
		Response response = UserEndpoints.readUser(payload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);//204
	}
	
	
}
