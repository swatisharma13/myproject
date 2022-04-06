package test;

import java.net.MalformedURLException;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import javax.security.auth.login.FailedLoginException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.Select;
import io.github.bonigarcia.wdm.WebDriverManager;
import test.cr;

public class kingsburyV6_Staging {
	static WebDriver d1;
	static WebElement f1;
public static String javaScript = "var evObj = document.createEvent('MouseEvents');" +
         "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);" +
           "arguments[0].dispatchEvent(evObj);";
	


	@Test(dataProvider = "kingsburystgV6-data-provider", priority =1)
	public static void login_backend(


			String UserName_BackEnd,
			String Password_BackEnd,
			String profileName,
			String maximize,
			String Environment) 
	
					throws InterruptedException, MalformedURLException, FailedLoginException {
			DesiredCapabilities caps = DesiredCapabilities.chrome();
			Reporter.log(
					"===============================================================");
			
			Reporter.log(kingsburyV6_Staging.class.getSimpleName());
			Reporter.log("Browser Name:" + caps.getBrowserName().toUpperCase());
			Reporter.log(Environment);
			Reporter.log("Browser Version:"+caps.getVersion().toString());
	
	Reporter.log(Environment);
			Reporter.log("Browser Version:"+caps.getVersion().toString());
			 WebDriverManager.chromedriver().setup();
			  d1 = new ChromeDriver();
			  d1.get("https://evergreen-stg.springboardats.com/jobtools/jncustomorglogin.logout?in_organid=19623");
			    d1.manage().window().maximize();

		d1.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);

						
	d1.findElement(By.xpath("//input[@name='in_username']")).sendKeys(UserName_BackEnd);
	d1.findElement(By.xpath("//input[@type='PASSWORD']")).sendKeys(Password_BackEnd);
	d1.findElement(By.xpath("//input[@value='Login']")).click();
	try{
		//Thread.sleep(2000);
		WebElement f1 = d1.findElement(By.id("raspframeEncoded"));
		d1.switchTo().frame(f1);
		WebElement f2 = d1.findElement(By.id("dashboardFrame"));
		d1.switchTo().frame(f2);
		// Validation for Login Check
		d1.findElement(By.xpath("//span[contains(text(),'My Jobs')]"));
		//Assert.assertEquals("Springboard", d1.getTitle());
		Reporter.log(kingsburyV6_Staging.class.getSimpleName()+"-"+UserName_BackEnd+"- Springboard Login Passed");
		}catch(Exception e){
			Reporter.log("Dashboard Load Failed");
			/*String URL = "https://vicboards-qa.hrxtech.com.au/rasp6/home.seam;jsessionid=A248A6C25F88E379463A978269FFE99B.node2?userId=1086203432&userName=vpsc_dedjtr&userOrgId=18545&appOrgId=18452&sessionId=1364422938950782470&login=true&in_redirect_url=&cid=200";
			Reporter.log("<a href=" + URL+ ">click to open screenshot</a>");*/
			throw new NoSuchElementException("Dashboard load failed");
			
		}
		
	}

	@Test(priority = 2)
	public static void JobTab()
					throws InterruptedException, FailedLoginException {
		//try{
			//Job Tab		
		//((JavascriptExecutor)d1).executeScript(javaScript,d1.findElement(By.xpath("//*[@id='sideMenuForm:j_id130_body']/span")));
	//	d1.findElement(By.id("sideMenuForm:REQ_SEARCH")).click();
		Thread.sleep(3000);			
	
		cr.clickSidebarMenuItem(d1,"Jobs");
		WebElement f1 = d1.findElement(By.id("raspframe"));
			d1.switchTo().frame(f1);
			d1.findElement(By.xpath("//input[@name='in_search']")).click();
			Thread.sleep(1000);
			d1.findElement(By.xpath("//a[contains(text(),'KB/1207040')]")).click();
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
				Reporter.log("Job Notes Tab Fail to Load");
				throw new FailedLoginException("Job Notes Tab Broken!");
				}			
	}
	@Test(priority = 3)
	public static void Experience_Tab()
					throws InterruptedException, FailedLoginException {
		try{
			//Candidate Experience Tab
			cr.clickSidebarMenuItem(d1,"Candidates");
			Thread.sleep(2000);
			d1.findElement(By.xpath("//input[@id='candidateSearch:searchForm:search-criteria:email']")).sendKeys("9699183@hrx.com.au.xx");
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
			Thread.sleep(2000);
			d1.findElement(By.xpath("//a[@id='candidateGridTitleForm:switchToReviewModeLink']/img")).click();
			Thread.sleep(2000);
			d1.findElement(By.xpath("//td[@id='EXPERIENCE_lbl']/table/tbody/tr/td[2]")).click();						
			WebElement f1 = d1.findElement(By.id("candidateExperienceFrame"));
			d1.switchTo().frame(f1);		
			Reporter.log("Candidate Experience Tab Loaded");
			}catch(Exception e){
			Reporter.log("Candidate Experience Tab Fail to Load");
			throw new FailedLoginException("Candidate Experience Tab Broken!");
			}	
		}	
	@Test(priority = 4)
	public static void externalSearch()
					throws InterruptedException, FailedLoginException {
		try{
		// Search String Builder
		cr.clickSidebarMenuItem(d1, "Candidates");	
		Thread.sleep(2000);
		d1.findElement(By.xpath("//a[contains(text(),'External Search')]")).click();
		WebElement f1 = d1.findElement(By.id("externalSearchFrame"));
		d1.switchTo().frame(f1);
		d1.findElement(By.xpath("//h3[contains(text(),'Use Google or Bing to search for profiles on LinkedIn')]"));
		Reporter.log("Search String Builder Page loaded Successfully");
		}catch(Exception e){
			Reporter.log("The External Search Link Is Broken");
			throw new FailedLoginException("The External Search Link Is Broken");
			
		}
	}
	
	@Test(priority = 5)
	public static void mailInbox() throws InterruptedException, FailedLoginException {
		try{
		// Mail Inbox
		cr.clickSidebarMenuItem(d1, "Candidates");
		Thread.sleep(2000);
		Select drpLastUpdated = new Select (d1.findElement(By.id("candidateSearch:searchForm:search-criteria:lastUpdated")));
		drpLastUpdated.deselectByVisibleText("Last 6 months");
		d1.findElement(By.xpath("//a[contains(text(),'Email Inbox')]")).click();
		WebElement f1 = d1.findElement(By.id("MailServiceInboxIFrame"));
		d1.switchTo().frame(f1);
		d1.findElement(By.xpath("//div[contains(text(),'Search')]"));
		 Reporter.log("Email Inbox Page loaded Successfully");
		}catch(Exception e){
			Reporter.log("Email Inbox Page Is Broken");
			throw new FailedLoginException("Email Inbox Page Is Broken");
		}	
	}


	@Test(priority = 6)
	public static void Interviews() throws InterruptedException, FailedLoginException {
		// Interviews
		//Thread.sleep(2000);
		cr.clickSidebarMenuItem(d1, "Interviews");
	try{	
		WebElement f1 = d1.findElement(By.id("interviewSchedulerFrame"));
		d1.switchTo().frame(f1);
		d1.findElement(By.xpath("//h3[contains(text(),'Search Interviews')]"));
	    Reporter.log("Interviews Page loaded Successfully");
	}catch(Exception e){	
	        Reporter.log("Interviews Page Is Broken");
	        throw new FailedLoginException("Interviews Page Is Broken");
	}
}


	@Test(priority = 7)
	public static void jobRequest() throws InterruptedException, FailedLoginException {
		try{
		// Job Requests
		cr.clickSidebarMenuItem(d1, "Job Requests");
		WebElement f1 = d1.findElement(By.id("jobRequestsFrame"));
		d1.switchTo().frame(f1);
		d1.findElement(By.xpath("//span[contains(text(),'Raise A Job')]"));
		Reporter.log("Job Request Page loaded Successfully");
		}catch(Exception e){
			  Reporter.log("Job Request Page Is Broken");
			  throw new FailedLoginException("Job Request Page Is Broken");
		}	
	}
	

	@Test(priority = 8)
	public static void Companies() throws InterruptedException, FailedLoginException {
		try{
		// Companies
		cr.clickSidebarMenuItem(d1, "Companies");
		WebElement f1 = d1.findElement(By.id("companiesFrame"));
		d1.switchTo().frame(f1);
		d1.findElement(By.xpath("//label[contains(text(),'Company Name :')]"));		
		Reporter.log("Companies Page loaded Successfully");
		}catch(Exception e) {
			Reporter.log("Companies Page Is Broken");
			throw new FailedLoginException("Companies Page Is Broken");
		}
	}
	@Test(priority = 9)
	public static void Campaigns() throws Exception{
		try{
		// Campaigns
			Thread.sleep(1000);
			cr.clickSidebarMenuItem(d1, "Campaigns");
			WebElement campaignsFrame = d1.findElement(By.id("campaignsFrame"));
			d1.switchTo().frame(campaignsFrame);
			d1.findElement(By.xpath("//h3[contains(text(),'Search Campaigns')]"));
			Reporter.log("Campaigns page loads");
			}catch(Exception e){
			Reporter.log("Campaigns page not loading properly");
			throw new Exception("Campaigns page not loading properly");
			}
		finally{
			d1.switchTo().defaultContent();	
		}
	}	
	@Test(priority = 10)
	public static void boardsManagement()
					throws InterruptedException, FailedLoginException {
		// Boards Management
		try{
		cr.clickSidebarMenuItem(d1, "Boards Management");
		Thread.sleep(2000);
		WebElement f1 = d1.findElement(By.id("boardsFrame"));
		d1.switchTo().frame(f1);
		d1.findElement(By.xpath("//h3[contains(text(),'Search Boards')]"));
		Reporter.log("Boards Management Page loaded Successfully");
		}catch(Exception e){
			Reporter.log("Boards Management Page Failed to Load");
			throw new FailedLoginException("Boards Management Page Failed to Load");
		}		
		
	}	
	@Test(priority = 11)
	public static void RTFTemplateAdmin()
					throws InterruptedException, FailedLoginException {
		try{
			cr.clickSidebarMenuItem(d1, "Administration");
			Thread.sleep(1000);
			d1.findElement(By.xpath("//a[contains(text(),'Admin Console')]")).click();
			Thread.sleep(1000);
			d1.findElement(By.xpath("//td[contains(text(),'RTF Template Admin')]")).click();
			Thread.sleep(1000);
			d1.findElement(By.xpath("//h2[contains(text(),'RTF Template Admin')]"));
			Reporter.log("RTF Template Admin Tab Loaded Successfully");
			}catch(Exception e){
				Reporter.log("RTF Template Admin Tab Load Fail");
				throw new NoSuchElementException("RTF Template Admin Tab Load Fail");
			}
		}

	@Test(priority = 12)
	public static void DownloadRTFTemplateBuilder()
					throws InterruptedException, FailedLoginException {
		try{
			d1.findElement(By.xpath("//td[contains(text(),'Download RTF Template Builder')]")).click();
			Thread.sleep(1000);
			d1.findElement(By.xpath("//p[contains(text(),'Download the RTF Template Builder to help you build marked up RTF templates for your offer letters, contracts, reference checks and other MSWord based documents.')]"));
			Reporter.log("Download RTF Template Builder Tab Loaded Successfully");
			}catch(Exception e){
				Reporter.log("Download RTF Template Builder Tab Load Fail");
				throw new NoSuchElementException("Download RTF Template Builder Tab Load Fail");
			}
	}
	
	@Test(priority = 13)
	public static void TalentFolderProcessesr()
					throws InterruptedException, FailedLoginException {
		try{
			Thread.sleep(1000);
			d1.findElement(By.xpath("//td[contains(text(),'Talent Folder Processes')]")).click();
			Thread.sleep(1000);
			d1.findElement(By.xpath("//div[contains(text(),'Talent Folder Processes')]"));
			Reporter.log("Talent Folder Processes Tab Loaded Successfully");
			}catch(Exception e){
				Reporter.log("Talent Folder Processes Tab Load Fail");
				throw new NoSuchElementException("Talent Folder Processes Tab Load Fail");
			}
	}
	
