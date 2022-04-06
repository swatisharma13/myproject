package test;

import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.security.auth.login.FailedLoginException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;


public class kingsbury_Staging {
	 public static SoftAssert softAssert = new SoftAssert();
	 static WebDriver d1;
		static WebElement f1;
public static String javaScript = "var evObj = document.createEvent('MouseEvents');" +
         "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);" +
           "arguments[0].dispatchEvent(evObj);";
	


	@Test(dataProvider = "kingsburystgV7-data-provider", priority =1)
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
			
			Reporter.log(kingsbury_Staging.class.getSimpleName());
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
		//d1.findElement(By.xpath("//input[@name='in_username']")).sendKeys("anna");
		//d1.findElement(By.xpath("//input[@type='PASSWORD']")).sendKeys("Test1234!");
		d1.findElement(By.xpath("//input[@value='Login']")).click();
		try{
			Thread.sleep(2000);			
			// Validation for Login Check
			Assert.assertEquals("Affinix", d1.getTitle());
			d1.findElement(By.xpath("//h2[contains(text(),'Dashboard')]"));
		Reporter.log(kingsbury_Staging.class.getSimpleName()+"-"+UserName_BackEnd+"- Springboard Login Passed");
			}catch(Exception e){
				Reporter.log("Dashboard Load Failed");
				/*String URL = "https://vicboards-qa.hrxtech.com.au/rasp6/home.seam;jsessionid=A248A6C25F88E379463A978269FFE99B.node2?userId=1086203432&userName=vpsc_dedjtr&userOrgId=18545&appOrgId=18452&sessionId=1364422938950782470&login=true&in_redirect_url=&cid=200";
				Reporter.log("<a href=" + URL+ ">click to open screenshot</a>");*/				
				throw new FailedLoginException("Dashboard load failed");
				
			}
		
	}
	@Test(priority = 2)
	  public static void topMenuItems()
	                             throws Exception {
	   
	         
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

	  
	
	@Test(priority = 3)
	public static void goToAJob()
					throws Exception {

		d1.findElement(By.xpath("//a[@id='myReq:contReqForm:myReqsDashletTable:0:j_idt9']")).click();
		
		Thread.sleep(2000);
		f1 = d1.findElement(By.id("raspframe"));
		d1.switchTo().frame(f1);
		d1.findElement(By.xpath("//span[contains(text(),'Job Notes')]")).click();
		try{
		Thread.sleep(1000);
			
	//		WebElement f2 = d1.findElement(By.id("reqNotesFrame"));
	//		d1.switchTo().frame(f2);
	//		d1.findElement(By.xpath("//span[contains(text(),'Create New Note')]"));
		Reporter.log("Job Tab Loaded");
		}catch(Exception e){
			Reporter.log("Job Notes Tab Fail to Load");
			throw new Exception("Job Notes Tab Broken!");
			}	
			
	finally{				
		d1.switchTo().defaultContent();	
	}
	}

	
	@Test(priority = 4)
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
		softAssert.assertEquals(Applications, "Applications","Application tab not loaded properly");
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
			d1.findElements(By.xpath("//a[contains(@href,'jobtools/cmutils.multilov')]")).get(0).click();
			Thread.sleep(1000);
			d1.findElements(By.xpath("//a[contains(@href,'jobtools/cmutils.multilov')]")).get(1).click();
			Thread.sleep(1000);
			//d1.findElements(By.xpath("//a[contains(@href,'javascript:passBack')]")).get(0).click();
			d1.findElement(By.xpath("//a[contains(text(),'Level3')]")).click();
			Thread.sleep(1000);
			d1.switchTo().window(winHandleBefore);
			d1.switchTo().frame(f4);	
			 DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			 String date1= dateFormat.format(date);
		d1.findElement(By.name("in_job_title")).sendKeys("Automation Test Job - ",date1);
		d1.findElement(By.name("in_copy_group")).click();
		Thread.sleep(1000);	
		d1.findElement(By.name("in_position_count")).sendKeys("1");
		Select Employment_Status = new Select (d1.findElement(By.id("in_jobmgt_jobtype")));
		Employment_Status.selectByVisibleText("Full Time");
		Select req_Status = new Select (d1.findElement(By.name("in_status")));
		req_Status.selectByVisibleText("Approved");
		//Category
		String winHandleBefore1 = d1.getWindowHandle();	
		d1.findElement(By.xpath("//a[contains(@href,'javascript:industrylov()')]")).click();
		for(String windowHandle : d1.getWindowHandles()){
		    d1.switchTo().window(windowHandle);
		}
		d1.findElement(By.name("in_action")).click();
		d1.findElements(By.xpath("//a[contains(@href,'javascript:passBack')]")).get(6).click();
		d1.switchTo().window(winHandleBefore1);
		WebElement f5 = d1.findElement(By.id("raspframe"));
		d1.switchTo().frame(f5);
		Thread.sleep(500);
		
		//Location
		String winHandleBefore2 = d1.getWindowHandle();	
		d1.findElement(By.xpath("//a[contains(@href,'javascript:locationlov()')]")).click();
		for(String windowHandle : d1.getWindowHandles()){
		d1.switchTo().window(windowHandle);
		}
		d1.findElement(By.name("in_action")).click();
		d1.findElements(By.xpath("//a[contains(@href,'javascript:passBack')]")).get(5).click();
		d1.switchTo().window(winHandleBefore2);
		WebElement f6 = d1.findElement(By.id("raspframe"));
		d1.switchTo().frame(f6);
		Thread.sleep(500);
		//City
		d1.findElements(By.name("in_values")).get(0).sendKeys("California City");			
		//State/Province
		Select state = new Select (d1.findElements(By.name("in_values")).get(1));
		state.selectByVisibleText("California");	
		//Will this job be posted in multiple languages?
		Select languages = new Select (d1.findElements(By.name("in_values")).get(4));
		languages.selectByVisibleText("Yes");		
		//Requisition Type:
		Select ReqType = new Select (d1.findElements(By.name("in_values")).get(7));
		ReqType.selectByVisibleText("Internal/External");	
		//Reason for Recruitment:
		Select RecruitmentReason = new Select (d1.findElements(By.name("in_values")).get(5));
		RecruitmentReason.selectByVisibleText("Addition");	
		//Hiring Manager Details
		d1.findElements(By.name("in_values")).get(8).sendKeys("Automation User");
		d1.findElements(By.name("in_values")).get(9).sendKeys("Automation@User.com");
		d1.findElement(By.name("in_update")).click(); 
			}catch(Exception e){
			Reporter.log("Create Job is unsuccessful");
			throw new Exception("Create Job is unsuccessful!!");
			
			}
		finally{
			d1.switchTo().defaultContent();	
		}
	}
	
	
	
	@Test(priority = 5)
	public static void externalSearch()
					throws Exception {
		try{
		// Search String Builder

		Thread.sleep(1000);		
		
		((JavascriptExecutor)d1).executeScript(javaScript, d1.findElement(By.xpath("//span[contains(text(),'Candidates')]")));
		Thread.sleep(1000);
		d1.findElement(By.xpath("//a[contains(text(),'External Search')]")).click();
		WebElement externalframe = d1.findElement(By.id("externalSearchFrame"));
		d1.switchTo().frame(externalframe);
		Thread.sleep(1000);
		WebElement extschelmnt = d1.findElement(By.xpath("//a[contains(text(),'LinkedIn')]"));
		String externalsearch = extschelmnt.getText();
		Thread.sleep(1000);
		Assert.assertEquals("LinkedIn", externalsearch);
		Thread.sleep(1000);	

		//////softAssert.assertAll();
		Reporter.log("External Search page loads");
		}catch(Exception e){
		Reporter.log("External Search page not loading properly");
		throw new Exception("External Search page not loading properly");
		}
		finally{
		d1.switchTo().defaultContent();	
		  }
		}	
	
		
	
	

	@Test(priority = 6)
	public static void EmailInbox() throws Exception{
		try{		
			Thread.sleep(500);
			((JavascriptExecutor)d1).executeScript(javaScript, d1.findElement(By.xpath("//span[contains(text(),'Candidates')]")));
			Thread.sleep(500);
			d1.findElement(By.xpath("//a[contains(text(),'Email Inbox')]")).click();
			WebElement inboxframe = d1.findElement(By.id("MailServiceInboxIFrame"));
			d1.switchTo().frame(inboxframe);
			Thread.sleep(500);
			Select typeDropDown = new Select (d1.findElement(By.id("inboundMessagesSearchForm:typeInput")));
			typeDropDown.selectByVisibleText("Archived");
			Thread.sleep(1000);
			//	d1.findElement(By.xpath("//*[@id="inboundMessagesSearchForm:inboundMessagesSearchButton"]']")).click();
			d1.findElement(By.xpath("//div[contains(text(),'Search')]")).click();
			Thread.sleep(2000);
			WebElement inboxelmnt = d1.findElement(By.xpath("//span[contains(text(),'Subject:')]"));
			String inbox = inboxelmnt.getTagName();
			assertEquals(inbox, "span","Email Inbox page not loading properly");
			//softAssert.assertAll();
			Reporter.log("Email Inbox page loads");
			}catch(Exception e){
			Reporter.log("Email Inbox page not loading properly");
			throw new Exception("Email Inbox page not loading properly");
			}
		finally{
		
		d1.switchTo().defaultContent();	
		}
	}

