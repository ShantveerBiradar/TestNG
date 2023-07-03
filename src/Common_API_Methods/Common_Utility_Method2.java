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

public class Common_Utility_Method2 {

	public static void EvidenceCreater(String Filename, String RequestBody, String ResponseBody, int StatusCode)
			throws IOException {
		File TextFile = new File("F:\\MS_SQUARE\\ASSIGNMENT\\RestAssureed Assignment\\Evidence\\" + Filename + ".txt");
		System.out.println("New blank text file of name : " + TextFile.getName());

		FileWriter dataWrite = new FileWriter(TextFile);

		dataWrite.write("Request Body is : "+ "\n\n" + RequestBody + "\n\n");
		dataWrite.write("Status code is : " + StatusCode + "\n\n");
		dataWrite.write("Response Body is : "+ "\n\n" + ResponseBody);

		dataWrite.close();
		System.out.println("Request body and response body written in textfile : " + TextFile.getName());
	}

	public static ArrayList<String> ReadDataExcel(String SheetName, String TestCaseName) throws IOException {
		ArrayList<String> ArrayData = new ArrayList<String>();

		// clear the object of the file input stream to locate the excel file
		FileInputStream Fis = new FileInputStream("F:\\MS_SQUARE\\RestAssured\\Excel2.xlsx");

		// Open the excel file by creating the object XSSFWorkBook
		XSSFWorkbook WorkBook = new XSSFWorkbook(Fis);

		// open the desired sheet count of sheet
		int countofsheet = WorkBook.getNumberOfSheets();
		for (int i = 0; i < countofsheet; i++) {
			String Sheetname = WorkBook.getSheetName(i);
			// Access the desired sheet
			if (Sheetname.equalsIgnoreCase(Sheetname)) {
				// Use XSSFSheet to save the sheet into a variable
				XSSFSheet Sheet = WorkBook.getSheetAt(i);
				// create iterators iterated thought row and find out in which column the test
				// case name found
				Iterator<Row> Rows = Sheet.iterator();
				Row FirstRow = Rows.next();
				// create the Iterator to Iterated though the cells of 1st Row to find out which
				// cell contains TestCase name
				Iterator<Cell> CellsOfFirstRow = FirstRow.cellIterator();
				int k = 0;
				int TC_column = 0;
				while (CellsOfFirstRow.hasNext()) {
					Cell CellValue = CellsOfFirstRow.next();
					if (CellValue.getStringCellValue().equalsIgnoreCase("TestCaseName")) {
						TC_column = k;
						// System.out.println("expected column for test case name:" +k);
						break;
					}
					k++;
				}
				// verify the row where the desired test case is found and fetch the entire row
				while (Rows.hasNext()) {
					Row Datarow = Rows.next();
					String TCName = Datarow.getCell(TC_column).getStringCellValue();
					// Datarow.getCell(TC_column).getNumericCellValue()
					if (TCName.equalsIgnoreCase(TestCaseName)) {
						Iterator<Cell> CellValues = Datarow.cellIterator();
						while (CellValues.hasNext()) {
							String Data = "";
							Cell CurrentCell = CellValues.next();
							try {
								String StringData = CurrentCell.getStringCellValue();
								Data = StringData;
							} catch (IllegalStateException e) {
								double doubledata = CurrentCell.getNumericCellValue();
								Data = Double.toString(doubledata);
							}

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
    