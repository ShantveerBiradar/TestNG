	package Test_Classes;

	import org.testng.Assert;
import org.testng.annotations.Test;

import Common_API_Methods.API_Methods_Patch;
	import RequestRepository.Patch_Req_Repository;
	import io.restassured.path.json.JsonPath;

	public class Patch_TC1 {
		@Test
	public static void extractor () {
	int statusCode = 
	API_Methods_Patch.ResponseStatusCode(Patch_Req_Repository.BaseURI(), Patch_Req_Repository.Patch_Resource(), Patch_Req_Repository.Patch_Req_TC1());
	System.out.println(statusCode);
	
	String ResponseBody = 
	API_Methods_Patch.ResponseBody(Patch_Req_Repository.BaseURI(), Patch_Req_Repository.Patch_Resource(), Patch_Req_Repository.Patch_Req_TC1());
	System.out.println(ResponseBody);
	
	JsonPath JspResponse = new JsonPath(ResponseBody);
	String Res_name = JspResponse.getString("name");
	String Res_job = JspResponse.getString("job");
	
	Assert.assertEquals(Res_name,"morpheus");
	Assert.assertEquals(Res_job,"leader"); 
	
	}

}
  