@Test(priority = 7)
	public static void Interviews() throws Exception {
		try{
		Thread.sleep(1000);	
		((JavascriptExecutor)d1).executeScript(javaScript, d1.findElement(By.xpath("//span[contains(text(),'Interviews')]")));
		Thread.sleep(1000);	
		d1.findElement(By.xpath("//a[contains(text(),'Interview Search')]")).click();
		Thread.sleep(1000);
		WebElement interviewframe = d1.findElement(By.id("interviewSchedulerFrame"));
		d1.switchTo().frame(interviewframe);
		//Select interviewStatus = new Select (d1.findElement(By.id("interviewManagerForm:statusSelector_items")));
		//interviewStatus.selectByVisibleText("All");
		//Thread.sleep(1000);
		d1.findElement(By.xpath("//span[contains(text(),'Any')]")).click();
		Thread.sleep(1000);
		WebElement intelmnt = d1.findElement(By.xpath("//span[contains(text(),'Step')]"));
		String interview = intelmnt.getText();
		softAssert.assertEquals(interview, "STEP","Interview page not loading properly");
		softAssert.assertAll();
		Reporter.log("Interview page loads");
		}catch(Exception e){
		Reporter.log("Interview page not loading properly");
		throw new Exception("Interview page not loading properly");
		}
		finally{
		d1.switchTo().defaultContent();	
		}
}


