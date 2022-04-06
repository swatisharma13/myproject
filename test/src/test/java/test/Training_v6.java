package test;

import java.util.concurrent.TimeUnit;
import javax.security.auth.login.FailedLoginException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import test.cr;

public class Training_v6 {

	static WebDriver d1;
	@Test(dataProvider = "trnv6-data-provider", priority =1)
	public static void login_backend(
				String UserName_BackEnd,
				String Password_BackEnd,
				String profileName,
				String maximize,
				String Environment)
						throws InterruptedException {
			WebDriverManager.chromedriver().setup();
			d1 = new ChromeDriver();
			DesiredCapabilities caps = DesiredCapabilities.chrome();
			caps.setBrowserName("chrome");
			caps.setPlatform(Platform.WINDOWS);
			//d1 = new RemoteWebDriver(new URL("http://localhost:5566/wd/hub"), caps);
			Reporter.log(
					"===============================================================");
			
			Reporter.log(Training_V7.class.getSimpleName());
			Reporter.log("Browser Name:" + caps.getBrowserName().toUpperCase());
			Reporter.log(Environment);
			d1.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);

		d1.get("https://training.smartjobs.govnet.qld.gov.au/jobtools/jncustomorglogin.logout?in_organid=15137");

		d1.manage().window().maximize();
				
