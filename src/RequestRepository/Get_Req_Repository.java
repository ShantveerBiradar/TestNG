package RequestRepository;

public class Get_Req_Repository {
	
	public static String BaseURI() {
		 String baseURI = "https://reqres.in/";
		 return baseURI;	 
	}
	public static String Get_Resource() {
		String get_Resource = "api/users?page=2";
		return get_Resource;
	}

}