@Test(priority = 8)
public static void jobRequest() throws Exception{
	try{
		Thread.sleep(1000);				
		((JavascriptExecutor)d1).executeScript(javaScript, d1.findElement(By.xpath("//span[contains(text(),'Job Requests')]")));
		Thread.sleep(1000);	
		d1.findElement(By.xpath("//span[contains(text(),'Job Requests')]")).click();					
		Thread.sleep(1000);	

		//WebElement jobrequestframe = d1.findElement(By.id("jobRequestsFrame"));
		//d1.switchTo().frame(jobrequestframe);

		WebElement jobreqelmnt = d1.findElement(By.xpath("//h2[contains(text(),'Job Requests')]"));
		String jobrequest = jobreqelmnt.getText();
		Assert.assertEquals(jobrequest,"Job Requests","Job Request page not loading properly");
		//softAssert.assertAll();
		Reporter.log("Job Request page loads");
		}catch(Exception e){
		Reporter.log("Job Request page not loading properly");
		throw new Exception("Job Request page not loading properly");
		
		}
	finally{
		d1.switchTo().defaultContent();	
	}
}

@Test(priority = 13)
public static void TalentFolderProcessesr()
				throws Exception {
	try{			
		Thread.sleep(1000);
		((JavascriptExecutor)d1).executeScript(javaScript, d1.findElement(By.xpath("//span[contains(text(),'Administration')]")));
		Thread.sleep(1000);
		d1.findElement(By.xpath("//a[contains(text(),'Admin Console')]")).click();
		Thread.sleep(1000);
		d1.findElement(By.xpath("//a[contains(text(),'Talent Folder Processes')]")).click();
		Thread.sleep(1000);
		WebElement TalentFolderelmnt =d1.findElement(By.xpath("//a[contains(text(),'Processes Configuration')]"));
		String TalentFolder = TalentFolderelmnt.getText();
		Assert.assertEquals(TalentFolder,"PROCESSES CONFIGURATION", "Talent Folder Processes tab not loading properly");
		//softAssert.assertAll();
		Reporter.log("Talent Folder Processes tab loaded successfully");
		}catch(Exception e){
			Reporter.log("Talent Folder Processes tab not loading properly");
			throw new Exception("Talent Folder Processes tab not loading properly");
		}
}