			// throw new SkipException("Skipping Test");
			d1.findElement(By.xpath("//input[@name='in_username']")).sendKeys(UserName_BackEnd);
			d1.findElement(By.xpath("//input[@type='PASSWORD']")).sendKeys(Password_BackEnd);
			d1.findElement(By.xpath("//input[@value='Login']")).click();
			Thread.sleep(1000);
		// throw new SkipException("Skipping Test");
			
		
		try{
			//Thread.sleep(2000);
			WebElement f1 = d1.findElement(By.id("raspframeEncoded"));
			d1.switchTo().frame(f1);
			WebElement f2 = d1.findElement(By.id("dashboardFrame"));
			d1.switchTo().frame(f2);
			// Validation for Login Check
			d1.findElement(By.xpath("//span[contains(text(),'My Tasks')]"));
			//Assert.assertEquals("Springboard", d1.getTitle());
			Reporter.log(Training_v6.class.getSimpleName()+"-"+UserName_BackEnd+"- Springboard Login Passed");
			}catch(Exception e){
				Reporter.log("Dashboard Load Failed");
				throw new NoSuchElementException("Dashboard load failed");
				
			}
	}
	
	@Test(priority = 2)
	public static void Interviews() throws InterruptedException, FailedLoginException {
		// Interviews
		cr.clickSidebarMenuItem(d1, "Interviews");
	try{	
		
		WebElement f1 = d1.findElement(By.id("interviewSchedulerFrame"));
		d1.switchTo().frame(f1);		
		d1.findElement(By.xpath("//h3[contains(text(),'Search Interviews')]"));
	    Reporter.log("Interviews Page loaded Successfully");
	}catch(Exception e){	
	        Reporter.log("Interviews Page Is Broken");
		throw new FailedLoginException("Interview Page fails to load");
	}	
}
	
	@Test(priority = 3)
	public static void JobTab()
					throws InterruptedException, FailedLoginException {
		//try{
			//Job Tab		
			cr.clickSidebarMenuItem(d1,"Jobs");
			d1.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			WebElement f1 = d1.findElement(By.id("raspframe"));
			Thread.sleep(2000);
		d1.switchTo().frame(f1);
			d1.findElement(By.xpath("//input[@name='in_search']")).click();
			Thread.sleep(3000);
		    d1.findElement(By.xpath("//a[contains(text(),'QLD/174563')]")).click();
			Thread.sleep(2000);
			d1.findElement(By.xpath("//span[contains(text(),'Job Notes')]")).click();
			try{
			Thread.sleep(1000);
			//Assert.assertEquals(d1.switchTo().frame("reqNotesFrame"), "reqNotesFrame");
				WebElement f2 = d1.findElement(By.id("reqNotesFrame"));
				d1.switchTo().frame(f2);
				//d1.findElement(By.className(className))
				d1.findElement(By.xpath("//span[contains(text(),'Create New Note')]"));
			Reporter.log("Job Tab Loaded");
			}catch(Exception e){
				Reporter.log("Job Tab Failed to Load");
				throw new FailedLoginException("Job Tab Broken!");
				}			
	}
	
	
	@Test(priority = 4)
	public static void Experience_Tab()
					throws InterruptedException, FailedLoginException {
		try{
			//Candidate Experience
			cr.clickSidebarMenuItem(d1,"Candidates");
			Thread.sleep(2000);
			d1.findElement(By.xpath("//input[@id='candidateSearch:searchForm:search-criteria:name']")).sendKeys("Janet Jackson");
			Thread.sleep(2000);
			d1.findElement(By.xpath("//input[@id='candidateSearch:searchForm:search-actions:findCandidates']")).click();
			/*Thread.sleep(2000);
			d1.switchTo().defaultContent();					
			//@formatter:off
			Optional<WebElement> menuItem_assesment = 				
					d1.findElements(By.xpath("//span[contains(text(),'My Folders')]")).stream().filter(p -> p.isDisplayed()).findAny();
					//@formatter:on				
			menuItem_assesment.ifPresent(p -> p.click());*/
			Thread.sleep(2000);
			d1.findElement(By.xpath("//input[starts-with(@id, 'candForm:candidateGrid:')]")).click();
			d1.findElement(By.xpath("//a[@id='candidateGridTitleForm:switchToReviewModeLink']/img")).click();
			Thread.sleep(2000);
			//d1.findElement(By.xpath("//td[@id='EXPERIENCE_lbl']/table/tbody/tr/td[2]")).click();						
			//WebElement f1 = d1.findElement(By.id("candidateExperienceFrame")); 
			//d1.switchTo().frame(f1);		
			Reporter.log("Candidate Experience Tab Loaded");
			}catch(Exception e){
			Reporter.log("Candidate Experience Tab Faile to Load");
			throw new NoSuchElementException("Candidate Experience Tab Broken!");
			}		
	}	
	
	
	@Test(priority = 5)
	public static void RTFTemplateAdmin()
					throws InterruptedException, FailedLoginException {
		try{
			cr.clickSidebarMenuItem(d1, "Administration");
			Thread.sleep(1000);
			d1.findElement(By.xpath("//a[contains(text(),'Admin Console')]")).click();
			Thread.sleep(2000);
			d1.findElement(By.xpath("//td[contains(text(),'RTF Template Admin')]")).click();
			Thread.sleep(1000);
			d1.findElement(By.xpath("//h2[contains(text(),'RTF Template Admin')]"));
			Reporter.log("RTF Template Admin Tab Loaded Successfully");
			}catch(Exception e){
				Reporter.log("RTF Template Admin Tab Load Fail");
				throw new NoSuchElementException("RTF Template Admin Tab Load Fail");
			}
	}
	
	
	@Test(priority = 6)
	public static void DownloadRTFTemplateBuilder()
					throws InterruptedException, FailedLoginException {
		try{
			Thread.sleep(1000);
			d1.findElement(By.xpath("//td[contains(text(),'Download RTF Template Builder')]")).click();
			Thread.sleep(1000);
			d1.findElement(By.xpath("//p[contains(text(),'Download the RTF Template Builder to help you build marked up RTF templates for your offer letters, contracts, reference checks and other MSWord based documents.')]"));
			Reporter.log("Download RTF Template Builder Tab Loaded Successfully");
			}catch(Exception e){
				Reporter.log("Download RTF Template Builder Tab Load Fail");
				throw new NoSuchElementException("Download RTF Template Builder Tab Load Fail");
			}
	}
	
	@Test(priority = 7)
	public static void CandidateAccounts()
					throws InterruptedException, FailedLoginException {
		try{
			d1.findElement(By.xpath("//td[contains(text(),'Candidate Accounts')]")).click();
			Thread.sleep(1000);
			d1.findElement(By.xpath("//div[contains(text(),'Candidate Account Administration')]"));
			Reporter.log("Candidate Accounts Tab Loaded Successfully");
			}catch(Exception e){
				Reporter.log("Candidate Accounts Tab Load Fail");
				throw new NoSuchElementException("Candidate Accounts Tab Load Fail");
			}
	}
	@Test(priority = 8)
	public static void SMSAccounts()
					throws InterruptedException, FailedLoginException {
			try{
				d1.findElement(By.xpath("//td[contains(text(),'SMS Accounts')]")).click();
				Thread.sleep(1000);
				d1.findElement(By.xpath("//span[contains(text(),'SMS Accounts')]"));
				Reporter.log("SMS Accounts Tab Loaded Successfully");
				}catch(Exception e){
					Reporter.log("SMS Accounts Tab Load Fail");
					throw new NoSuchElementException("SMS Accounts Tab Load Fail");
				}
	}
	
	@Test(priority = 9)
	public static void CandidatePortalConfig()
					throws InterruptedException, FailedLoginException {
			try{
				d1.findElement(By.xpath("//td[contains(text(),'Candidate Portal Config')]")).click();
				Thread.sleep(1000);
				d1.findElement(By.xpath("//span[contains(text(),'Candidate Portal Config Details')]"));
				Reporter.log("Candidate Portal Config Tab Loaded Successfully");
				}catch(Exception e){
					Reporter.log("Candidate Portal Config Tab Load Fail");
					throw new NoSuchElementException("Candidate Portal Config Tab Load Fail");
				}
	}
	@Test(priority = 10)
	public static void Broadcasts()
					throws InterruptedException, FailedLoginException {
	try{
		d1.findElement(By.xpath("//td[contains(text(),'Broadcasts')]")).click();
		Thread.sleep(1000);
		d1.findElement(By.xpath("//div[contains(text(),'Generate a notification to Springboard users')]"));
		Reporter.log("Broadcasts Tab Loaded Successfully");
		}catch(Exception e){
			Reporter.log("Broadcasts Tab Load Fail");
			throw new NoSuchElementException("Broadcasts Tab Load Fail");
		}
	}
	@Test(priority = 11)
	public static void UserGroups() throws InterruptedException {
		try{
		// User Groups
		d1.findElement(By.xpath("//td[contains(text(),'User Groups')]")).click();		
		d1.findElement(By.xpath("//div[contains(text(),'User Groups Admin')]"));
		 Reporter.log("User Groups Tab loaded Successfully");
		}catch(Exception e){
			Reporter.log("User Groups Tab Is Broken");
		}
	}
	
	@Test(priority = 12)
	public static void Locations()
					throws InterruptedException, FailedLoginException {
		try{
			d1.findElement(By.xpath("//td[contains(text(),'Locations')]")).click();
			Thread.sleep(1000);
			WebElement f1 = d1.findElement(By.id("interviewLocations"));
			d1.switchTo().frame(f1);
			d1.findElement(By.xpath("//span[contains(text(),'Add Location')]"));
			Reporter.log("Locations Tab Loaded Successfully");
			}catch(Exception e){
				Reporter.log("Locations Tab Load Fail");
				throw new NoSuchElementException("Locations Tab Load Fail");
			}
		d1.switchTo().defaultContent();
	}
	
	@Test(priority = 13)
	public static void InterviewAutoCommunication()
					throws InterruptedException, FailedLoginException {
		try{
			d1.findElement(By.xpath("//td[contains(text(),'Interview Auto Communication')]")).click();
			Thread.sleep(1000);
			WebElement f1 = d1.findElement(By.xpath("//iframe[starts-with(@src,'../scheduling-manager/auto-communication-setup/auto-comm-config-setup.jsf;')]"));
			d1.switchTo().frame(f1);
			d1.findElement(By.xpath("//span[contains(text(),'Enabled')]"));
			Reporter.log("Interview Auto Communication Tab Loaded Successfully");
			}catch(Exception e){
				Reporter.log("Interview Auto Communication Tab Load Fail");
				throw new NoSuchElementException("Interview Auto Communication Tab Load Fail");
			}
		d1.switchTo().defaultContent();
	}
	
	@Test(priority = 14)
	public static void GridColumns()
					throws InterruptedException, FailedLoginException {
		try{
			d1.findElement(By.xpath("//td[contains(text(),'Grid Columns')]")).click();
			Thread.sleep(1000);
			d1.findElement(By.xpath("//div[contains(text(),'Grid Columns')]"));
			Reporter.log("Grid Columns Tab Loaded Successfully");
			}catch(Exception e){
				Reporter.log("Grid Columns Tab Load Fail");
				throw new NoSuchElementException("Grid Columns Tab Load Fail");
			}
	}
	
	/*@Test(priority = 11)
	public static void ApplicationAdmin()
					throws InterruptedException, FailedLoginException {
		try{
			d1.findElement(By.xpath("//td[contains(text(),'Application Admin')]")).click();
			Thread.sleep(1000);
			WebElement f1 = d1.findElement(By.id("applicationAdmin"));
			d1.switchTo().frame(f1);
			d1.findElement(By.xpath("//h3[contains(text(),'Manage Items')]"));
			Reporter.log("Application Admin Tab Loaded Successfully");
			}catch(Exception e){
				Reporter.log("Application Admin Feedback Tab Load Fail");
				throw new NoSuchElementException("Application Admin Feedback Tab Load Fail");
			}
		d1.switchTo().defaultContent();		
	}*/
	@Test(priority = 15)
	public static void HiringManagerFeedback()
					throws InterruptedException, FailedLoginException {
	try{
		d1.findElement(By.xpath("//td[contains(text(),'Hiring Manager Feedback')]")).click();
		Thread.sleep(1000);
		WebElement f1 = d1.findElement(By.id("hmFeedbackAdmin"));
		d1.switchTo().frame(f1);
		d1.findElement(By.xpath("//span[contains(text(),'Hiring Manager Feedback Administration')]"));
		Reporter.log("Hiring Manager Feedback Tab Loaded Successfully");
		}catch(Exception e){
			Reporter.log("Hiring Manager Feedback Tab Load Fail");
			throw new NoSuchElementException("Hiring Manager Feedback Tab Load Fail");
		}
	d1.switchTo().defaultContent();
	
	}
	@Test(priority = 16)
	public static void Holiday()
					throws InterruptedException, FailedLoginException {
		try{
			d1.findElement(By.xpath("//td[contains(text(),'Holiday')]")).click();
			Thread.sleep(1000);
			WebElement f1 = d1.findElement(By.id("holidayAdmin"));
			d1.switchTo().frame(f1);
			d1.findElement(By.xpath("//h3[contains(text(),'Add a new holiday')]"));
			Reporter.log("Holiday Tab Loaded Successfully");
			}catch(Exception e){
				Reporter.log("Holiday Tab Load Fail");
				throw new NoSuchElementException("Holiday Tab Load Fail");
			}
		d1.switchTo().defaultContent();
		
	}
	
	@Test(priority = 17)
	public static void EmailQueueManagement()
					throws InterruptedException, FailedLoginException {
		try{
		cr.clickSidebarMenuItem(d1, "Administration");
		Thread.sleep(1000);
		d1.findElement(By.xpath("//a[contains(text(),'Email Queue Admin')]")).click();
		Thread.sleep(1000);
		WebElement f1 = d1.findElement(By.id("EmailQueueAdminIFrame"));
		d1.switchTo().frame(f1);
		d1.findElement(By.xpath("//span[contains(text(),'This page auto refreshes every 60 seconds')]"));
		Reporter.log("Email Queue Management Page loaded Successfully");
		}catch(Exception e){
			Reporter.log("Email Queue Management Page Is Broken");
			throw new FailedLoginException("Email Queue Management Page Is Broken");
		}
	}

