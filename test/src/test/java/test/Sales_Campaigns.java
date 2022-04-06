package test;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import javax.security.auth.login.FailedLoginException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Sales_Campaigns {

	static WebDriver d1;
	 public static Assertion hardAssert = new Assertion();
	 public static SoftAssert softAssert = new SoftAssert();
public static String javaScript = "var evObj = document.createEvent('MouseEvents');" +
         "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);" +
           "arguments[0].dispatchEvent(evObj);";
	


   @Test(dataProvider = "sales-demo-data-provider", priority =1)
	public static void login_backend(


			String UserName_BackEnd,
			String Password_BackEnd,
			String profileName,
			String maximize,
			String Environment) 
	// public static void login_backend() 
					throws InterruptedException, MalformedURLException, FailedLoginException {
			DesiredCapabilities caps = DesiredCapabilities.chrome();
			Reporter.log(
					"===============================================================");
			
			Reporter.log(Sales_Campaigns.class.getSimpleName());
			Reporter.log("Browser Name:" + caps.getBrowserName().toUpperCase());
			Reporter.log(Environment);
			Reporter.log("Browser Version:"+caps.getVersion().toString());
	
	Reporter.log(Environment);
			Reporter.log("Browser Version:"+caps.getVersion().toString());
			 WebDriverManager.chromedriver().setup();
			  d1 = new ChromeDriver();
			  d1.get("https://sales.springboard.com.au/jobtools/jncustomorglogin.userlogin?in_organid=17589");
			    d1.manage().window().maximize();

		d1.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);

						
	d1.findElement(By.xpath("//input[@name='in_username']")).sendKeys(UserName_BackEnd);
	d1.findElement(By.xpath("//input[@type='PASSWORD']")).sendKeys(Password_BackEnd);
		//d1.findElement(By.xpath("//input[@name='in_username']")).sendKeys("anna");
		//d1.findElement(By.xpath("//input[@type='PASSWORD']")).sendKeys("Test1234!");
		d1.findElement(By.xpath("//input[@value='Login']")).click();
		try{
			Thread.sleep(2000);			
			// Validation for Login Check
			Assert.assertEquals("Springboard", d1.getTitle());
			d1.findElement(By.xpath("//h2[contains(text(),'Dashboard')]"));
		Reporter.log(Sales_Campaigns.class.getSimpleName()+"-"+UserName_BackEnd+"- Springboard Login Passed");
			}catch(Exception e){
				Reporter.log("Dashboard Load Failed");
				/*String URL = "https://vicboards-qa.hrxtech.com.au/rasp6/home.seam;jsessionid=A248A6C25F88E379463A978269FFE99B.node2?userId=1086203432&userName=vpsc_dedjtr&userOrgId=18545&appOrgId=18452&sessionId=1364422938950782470&login=true&in_redirect_url=&cid=200";
				Reporter.log("<a href=" + URL+ ">click to open screenshot</a>");*/				
				throw new FailedLoginException("Dashboard load failed");
				
			}
		
	}
	

     @Test(priority = 2)
	public static void Campaigns() throws Exception{
		try{Thread.sleep(500);
		((JavascriptExecutor)d1).executeScript(javaScript, d1.findElement(By.xpath("//span[contains(text(),'Campaigns')]")));
		Thread.sleep(500);
		WebElement campaignMonitorAdmin = d1.findElement(By.xpath("//a[contains(text(),'Campaign Monitor Admin')]"));
		campaignMonitorAdmin.click();
		Thread.sleep(7000);
	//	 WebElement f1= d1.findElement(By.xpath("/html/body/div[4]/div/div[3]/div[2]/div[3]/div/div/form/iframe"));
		WebElement f1= d1.findElement(By.cssSelector("#j_idt32615 > iframe:nth-child(4)"));
		d1.switchTo().frame(f1);
		Thread.sleep(500);
		WebElement campaignMonitorAdminelmnt =d1.findElement(By.xpath("//h1[contains(text(),'Recent Drafts')]"));
		Thread.sleep(1000);
		String campaigns = campaignMonitorAdminelmnt.getTagName();
		System.out.println(campaigns);
		softAssert.assertEquals(campaigns,"h1", "Campaigns Monitor Admin tab not loading properly");
		softAssert.assertAll();
		Reporter.log("Campaigns Monitor Admin tab loaded successfully");
		}catch(Exception e){
			Reporter.log("Campaigns monitor Admin tab not loading properly");	
			throw new Exception("Campaigns Monitor Admin tab not loading properly");
		}
	finally{
		d1.switchTo().defaultContent();
		
			}
	}


	 @AfterSuite
	    public void tearDown() {
	       d1.quit();
	    }
	@DataProvider(name="sales-demo-data-provider")
	public String[][] usernameAndPasswordDataProvider() {
		return ExcelReadUtil.readExcelInto2DArray("./salesdemo.xlsx","sales",5);
	}
}
