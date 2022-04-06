package nsw.news;
import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class ExcelReadUtil {
//public static void main (int [] args) throws IOException{
	  public static int readExcel(String excelFilePath, String sheetName, int totalCols) {
		  int topic=0;
		  try  
		  {  
		  File file = new File("C:\\Users\\ssharma5\\news\\topics.xlsx");   //creating a new file instance  
		  FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file  
		  //creating Workbook instance that refers to .xlsx file  
		  XSSFWorkbook wb = new XSSFWorkbook(fis);   
		  XSSFSheet sheet = wb.getSheetAt(0);     //creating a Sheet object to retrieve object  
		  Iterator<Row> itr = sheet.iterator();    //iterating over excel file  
		  while (itr.hasNext())                 
		  {  
		  Row row = itr.next();  
		  Iterator<Cell> cellIterator = row.cellIterator();   //iterating over each column  
		  while (cellIterator.hasNext())   
		  {  
		  Cell cell = cellIterator.next();  
		  System.out.print((int)cell.getNumericCellValue() + "\t\t\t");    
		  topic = (int)cell.getNumericCellValue();
		  }  
		  System.out.println("");  
		  }  
		  }  
		  catch(Exception e)  
		  {  
		  e.printStackTrace();  
		  }  
		  return topic;
		  
		  }  
}
