package scripts.ddt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class dataProviders {

	
	@DataProvider (name="dataFromXls")
	  public Object[][] providesDataFromXls() {
		  String [] [] tabArray = readDataFromXLS("test/resouses/TestData/projecttasks.txt", "NT", "NTStartEnd");
		  return tabArray;
	  }
	  
	  public String[][] readDataFromXLS(String xlFilePath, String sheetName, String marker) {
			String[][] tabArray = null;
			try {
				Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
				// Workbook class is provied by jxl.jar
				// WebDriver provided by Selenium
				// File is class provided by Java to read a physical file
				Sheet sheet = workbook.getSheet(sheetName);
				Cell tableStart = sheet.findCell(marker);

				int startRow, startCol, endRow, endCol, ci, cj;

				startRow = tableStart.getRow();// 2
				startCol = tableStart.getColumn();// 1

				Cell tableEnd = sheet.findCell(marker, startCol + 1, startRow + 1, 100, 64000, false);

				endRow = tableEnd.getRow();// 7
				endCol = tableEnd.getColumn();// 5
				System.out.println("startRow=" + startRow + ", endRow=" + endRow + ", " + "startCol=" + startCol
						+ ", endCol=" + endCol);
				tabArray = new String[endRow - startRow - 1][endCol - startCol - 1];// 4, 3
				ci = 0; // array row
				// ci=0,i=3, j=3,cj=1
				for (int i = startRow + 1; i < endRow; i++, ci++) {// i represents
																	// xls row
					cj = 0;// array column
					for (int j = startCol + 1; j < endCol; j++, cj++) {// j
																		// represents
																		// xls
																		// column
						tabArray[ci][cj] = sheet.getCell(j, i).getContents();
					}
				}
			} catch (FileNotFoundException e) {
				System.out.println("The file you specified does not exist");
			} catch (Exception e) {
				System.out.println("Please check if file path, sheet name and tag name are correct");
				e.toString();

			}

			return (tabArray);
		}
	  
	  @DataProvider(name = "dataFromCSV")
		public Iterator providesDataFromCSV() throws IOException {
			Collection<String[]> returnData = getTestDataFromCSV("src/test/resources/TestData/products.csv");
			return returnData.iterator();
		}
	  
	  
	  
	  public static Collection<String[]> getTestDataFromCSV(String fileName) throws IOException {
			List<String[]> records = new ArrayList<String[]>(); // arraylist of array of string is two dim arraylist

			String record;
			// FileReader is meant for reading streams of characters(reading text files)
			// FileReader myReader = new FileReader(fileName);
			// A BufferedReader object takes a FileReader object as
			// an input which contains all the necessary information
			// about the text file that needs to be read.
			BufferedReader file = new BufferedReader(new FileReader(fileName));
			while ((record = file.readLine()) != null) {// record = "niche,thyself"
				String fields[] = record.split(",");// split() method is inside String class
				// fields[0] = "niche", and fields[1] = "thyself"
				records.add(fields);
			}
			file.close();
			return records;
		}

	  @DataProvider
	  public Object[][] dp() {
	    return new Object[][] {
	      new Object[] { 1, "a" },
	      new Object[] { 2, "b" },
	    };
	
}
}
