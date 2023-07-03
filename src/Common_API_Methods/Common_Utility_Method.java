package Common_API_Methods;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class Common_Utility_Method {

	public static void EvidenceCreater(String Filename,String RequestBody, String ResponseBody, int StatusCode) throws IOException {
		File TextFile = new File ("F:\\MS_SQUARE\\ASSIGNMENT\\RestAssureed Assignment\\Evidence\\"+Filename+".txt");
		System.out.println("New blank text file of name : " + TextFile.getName());
		
		FileWriter dataWrite = new FileWriter(TextFile);
		
		dataWrite.write("Request Body is : " +RequestBody +"\n\n");
		dataWrite.write ("Status code is : "+ StatusCode + "\n\n");
		dataWrite.write ("Response Body is : " + ResponseBody);
		
		dataWrite.close();
		System.out.println("Request body and response body written in textfile : " + TextFile.getName());	
	}
	public static ArrayList<String> ReadDataExcel(String SheetName , String TestCaseName) throws IOException 
	{
		ArrayList<String> ArrayData = new ArrayList<String>();
		
		//create the object of the file input stream to locate the excel file
		FileInputStream Fis = new FileInputStream("F:\\MS_SQUARE\\RestAssured\\Excel1.xlsx");
		
		//Open the excel file by creating the object XSSFWorkBook
		XSSFWorkbook WorkBook = new XSSFWorkbook(Fis); 
		
		//open the desired sheet count of sheet 
		int countofsheet = WorkBook.getNumberOfSheets();
		for(int i=0; i<countofsheet; i++) {
		String Sheetname = WorkBook.getSheetName(i);
		//Access the desired sheet 
			if(Sheetname.equalsIgnoreCase(Sheetname)) {
				//use XSSFSheet t save the sheet into a variable 
				XSSFSheet Sheet = WorkBook.getSheetAt(i);
				//create the Iterator through row and find out in which column the test case name is found
				Iterator<Row> Rows =Sheet.iterator();
				Row FirstRow =Rows.next();
				//Create the iterator to iterated through the cells of 1st Row to find out which cell contains test case name 
				Iterator<Cell> CellOfFirstRow = FirstRow.cellIterator();
						int k = 0;
					int TC_column = 0;
					while(CellOfFirstRow.hasNext())
					{
						Cell CellValue = CellOfFirstRow.next();
						if(CellValue.getStringCellValue().equalsIgnoreCase("TestCaseName"))
						{
							TC_column = k;
							System.out.println("Expected column for test case name :" +k);
							break;
						}
						k++;
						}
            		//verify the row where the desired test case is found and fetch the entire row
		     		while(Rows.hasNext())
		     		{
		     			Row DataRow =Rows.next();	 
		     			String TCName = DataRow.getCell(TC_column).getStringCellValue();
		     			if(TCName.equalsIgnoreCase(TestCaseName))
		     			{
		     				Iterator<Cell> CellValues =DataRow.cellIterator();
		     				while(CellValues.hasNext())
		     				{
		        		  String Data=CellValues.next().getStringCellValue();
		        		  ArrayData.add(Data);
		     				}
		     				break;
		     			}
		     		}
					}
		}
		return ArrayData;
}
}