@Test(priority = 17)
public static void Locations() throws Exception{
	try{			
		Thread.sleep(1000);
		d1.findElement(By.xpath("//a[contains(text(),'Locations')]")).click();
		Thread.sleep(1000);
		WebElement interviewLocations = d1.findElement(By.id("interviewLocations"));
		d1.switchTo().frame(interviewLocations);
		WebElement Locationserelmnt = d1.findElement(By.xpath("//span[@class='ui-icon ui-icon-seek-first']"));
		String Locations = Locationserelmnt.getTagName();
		Assert.assertEquals(Locations,"span", "Locations tab not loading properly");
		//softAssert.assertAll();
		Reporter.log("Locations tab loaded successfully");
		}catch(Exception e){
			Reporter.log("Locations tab not loading properly");	
			throw new Exception("Locations tab not loading properly");	
			
		}
	finally{
		d1.switchTo().defaultContent();				
	}
}
@Test(priority = 18)
public static void HiringManagerFeedback() throws Exception{
	try{			
		Thread.sleep(1000);
		d1.findElement(By.xpath("//a[contains(text(),'Hiring Manager Feedback')]")).click();
		Thread.sleep(1000);
		WebElement hmFeedbackAdmin = d1.findElement(By.id("hmFeedbackAdmin"));
		d1.switchTo().frame(hmFeedbackAdmin);
		Thread.sleep(1000);
		WebElement HiringManagerFeedbackelmnt = d1.findElement(By.xpath("//h3[contains(text(),'Hiring Manager Feedback Administration')]"));
		Thread.sleep(1000);
		String HiringManagerFeedback = HiringManagerFeedbackelmnt.getTagName();
		softAssert.assertEquals(HiringManagerFeedback,"h3", "Hiring Manager Feedback tab not loading properly");
		softAssert.assertAll();
		Reporter.log("Hiring Manager Feedback tab loaded successfully");
		}catch(Exception e){
			Reporter.log("Hiring Manager Feedback tab not loading properly");	
			throw new Exception("Hiring Manager Feedback tab not loading properly");
		}
	finally{
		d1.switchTo().defaultContent();
	}
}


@Test(priority = 19)
public static void Holiday() throws Exception{
	try{
		Thread.sleep(1000);
		d1.findElement(By.xpath("//a[contains(text(),'Holiday')]")).click();
		Thread.sleep(1000);
		WebElement f1 = d1.findElement(By.id("holidayAdmin"));
		d1.switchTo().frame(f1);
		WebElement holidayelmnt = d1.findElement(By.xpath("//h3[contains(text(),'Add a new holiday')]"));
		String Holiday = holidayelmnt.getTagName();
		Assert.assertEquals(Holiday,"h3", "Holiday tab not loading properly");
		//softAssert.assertAll();			
		Reporter.log("Holiday tab loaded successfully");
		}catch(Exception e){
			Reporter.log("Holiday tab not loading properly");
			throw new Exception("Holiday tab not loading properly");
		}
	finally{
	d1.switchTo().defaultContent();
	}
}
@Test(priority = 20)
public static void EmailQueueAdmin() throws Exception{
	try{
		Thread.sleep(2000);
		((JavascriptExecutor)d1).executeScript(javaScript, d1.findElement(By.xpath("//span[contains(text(),'Administration')]")));
		Thread.sleep(2000);
		d1.findElement(By.xpath("//a[contains(text(),'Email Queue Admin')]")).click();
		Thread.sleep(2000);
		WebElement emailqueueadminelmnt = d1.findElement(By.xpath("//h2[contains(text(),'Administration/Email Queue Admin')]"));
		String EmailQueueAdmin = emailqueueadminelmnt.getText();
		Assert.assertEquals(EmailQueueAdmin,"Administration/Email Queue Admin", "Email Queue Admin page not loading properly");
		//softAssert.assertAll();			
		Reporter.log("Email Queue Admin page loaded successfully");
		}catch(Exception e){
			Reporter.log("Email Queue Admin page not loading properly");
			throw new Exception("Email Queue Admin page not loading properly");
		}
}			


@Test(priority = 21)
public static void OrbeonForm() throws Exception{
	try{
		// Orbeon
		
		Thread.sleep(1000);
		((JavascriptExecutor)d1).executeScript(javaScript, d1.findElement(By.xpath("//span[contains(text(),'Administration')]")));	
		Thread.sleep(1000);
		d1.findElement(By.xpath("//a[contains(text(),'Forms Library')]")).click();	
		d1.findElement(By.xpath("//a[@aria-label='Edit Form']")).click();
		WebElement f1 = d1.findElement(By.id("formBuilderFrame"));
		d1.switchTo().frame(f1);
		WebElement orbeonelmnt = d1.findElement(By.xpath("//span[@class='xforms-output-output']"));
		String OrbeonForm = orbeonelmnt.getTagName();
		Assert.assertEquals(OrbeonForm,"span", "Orbeon form not loading properly");
		//softAssert.assertAll();		
		Reporter.log("Orbeon form loaded successfully");	
		d1.findElement(By.xpath("//*[@id='xf-391≡xf-1413≡≡c⊙1']")).click();
		}catch(Exception e){
        Reporter.log("Orbeon form not loading properly");
        throw new Exception("Orbeon form not loading properly");
		}								

	finally{
		d1.switchTo().defaultContent();
	}
}