@Test(priority = 18)
public static void Orbeon()
				throws InterruptedException, FailedLoginException {
	try{
	// Orbeon
	cr.clickSidebarMenuItem(d1, "Administration");
	Thread.sleep(2000);		
	d1.findElement(By.xpath("//a[contains(text(),'Forms Library')]")).click();
	d1.findElement(By.xpath("//img[@src='/rasp6/themes/spring/includes/modules/images/modify_form.gif']/ancestor::a[1]")).click();
	Thread.sleep(2000);
	WebElement f1 = d1.findElement(By.id("formBuilderFrame"));
	d1.switchTo().frame(f1);
	d1.findElement(By.xpath("//span[contains(text(),'6.31 Post Check')]"));		
    Reporter.log("Orbeon Form loaded Successfully");		
	}catch(Exception e){
		Reporter.log("The Orbeon Form Is Broken");
		throw new FailedLoginException("Orbeon Form Fails to Load");
	}
  	
}
@Test(priority = 19)
public static void DocumentTemplates(
		)
				throws InterruptedException, FailedLoginException {
	try{
	// Document Templates
	cr.clickSidebarMenuItem(d1, "Administration");
	Thread.sleep(2000);		
	d1.findElement(By.xpath("//a[contains(text(),'Document Templates')]")).click();
	WebElement f1 = d1.findElement(By.id("onboardingFrame"));
	d1.switchTo().frame(f1);
	d1.findElement(By.xpath("//span[contains(text(),'Add Template')]"));		
    Reporter.log("Document Templates Page loaded Successfully");		
	}catch(Exception e){
        Reporter.log("Document Templates Page Is Broken");
		throw new FailedLoginException("Document Templates Page Is Broken");
    }									
}
@Test(priority = 20)
public static void FormsSearch(
		)
				throws InterruptedException, FailedLoginException {
	try{
	// Forms Search
	cr.clickSidebarMenuItem(d1, "Administration");
	Thread.sleep(2000);		
	d1.findElement(By.xpath("//a[contains(text(),'Forms Management')]")).click();
	WebElement f1 = d1.findElement(By.id("formsManagementFrame"));
	d1.switchTo().frame(f1);
	d1.findElement(By.xpath("//h3[contains(text(),'Search Forms')]"));		
    Reporter.log("Search Forms Page loaded Successfully");		
	}catch(Exception e){
        Reporter.log("Search Forms Page Is Broken");
		throw new FailedLoginException("Search Forms Page Is Broken");
    }								
	finally{	
		d1.close();
	}	
}
	
	@DataProvider(name="trnv6-data-provider")
	public String[][] usernameAndPasswordDataProvider() {
		return ExcelReadUtil.readExcelInto2DArray("./trainingV6.xlsx","TRN_v6",5);
	}
}