/*	@Test(priority = 13)
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
	
	@Test(priority = 14)
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
	}*/
	
	@Test(priority = 15)
	public static void Broadcasts()
					throws InterruptedException, FailedLoginException {
		try{
			Thread.sleep(1000);
			d1.findElement(By.xpath("//td[contains(text(),'Broadcasts')]")).click();
			Thread.sleep(1000);
			d1.findElement(By.xpath("//div[contains(text(),'Generate a notification to Springboard users')]"));
			Reporter.log("Broadcasts Tab Loaded Successfully");
			}catch(Exception e){
				Reporter.log("Broadcasts Tab Load Fail");
				throw new NoSuchElementException("Broadcasts Tab Load Fail");
			}
	}
	
	/*@Test(priority = 16)
	public static void FacebookApp()
					throws InterruptedException, FailedLoginException {
		try{
			d1.findElement(By.xpath("//td[contains(text(),'Facebook App')]")).click();
			Thread.sleep(1000);
			d1.findElement(By.xpath("//div[contains(text(),'Generate a secured facebook app installation link')]"));
			Reporter.log("Facebook App Tab Loaded Successfully");
			}catch(Exception e){
				Reporter.log("Facebook App Tab Load Fail");
				throw new NoSuchElementException("Facebook App Tab Load Fail");
			}
	}*/
	
	@Test(priority = 17)
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
	
	@Test(priority = 18)
	public static void InterviewAutoCommunication()
					throws InterruptedException, FailedLoginException {
		try{
			d1.switchTo().defaultContent();
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
	
	@Test(priority = 19)
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
	
	@Test(priority = 20)
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
	
	@Test(priority = 21)
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
	}
	
	@Test(priority = 22)
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
	@Test(priority = 23)
	public static void EmailQueueManagement()
					throws InterruptedException, FailedLoginException {
		try{
		cr.clickSidebarMenuItem(d1, "Administration");
		Thread.sleep(1000);
		d1.findElement(By.xpath("//a[contains(text(),'Email Queue Admin')]")).click();
		Thread.sleep(1000);
		WebElement f1 = d1.findElement(By.id("EmailQueueAdminIFrame"));
		d1.switchTo().frame(f1);
		d1.findElement(By.xpath("//span[contains(text(),'This page auto refreshes every 60 seconds.')]"));
		Reporter.log("Email Queue Management Page loaded Successfully");
		}catch(Exception e){
			Reporter.log("Email Queue Management Page Is Broken");
			throw new FailedLoginException("Email Queue Management Page Is Broken");
		}
	}			
	
	
	@Test(priority = 24)
	public static void Orbeon(
			)
					throws InterruptedException, FailedLoginException {
		try{
		// Orbeon
		cr.clickSidebarMenuItem(d1, "Administration");
		Thread.sleep(2000);		
		d1.findElement(By.xpath("//a[contains(text(),'Forms Library')]")).click();
		d1.findElement(By.xpath("//img[@src='/rasp6/themes/spring/includes/modules/images/modify_form.gif']/ancestor::a[1]")).click();
		//d1.findElement(By.xpath("//button[contains(text(),'Publish')]")).click();
		WebElement f1 = d1.findElement(By.id("formBuilderFrame"));
		d1.switchTo().frame(f1);
		d1.findElement(By.xpath("//span[contains(text(),'1.11 AFTER RELEASE')]"));		
	    Reporter.log("Orbeon Page loaded Successfully");		
		}catch(Exception e){
	        Reporter.log("The Orbeon Page Is Broken");
			throw new FailedLoginException("The Orbeon Page Is Broken");
	    }								
	}
	
	@Test(priority = 25)
	public static void DocumentTemplates(
			)
					throws InterruptedException, FailedLoginException {
		try{
		// Forms Search
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
	
	
	@Test(priority = 26)
	
		
		// Forms Search
			public static void Formssearch()
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
					d1.quit();
				}	
			}
		
	
	
	/*@Test(priority = 14)
	public static void delete_candidate(
)
					throws InterruptedException, FailedLoginException {
		try{
			//Candidate Experience
			cr.clickSidebarMenuItem(d1,"Candidates");
			Thread.sleep(2000);
			d1.findElement(By.xpath("//input[@id='candidateSearch:searchForm:search-criteria:name']")).sendKeys("Test-117 Candidate");
			Thread.sleep(2000);
			d1.findElement(By.xpath("//input[@id='candidateSearch:searchForm:search-actions:findCandidates']")).click();
			Thread.sleep(3000);
			d1.switchTo().defaultContent();					
			//@formatter:off
			Optional<WebElement> menuItem_assesment = 				
					d1.findElements(By.xpath("//span[contains(text(),'Automated Testers')]")).stream().filter(p -> p.isDisplayed()).findAny();
					//@formatter:on				
			menuItem_assesment.ifPresent(p -> p.click());
			d1.findElement(By.xpath("//div[@class='extdt-cell-div']/input")).click();
			Thread.sleep(3000);
			Actions oAction_delcandidate=new Actions(d1);
			oAction_delcandidate.contextClick(d1.findElement(By.xpath("//td[@class='candidateNameCell']"))).perform();
			Thread.sleep(3000);
			d1.findElement(By.xpath("//span[contains(text(),'Delete Candidate')]")).click();
			for (String winHandle : d1.getWindowHandles()) {
				d1.switchTo().window(winHandle);
			}
			d1.switchTo().frame("DeleteCandidateActionFrame");
			d1.findElement(By.xpath("//span[contains(text(),'Delete Candidate')]")).click();
			for (String winHandle : d1.getWindowHandles()) {
				d1.switchTo().window(winHandle);
			}
			Thread.sleep(3000);
			int Row_Count = d1.findElements(By.xpath("//tr[starts-with(@id,'candForm:candidateGrid:n:')]")).size();
			Reporter.log("Candidate Delete Successfull and number of record in grid = "+Row_Count); 	
			}catch(Exception e){
			Reporter.log("Candidate Delete Failed");
			throw new FailedLoginException("Candidate Delete Not Successfull!");
			}	
	}
	
	
	@Test(dataProvider = "getData", priority = 15)
	public static void Send_SMS(
			String UserName_BackEnd,
			String Password_BackEnd,
			String browserName,
			String profileName,
			String maximize,
			String Environment)
					throws InterruptedException, FailedLoginException {
		try{
			//Candidate Experience
			cr.clickSidebarMenuItem(d1,"Candidates");
			Thread.sleep(2000);
			d1.findElement(By.xpath("//input[@id='candidateSearch:searchForm:search-criteria:email']")).sendKeys("amin101@hrx.com.au");
			Thread.sleep(2000);
			d1.findElement(By.xpath("//input[@id='candidateSearch:searchForm:search-actions:findCandidates']")).click();
			Thread.sleep(3000);
			d1.switchTo().defaultContent();					
			//@formatter:off
			Optional<WebElement> menuItem_assesment = 				
					d1.findElements(By.xpath("//span[contains(text(),'Automated Testers')]")).stream().filter(p -> p.isDisplayed()).findAny();
					//@formatter:on				
			menuItem_assesment.ifPresent(p -> p.click());
			d1.findElement(By.xpath("//div[@class='extdt-cell-div']/input")).click();
			Thread.sleep(3000);
			Actions oAction_delcandidate=new Actions(d1);
			oAction_delcandidate.contextClick(d1.findElement(By.xpath("//td[@class='candidateNameCell']"))).perform();
			Thread.sleep(3000);
			d1.findElement(By.xpath("//span[contains(text(),'Send SMS')]")).click();
			for (String winHandle : d1.getWindowHandles()) {
				d1.switchTo().window(winHandle);
			}
			d1.switchTo().frame("SendSmsActionContainerIFrame");
			//d1.findElement(By.xpath("//div[@id='j_id25543:j_id25551']/table/tbody/tr/td[2]/select")).click();/*sendKeys("Burst");
			d1.findElement(By.xpath("//input[@id='j_id25543:j_id25560:mobileNumber']")).sendKeys("ABC");
		
			
			for (String winHandle : d1.getWindowHandles()) {
				d1.switchTo().window(winHandle);
			}
			Thread.sleep(3000);
			int Row_Count = d1.findElements(By.xpath("//tr[starts-with(@id,'candForm:candidateGrid:n:')]")).size();
			Reporter.log("Candidate Delete Successfull and number of record in grid = "+Row_Count); 
			String a1 = d1.findElement(By.xpath("//span[contains(text(),'Grid refreshed with latest data.']")).getText();
			System.out.println(a1);		
			
			}catch(Exception e){
			Reporter.log("Candidate Delete Failed");
			throw new FailedLoginException("Candidate Delete Not Successfull!");
			}
	}*/
	 

	

		 @AfterSuite
	    public void tearDown() {
	       d1.quit();
	    }
	@DataProvider(name="kingsburystgV6-data-provider")
	public String[][] usernameAndPasswordDataProvider() {
		return ExcelReadUtil.readExcelInto2DArray("./kingsburystgV6.xlsx","kingsbury",5);
	}
}