@Test(priority = 22)
public static void DocumentTemplates() throws Exception
													{
	try{
	// Document Templates
	Thread.sleep(1000);
	((JavascriptExecutor)d1).executeScript(javaScript, d1.findElement(By.xpath("//span[contains(text(),'Administration')]")));
	Thread.sleep(2000);		
	d1.findElement(By.xpath("//a[contains(text(),'Document Templates')]")).click();
	WebElement templateframe = d1.findElement(By.id("onboardingFrame"));
	d1.switchTo().frame(templateframe);
	WebElement templateelmnt = d1.findElement(By.xpath("//span[contains(text(),'Add Template')]"));	
	String Documenttemplate = templateelmnt.getText();
	Assert.assertEquals(Documenttemplate,"Add Template", "Document Template page not loading properly");
	//softAssert.assertAll();					
	Reporter.log("Document Template page loaded successfully");		
	}catch(Exception e){
    Reporter.log("Document Template page not loading properly");
    throw new Exception("Document Template page not loading properly");
	}								
	finally{
	d1.switchTo().defaultContent();
	
}
		
}

@Test(priority = 23)
public static void ProcessDesigner() throws Exception
													{
	try{
	// Document Templates
	Thread.sleep(1000);
	((JavascriptExecutor)d1).executeScript(javaScript, d1.findElement(By.xpath("//span[contains(text(),'Administration')]")));
	Thread.sleep(2000);		
	d1.findElement(By.xpath("//a[contains(text(),'Process Designer')]")).click();
	WebElement templateframe = d1.findElement(By.id("raspframe"));
	d1.switchTo().frame(templateframe);
	//WebElement templateelmnt = d1.findElement(By.xpath("//td[contains(text(),'Workflow Library')]"));
	String templateelmnt = d1.findElement(By.xpath("//table/caption")).getText();
	//System.out.println(templateelmnt);
	Assert.assertEquals(templateelmnt,"WORKFLOW LIBRARY", "Process designer page not loading properly");
	//softAssert.assertAll();					
	Reporter.log("Process designer page loaded successfully");		
	}catch(Exception e){
    Reporter.log("Process designer page not loading properly");
    throw new Exception("Process designer page not loading properly");
	}								
	finally{
	d1.switchTo().defaultContent();
}
		
}

	@Test(priority = 24)
	public static void delete_candidate(
	)
					throws InterruptedException, FailedLoginException {
		try{
			
			   WebElement searchButton = d1.findElement(By.xpath("//div[4]/div/div[1]/div/nav/span/div/div/span/div/i"));
			   
			  Thread.sleep(2000);
			  searchButton.click();
			  WebElement searchInputBox  = d1.findElement(By.name("mainQuickSearch:quickSearchForm:quickSearchAutoComplete_input"));
			  searchInputBox.sendKeys("John Citizen");
			  searchInputBox.sendKeys(Keys.ENTER);
			  Thread.sleep(3000);
			  Actions actions = new Actions(d1);
			 WebElement rightClick =  d1.findElement(By.id("candForm:candidateGrid:0:candidateSearchNamePanel_content"));
			 actions.contextClick(rightClick).perform();
			 Thread.sleep(1000);
			 ((JavascriptExecutor)d1).executeScript(javaScript, d1.findElement(By.xpath("//span[contains(text(),'Manage Candidate Record')]")));
			 Thread.sleep(500);
				d1.findElement(By.xpath("//span[contains(text(),'Delete Candidate')]")).click();
				 Thread.sleep(3000);
				 WebElement deleteFrame = d1.findElement(By.id("DeleteCandidateActionFrame"));
				 d1.switchTo().frame(deleteFrame);
				 String deleteMessage = d1.findElement(By.xpath("//div[1]/div/div/div/div[1]/div[2]")).getText();
				 System.out.println(deleteMessage);
				 Assert.assertEquals(deleteMessage,"Delete Candidate from System", "Delete Candidate page not loading properly"); 
				 Reporter.log("Delete Candidate page loaded successfully");		
			}catch(Exception e){
			Reporter.log("Candidate Delete Failed");
			throw new FailedLoginException("Candidate Delete Not Successfull!");
			}	
	}
 
	 
	 @AfterSuite
	    public void tearDown() {
	       d1.quit();
	    }
	@DataProvider(name="kingsburystgV7-data-provider")
	public String[][] usernameAndPasswordDataProvider() {
		return ExcelReadUtil.readExcelInto2DArray("./kingsburystg.xlsx","kingsbury",5);
	}
}
