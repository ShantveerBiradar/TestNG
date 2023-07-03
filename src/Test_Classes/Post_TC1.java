package Test_Classes;
import java.io.IOException;
import java.time.LocalDateTime;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common_API_Methods.API_Methods_Post;
import RequestRepository.Post_Req_Repository;
import io.restassured.path.json.JsonPath;

public class Post_TC1 {
	@Test
	public static void extractor () throws IOException {
		for(int i=0; i<=5; i++) {
		int statusCode = 
	API_Methods_Post.ResponseStatusCode(Post_Req_Repository.BaseURI(), Post_Req_Repository.Post_Resource(), Post_Req_Repository.Post_Req_TC1());
	System.out.println(statusCode);
	if(statusCode==201) {
	String ResponseBody =
	API_Methods_Post.ResponseBody(Post_Req_Repository.BaseURI(), Post_Req_Repository.Post_Resource(), Post_Req_Repository.Post_Req_TC1());
	System.out.println(ResponseBody);
	Post_TC1.Validator(Post_Req_Repository.Post_Req_TC1(), ResponseBody);
	break;
	}
	else
	{ 
		System.out.println("Statuscode is failed");
	}
	
	}
	
	}
	public static void Validator(String RequestBody, String ResponseBody) throws IOException {
		// validate
				JsonPath JspRequest = new JsonPath(Post_Req_Repository.Post_Req_TC1());
				String Req_name = JspRequest.getString("name");
				String Req_job = JspRequest.getString("job");
				LocalDateTime currentdate = LocalDateTime.now();
				String expecteddate = currentdate.toString().substring(0, 11);
		// Create an object of JSON path to parse the response body
				JsonPath JspResponse = new JsonPath(ResponseBody);
				String Res_name = JspResponse.getString("name");
				String Res_job = JspResponse.getString("job");
				String Res_createdAt = JspResponse.getString("createdAt");
				Res_createdAt = Res_createdAt.substring(0, 11);
		// Validate the ResponseBody parameters
				Assert.assertEquals(Res_name, Req_name);
				Assert.assertEquals(Res_job, Req_job);
				Assert.assertEquals(Res_createdAt, expecteddate);
			}

}
    