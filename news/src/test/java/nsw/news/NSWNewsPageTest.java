package nsw.news;

import org.testng.annotations.Test;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.print.DocFlavor.STRING;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import io.github.bonigarcia.wdm.WebDriverManager;
import nsw.news.ExcelReadUtil;

public class NSWNewsPageTest {

	static WebDriver d1;
	 public static Assertion hardAssert = new Assertion();
	 public static SoftAssert softAssert = new SoftAssert();
public static String javaScript = "var evObj = document.createEvent('MouseEvents');" +
         "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);" +
           "arguments[0].dispatchEvent(evObj);";
  @SuppressWarnings("deprecation")
 
  
  public static void main (String [] args) throws IOException{
	//Path of the excel file
	FileInputStream fs = new FileInputStream("D:\\DemoFile.xlsx");
	//Creating a workbook
	XSSFWorkbook workbook = new XSSFWorkbook(fs);
	XSSFSheet sheet = workbook.getSheetAt(0);
	Row row = sheet.getRow(0);
	Cell cell = row.getCell(0);
	System.out.println(sheet.getRow(0).getCell(0));
	Row row1 = sheet.getRow(1);
	Cell cell1 = row1.getCell(1);
	System.out.println(sheet.getRow(0).getCell(1));
	Row row2 = sheet.getRow(1);
	Cell cell2 = row2.getCell(1);
	System.out.println(sheet.getRow(1).getCell(0));
	Row row3 = sheet.getRow(1);
	Cell cell3 = row3.getCell(1);
	System.out.println(sheet.getRow(1).getCell(1));
  
  
  
  @Test(dataProvider = "input-data-provider")
  public void f() {
	  WebDriverManager.chromedriver().setup();
	  d1 = new ChromeDriver();
	  d1.get("https://www.nsw.gov.au/news");
	    d1.manage().window().maximize();

d1.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
d1.findElement(By.xpath("//fieldset[@id='edit-fieldset-category']//button[@type='button']")).click();
d1.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


int Topic = 0;
switch(Topic) {
case 1:
	WebElement topic1 = d1.findElement(By.xpath("//label[contains(text(), 'Adult and Community Education')]"));
	topic1.click();
	boolean b1 = topic1.isSelected();
	if (!b1) {
		System.out.println("The filter is not applied correctly");
	}
	else { System.out.println("Adult and Community Education filter has been applied!!");
}
	break;

case 2:
	WebElement topic2 = d1.findElement(By.xpath("//label[contains(text(), 'Business and Economy')]"));
	topic2.click();
	boolean b2 = topic2.isSelected();
	if (!b2) {
		System.out.println("The filter is not applied correctly");
	}
	else { System.out.println("Business and Economy filter has been applied!!");
}
	break;
  case 3:
		WebElement topic3 = d1.findElement(By.xpath("//label[contains(text(), 'Business and Economy')]"));
		topic3.click();
		boolean b3 = topic3.isSelected();
		if (!b3) {
			System.out.println("The filter is not applied correctly");
		}
		else { System.out.println("Business and Economy filter has been applied!!");
	}
		break;
	}
  }
  
  @DataProvider(name="input-data-provider")
	public int[] userinputDataProvider() 
	{
	  NSWNewsPageTest objExcelFile = new NSWNewsPageTest();
	  objExcelFile.readExcel("./topics.xlsx","input",1);
	}
}
