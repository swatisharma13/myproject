package test;

import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.security.auth.login.FailedLoginException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;


public class qaTRIAL_V7 {
	 public static SoftAssert softAssert = new SoftAssert();
	 static WebDriver d1;
		static WebElement f1;
public static String javaScript = "var evObj = document.createEvent('MouseEvents');" +
         "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);" +
           "arguments[0].dispatchEvent(evObj);";
	


	@Test(dataProvider = "salesQAV7-data-provider", priority =1)
//	 @Test(priority =1)
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
			
			Reporter.log(qaTRIAL_V7.class.getSimpleName());
			Reporter.log("Browser Name:" + caps.getBrowserName().toUpperCase());
			Reporter.log(Environment);
			Reporter.log("Browser Version:"+caps.getVersion().toString());
	
	Reporter.log(Environment);
			Reporter.log("Browser Version:"+caps.getVersion().toString());
			 WebDriverManager.chromedriver().setup();
			  d1 = new ChromeDriver();
			  d1.get("https://kiwihealthjobs.qa.hrxtech.com.au/login");
			    d1.manage().window().maximize();

		d1.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);

						
	d1.findElement(By.xpath("//input[@name='in_username']")).sendKeys(UserName_BackEnd);
	d1.findElement(By.xpath("//input[@type='PASSWORD']")).sendKeys(Password_BackEnd);
		//d1.findElement(By.xpath("//input[@name='in_username']")).sendKeys("anna");
		//d1.findElement(By.xpath("//input[@type='PASSWORD']")).sendKeys("Test1234!");
		d1.findElement(By.name("in_login")).click();
		//d1.findElement(By.xpath("//input[@value='Login']")).click();
		try{
			Thread.sleep(2000);			
			// Validation for Login Check
			Assert.assertEquals("Springboard", d1.getTitle());
			d1.findElement(By.xpath("//h2[contains(text(),'Dashboard')]"));
		Reporter.log(qaTRIAL_V7.class.getSimpleName()+"-"+UserName_BackEnd+"- Springboard Login Passed");
			}catch(Exception e){
				Reporter.log("Dashboard Load Failed");
				/*String URL = "https://vicboards-qa.hrxtech.com.au/rasp6/home.seam;jsessionid=A248A6C25F88E379463A978269FFE99B.node2?userId=1086203432&userName=vpsc_dedjtr&userOrgId=18545&appOrgId=18452&sessionId=1364422938950782470&login=true&in_redirect_url=&cid=200";
				Reporter.log("<a href=" + URL+ ">click to open screenshot</a>");*/				
				throw new FailedLoginException("Dashboard load failed");
				
			}
		
	}
	
	@Test(priority = 2, enabled = false)
	  public static void topMenuItems()
	                             throws Exception {
	     try {
	            Thread.sleep(500);
	            d1.findElement(By.id("headerMenuForm:candidateSearch")).click();
	            WebElement candidateSearch = d1.findElement(By.xpath("//h2[contains(text(),'Candidate Search')]"));
	            String candiSearch = candidateSearch.getText();
	            Thread.sleep(1000);
	            softAssert.assertEquals(candiSearch, "CANDIDATE SEARCH","Candidate Search from the Top Menu Is not loading");
	            softAssert.assertAll();
	            d1.findElement(By.cssSelector(".ui-sidebar-close > span:nth-child(1)")).click();
	            Reporter.log("Candidate Search from the Top Menu is loaded");
	                }catch(Exception e){
	                      Reporter.log("Candidate Search from the Top Menu Fail to Load");
	                      throw new Exception("Candidate Search from the Top Menu Broken!");
	                }      
	         
	     try {
	            Thread.sleep(500);
	            d1.findElement(By.xpath("//*[@id='headerMenuForm:menuAddCandidateLink']")).click();
	             ((JavascriptExecutor)d1).executeScript(javaScript, d1.findElement(By.xpath("//*[(text() = 'Add Candidate')]")));
	          Thread.sleep(500);
	          // WebElement addCandidate = d1.findElement(By.xpath("//*[(text() = 'Add Candidate')]"));
	          //   String quickAddCandidate = addCandidate.getText();
	          // Thread.sleep(1000);
	          //softAssert.assertEquals(quickAddCandidate, "Add Candidate","Add candidate from the Topp Menu Is not loading");
	          //softAssert.assertAll();
	          Reporter.log("Add candidate from the Top Menu is loaded");
	         }catch(Exception e){
	          Reporter.log("Add candidate from the Top Menu Fail to Load");
	          throw new Exception("Add candidate from the Top Menu Broken!");
	          }      
	  
	    try {
	             Thread.sleep(500);
	             d1.findElement(By.cssSelector(".notifications")).click();
	             Thread.sleep(1000);
	             WebElement notifFrame = d1.findElement(By.id("notificationsFrame"));
	             d1.switchTo().frame(notifFrame);
	             WebElement notiElement = d1.findElement(By.xpath("//span[contains(text(),'Group by')]"));
	             String groupBy = notiElement.getText();
	             //d1.findElement(By.id("j_idt2195:ncCloseLink")).click();
	             softAssert.assertEquals(groupBy, "Group by","Job grid not loaded properly");
	             softAssert.assertAll();
	             Reporter.log("Notifications Tab Loaded");
	            
	                 }catch(Exception e){
	                      Reporter.log("Notifications Fail to Load");
	                      throw new Exception("Notifications Broken!");
	          }      
	    finally{                        
	           d1.switchTo().defaultContent();       
	          // d1.findElement(By.cssSelector(".ui-sidebar-close > span:nth-child(1)")).click();
	           d1.findElements(By.xpath("//*[@title=\"Close\"]")).get(1).click();
	}
	}

	 
	  @Test(priority = 3, enabled = false)
		public static void jobs()
						throws Exception {
			
				Thread.sleep(2000);
				((JavascriptExecutor)d1).executeScript(javaScript, d1.findElement(By.xpath("//span[contains(text(),'Dashboard')]")));
				
				Thread.sleep(1000);
				d1.findElement(By.xpath("//span[contains(text(),'Dashboard')]")).click();
				Thread.sleep(1000);
				d1.findElement(By.xpath("//a[@id='myReq:contReqForm:myReqsDashletTable:0:j_idt9']")).click();
				
				Thread.sleep(3000);
				WebElement f1 = d1.findElement(By.id("raspframe"));
				d1.switchTo().frame(f1);
				d1.findElement(By.xpath("//span[contains(text(),'Job Notes')]")).click();
				try{
				Thread.sleep(1000);
					
					WebElement f2 = d1.findElement(By.id("reqNotesFrame"));
					d1.switchTo().frame(f2);
					d1.findElement(By.xpath("//span[contains(text(),'Create New Note')]"));
				Reporter.log("Job Tab Loaded");
				}catch(Exception e){
					Reporter.log("Job Notes Tab Fail to Load");
					throw new Exception("Job Notes Tab Broken!");
					}	
					
			finally{				
				d1.switchTo().defaultContent();	
			}
		}
		
		@Test(priority = 4, enabled = false)
		public static void reviewModeJobGrid() throws Exception {
			
				Thread.sleep(1000);
				try{
			WebElement f1 = d1.findElement(By.id("raspframe"));
			d1.switchTo().frame(f1);
				d1.findElement(By.xpath("//span[contains(text(),'Search App')]")).click();
				Thread.sleep(3000);
				d1.switchTo().defaultContent();	
				Thread.sleep(2000);
				WebElement jobgridelement = d1.findElement(By.xpath("//span[contains(text(),'All')]"));
				String Jobgrid = jobgridelement.getText();
				softAssert.assertEquals(Jobgrid, "All","Job grid not loaded properly");
				softAssert.assertAll();
				Reporter.log("Job grid loads successfully");
				}catch(Exception e){
				Reporter.log("Job grid not loaded properly");
				throw new Exception("Job grid not loaded properly");
				}
				try{
				d1.findElement(By.xpath("//i[@title='Candidate Review']")).click();
				Thread.sleep(2000);		
				//d1.findElement(By.xpath("//a[@id='candForm:candidateGrid:0:j_idt710']")).click();
				
				WebElement resumeelement = d1.findElement(By.xpath("//div[starts-with(@id,'reviewTabPanel:resumeTabForm:j_idt')]"));
				Thread.sleep(1000);	
				boolean resume = resumeelement.isDisplayed();
				softAssert.assertEquals(resume, true,"Resume tab not loaded properly");
				softAssert.assertAll();
				Reporter.log("Resume tab working");
				}catch(Exception e){
				Reporter.log("Resume tab not working");
				throw new Exception("Resume tab not working");
				}
				try{
				Thread.sleep(1000);
				d1.findElement(By.xpath("//td[contains(text(),'Profile')]")).click();
				WebElement Profileelement = d1.findElement(By.xpath("//h3[contains(text(),'Personal Details')]"));
				String Profile = Profileelement.getText();	
				softAssert.assertEquals(Profile, "PERSONAL DETAILS","Profile tab not loaded properly");
				softAssert.assertAll();
				Reporter.log("PROFILE tab working");
				}catch(Exception e){
				Reporter.log("PROFILE tab not working");
				throw new Exception("PROFILE tab not working");
				}
				try{
				Thread.sleep(1000);
				d1.findElement(By.xpath("//td[contains(text(),'Applications')]")).click();
				WebElement Applicationelement = d1.findElement(By.xpath("//label[contains(text(),'Applications')]"));
				String Applications = Applicationelement.getText();
				softAssert.assertEquals(Applications, "Applications:","Application tab not loaded properly");
				softAssert.assertAll();
				Reporter.log("APPLICATION tab working");
				}catch(Exception e){
				Reporter.log("APPLICATION tab not working");
				throw new Exception("APPLICATION tab not working");
				}
				try{
				Thread.sleep(1000);
				d1.findElement(By.xpath("//td[contains(text(),'Communication')]")).click();
				WebElement Communicationelement = d1.findElement(By.xpath("//div[contains(text(),'Communication Relates to')]"));
				String Communication = Communicationelement.getText();
				softAssert.assertEquals(Communication, "Communication Relates to","Communication tab not loaded properly");	
				softAssert.assertAll();
				Reporter.log("COMMUNICATION tab working");
				}catch(Exception e){
				Reporter.log("COMMUNICATION tab not working");
				throw new Exception("COMMUNICATION tab not working");
				}
				try{
				Thread.sleep(1000);
				d1.findElement(By.xpath("//td[contains(text(),'Forms')]")).click();
				WebElement Formselement = d1.findElement(By.xpath("//span[contains(text(),'FORM')]"));
				String Forms = Formselement.getText();
				softAssert.assertEquals(Forms, "FORM","Forms tab not loaded properly");
				softAssert.assertAll();
				Reporter.log("FORMS tab working");
				}catch(Exception e){
				Reporter.log("FORMS tab not working");
				throw new Exception("FORMS tab not working");
				}
				try{
				Thread.sleep(1000);
				d1.findElement(By.xpath("//td[contains(text(),'Activity')]")).click();
				WebElement Activityelement = d1.findElement(By.xpath("//h3[contains(text(),'APPLICATION SUMMARY')]"));
				String Activity = Activityelement.getText();
				softAssert.assertEquals(Activity, "APPLICATION SUMMARY","Activity tab not loaded properly");
				softAssert.assertAll();
				Reporter.log("ACTIVITY tab working");
				}catch(Exception e){
				Reporter.log("ACTIVITY tab not working");
				throw new Exception("ACTIVITY tab not working");
				}
				
				try{
				Thread.sleep(1000);
				d1.findElement(By.xpath("//td[contains(text(),'Documents')]")).click();
				WebElement Documentselement = d1.findElement(By.xpath("//span[contains(text(),'Document name')]"));
				String Document = Documentselement.getText();
				softAssert.assertEquals(Document, "DOCUMENT NAME","Document tab not loaded properly");
				softAssert.assertAll();
				Reporter.log("DOCUMENTS tab working");
				}catch(Exception e){
				Reporter.log("DOCUMENTS tab not working");
				throw new Exception("DOCUMENTS tab not working");
				}
				try{
				Thread.sleep(1000);
				d1.findElement(By.xpath("//td[contains(text(),'Experience')]")).click();
				WebElement f3 = d1.findElement(By.id("candidateExperienceFrame"));
				d1.switchTo().frame(f3);
				Thread.sleep(1000);
				WebElement Experienceelement = d1.findElement(By.xpath("//span[contains(text(),'Company')]"));
				String Experience = Experienceelement.getText();
				softAssert.assertEquals(Experience, "Company","Experience tab not loaded properly");
				softAssert.assertAll();
				Reporter.log("EXPERIENCE tab working");
				}catch(Exception e){
				Reporter.log("EXPERIENCE tab not working");
				throw new Exception("EXPERIENCE tab not working");
				}
			
			finally{
				d1.switchTo().defaultContent();	

				}
			}	

		@Test(priority = 5, enabled= true)
		public static void CreateaJob() throws Exception{
			try{
				String winHandleBefore = d1.getWindowHandle();			
				((JavascriptExecutor)d1).executeScript(javaScript, d1.findElement(By.xpath("//span[contains(text(),'Jobs')]")));
				Thread.sleep(500);	
				d1.findElement(By.xpath("//a[contains(text(),'Create Job')]")).click();					
				Thread.sleep(500);	
				WebElement f4 = d1.findElement(By.id("raspframe"));
				d1.switchTo().frame(f4);	
				Thread.sleep(500);
		d1.findElement(By.xpath("//a[contains(@href,'clientlov')]")).click();
				Thread.sleep(500);
				for(String winHandle : d1.getWindowHandles()){
				    d1.switchTo().window(winHandle);
				}
				Thread.sleep(1000);	
				d1.findElements(By.xpath("//a[contains(@href,'jobtools/cmutils.multilov')]")).get(5).click();
				Thread.sleep(1000);
				d1.findElements(By.xpath("//a[contains(@href,'javascript:passBack')]")).get(0).click();
				//d1.findElement(By.xpath("//a[contains(text(),'States Training')]")).click();
				Thread.sleep(1000);
				d1.switchTo().window(winHandleBefore);
				d1.switchTo().frame(f4);	
				 DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Date date = new Date();
				 String date1= dateFormat.format(date);
			d1.findElement(By.name("in_job_title")).sendKeys("Automation Test Job - ",date1);
			d1.findElement(By.name("in_copy_group")).click();
			Thread.sleep(1000);	
			Select Employment_Status = new Select (d1.findElement(By.id("in_jobmgt_jobtype")));
			Employment_Status.selectByVisibleText("Full Time");
			Select req_Status = new Select (d1.findElement(By.name("in_status")));
			req_Status.selectByVisibleText("Approved");
			d1.findElements(By.name("in_values")).get(11).sendKeys("1234567890");
			d1.findElement(By.name("in_update")).click(); 
				}catch(Exception e){
				Reporter.log("Create Job is unsuccessful");
				throw new Exception("Create Job is unsuccessful!!");
				
				}
			finally{
				d1.switchTo().defaultContent();	
			}
		}
		
		
		
	
		
	 @AfterClass
	    public static void tearDown() {
	       d1.quit();
	    
	    }
	@DataProvider(name="salesQAV7-data-provider")
	public String[][] usernameAndPasswordDataProvider() {
		return ExcelReadUtil.readExcelInto2DArray("./salesqa.xlsx","sales",5);
	}
}
