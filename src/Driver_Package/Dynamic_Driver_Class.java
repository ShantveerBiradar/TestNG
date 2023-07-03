package Driver_Package;

	import java.io.IOException;
	import java.lang.reflect.InvocationTargetException;
	import java.lang.reflect.Method;
	import java.util.ArrayList;
	import Common_API_Methods.Common_Utility_Method2;

	public class Dynamic_Driver_Class {
		
		public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
			ArrayList<String> TestCasetoRun = Common_Utility_Method2.ReadDataExcel("DataDriven", "Tc2Execute");
			System.out.println(TestCasetoRun);
			int count=TestCasetoRun.size();
			for (int i=1; i<count; i++) 
			{
			String TestCaseName=TestCasetoRun.get(i);
			System.out.println("Test Case to Execute is :"+TestCaseName+".");
			// call the test case class on runtime by using java.lang.reflect package
			Class<?> testclassname=Class.forName("Test_Classes."+TestCaseName);
			// call the execute method belonging to test class captured in variable test class name by using java.lang.reflect.method class
			Method executemethod=testclassname.getDeclaredMethod("extractor");
			//set the accessibility of method true 
			executemethod.setAccessible(true);
			//create the instance of test class captured in variable name test class name
			Object instanceoftestclass=testclassname.getDeclaredConstructor().newInstance();
			// execute the test class captured in variable name test class name
			executemethod.invoke(instanceoftestclass);
			}
			}
			}
 