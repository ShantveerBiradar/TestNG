package Test_Classes;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common_API_Methods.API_Methods_Put;
import RequestRepository.Put_Req_Repository;
import io.restassured.path.json.JsonPath;

public class Put_TC1 {
	@Test
	public static void extractor () {
		int statusCode = 
		API_Methods_Put.ResponseStatusCode(Put_Req_Repository.BaseURI(), Put_Req_Repository.Put_Resource(), Put_Req_Repository.Put_Req_TC1());
	System.out.println(statusCode);
	
	String ResponseBody = 
	API_Methods_Put.ResponseBody(Put_Req_Repository.BaseURI(), Put_Req_Repository.Put_Resource(), Put_Req_Repository.Put_Req_TC1());
	System.out.println(ResponseBody);
	
	JsonPath JspResponse = new JsonPath(ResponseBody);
	String Res_name = JspResponse.getString("name");
	String Res_job = JspResponse.getString("job");
	
	
	//System.out.println(Res_name,);
	Assert.assertEquals(Res_name,"morpheus");
	Assert.assertEquals(Res_job,"leader");
	}

}

    