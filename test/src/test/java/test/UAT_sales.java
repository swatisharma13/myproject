package test;

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
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class UAT_sales {

	static WebDriver d1;
	static WebElement f1;
	 public static Assertion hardAssert = new Assertion();
	 public static SoftAssert softAssert = new SoftAssert();
public static String javaScript = "var evObj = document.createEvent('MouseEvents');" +
         "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);" +
           "arguments[0].dispatchEvent(evObj);";

/*@BeforeTest
public void startReport(){
	 //ExtentReports(String filePath,Boolean replaceExisting) 
	 //filepath - path of the file, in .htm or .html format - path where your report needs to generate. 
	 //replaceExisting - Setting to overwrite (TRUE) the existing file or append to it
	 //True (default): the file will be replaced with brand new markup, and all existing data will be lost. Use this option to create a brand new report
	 //False: existing data will remain, new tests will be appended to the existing report. If the the supplied path does not exist, a new file will be created.
	 extent = new ExtentReports (System.getProperty("user.dir") +"/test-output/STMExtentReport.html", true);
	 //extent.addSystemInfo("Environment","Environment Name")
	 extent
	                .addSystemInfo("Host Name", "Sales V7")
	                .addSystemInfo("Environment", "Automation Scripts")
	                .addSystemInfo("User Name", "Swati Sharma");
	                //loading the external xml file (i.e., extent-config.xml) which was placed under the base directory
	                //You could find the xml file below. Create xml file in your project and copy past the code mentioned below
	                extent.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));
	 }
	 
*/
   @Test(dataProvider = "salesV7-data-provider", priority =1)
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
			
			Reporter.log(UAT_sales.class.getSimpleName());
			Reporter.log("Browser Name:" + caps.getBrowserName().toUpperCase());
			Reporter.log(Environment);
			Reporter.log("Browser Version:"+caps.getVersion().toString());
	
	Reporter.log(Environment);
			Reporter.log("Browser Version:"+caps.getVersion().toString());
			 WebDriverManager.chromedriver().setup();
			  d1 = new ChromeDriver();
			  d1.get("https://sales-uat.springboard.com.au/login");
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
		Reporter.log(UAT_sales.class.getSimpleName()+"-"+UserName_BackEnd+"- Springboard Login Passed");
			}catch(Exception e){
				Reporter.log("Dashboard Load Failed");
				/*String URL = "https://vicboards-qa.hrxtech.com.au/rasp6/home.seam;jsessionid=A248A6C25F88E379463A978269FFE99B.node2?userId=1086203432&userName=vpsc_dedjtr&userOrgId=18545&appOrgId=18452&sessionId=1364422938950782470&login=true&in_redirect_url=&cid=200";
				Reporter.log("<a href=" + URL+ ">click to open screenshot</a>");*/				
				throw new FailedLoginException("Dashboard load failed");
				
			}
		
	}/*
    @Test(priority = 2)
   public static void notifications()
                              throws Exception {
      try {
             Thread.sleep(500);
             d1.findElement(By.id("headerMenuForm:nc-panel")).click();;
//d1.findElement(By.cssSelector(".notifications")).click();
Thread.sleep(1000);
   WebElement notifFrame = d1.findElement(By.id("notificationsFrame"));
   d1.switchTo().frame(notifFrame);
   WebElement notiElement = d1.findElement(By.xpath("//span[contains(text(),'Group by')]"));
   d1.findElement(By.xpath("//span[contains(text(),'Group by')]"));

String groupBy = notiElement.getText();


  softAssert.assertEquals(groupBy, "Group by","Job grid not loaded properly");
 softAssert.assertAll();
 Reporter.log("Notifications Tab Loaded");
// logger.log(LogStatus.PASS, "Test Case notifications Status is passed");
   }catch(Exception e){
          Reporter.log("Notifications Fail to Load");
          throw new Exception("Notifications Broken!");
          }      
          
      
finally{  
	d1.switchTo().defaultContent();
	d1.findElements(By.xpath("//*[@title=\"Close\"]")).get(1).click();
}
}*/
   

@Test(priority = 2)
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

 
  @Test(priority = 3)
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


	@Test(priority = 5, enabled= false)
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
	
	
	
	@Test(priority = 6)
	public static void JobRequest() throws Exception{
		try{
			Thread.sleep(1000);				
			((JavascriptExecutor)d1).executeScript(javaScript, d1.findElement(By.xpath("//span[contains(text(),'Job Requests')]")));
			Thread.sleep(1000);	
			d1.findElement(By.xpath("//span[contains(text(),'Job Requests')]")).click();					
			Thread.sleep(1000);	

			WebElement jobrequestframe = d1.findElement(By.id("jobRequestsFrame"));
			d1.switchTo().frame(jobrequestframe);
	
			WebElement jobreqelmnt = d1.findElement(By.xpath("//div[contains(text(),'Job Requests')]"));
			String jobrequest = jobreqelmnt.getText();
			softAssert.assertEquals(jobrequest,"Job Requests","Job Request page not loading properly");
			softAssert.assertAll();
			Reporter.log("Job Request page loads");
			}catch(Exception e){
			Reporter.log("Job Request page not loading properly");
			throw new Exception("Job Request page not loading properly");
			
			}
		finally{
			d1.switchTo().defaultContent();	
		}
	}
	
	@Test(priority = 6)
	public static void JobSearch() throws Exception {
		try{
		// Jobs Search
		Thread.sleep(1000);
		((JavascriptExecutor)d1).executeScript(javaScript, d1.findElement(By.xpath("//span[contains(text(),'Jobs')]")));
		Thread.sleep(1000);	
		((JavascriptExecutor)d1).executeScript(javaScript, d1.findElement(By.xpath("//a[contains(text(),'All Jobs')]")));
		Thread.sleep(1000);
		d1.findElement(By.xpath("//a[contains(text(),'All Jobs')]")).click();
		Thread.sleep(1000);	
		d1.findElement(By.className("searchPanelLink")).click();
		Thread.sleep(1000);
		d1.findElement(By.id("JobGrid:searchForm:jobrefSuggestion_input")).sendKeys("JOB/1532007");
		Thread.sleep(1000);
		
		//d1.findElement(By.xpath("//button[@class='ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only btn btn-primary']")).click();
	//	Thread.sleep(1000);
	//	WebElement jobsearchelmnt = d1.findElement(By.xpath("//a[contains(text(),'JOB/1532007')]"));	
	//	String jobsearch = jobsearchelmnt.getTagName();
	//	softAssert.assertEquals(jobsearch,"a", "Required Job Requisition not loading properly");
		//softAssert.assertAll();
		Reporter.log("Required Job Requisition loaded successfully");		
		}catch(Exception e){
        Reporter.log("Required Job Requisition not loading");
        throw new Exception("Required Job Requisition not loading");
		}					
		finally{	
			d1.switchTo().defaultContent();
		}	
	}
	
	@Test(priority = 7)
	public static void CandidateSearch() throws Exception{
		try{		
			Thread.sleep(500);
			((JavascriptExecutor)d1).executeScript(javaScript, d1.findElement(By.xpath("//span[contains(text(),'Candidates')]")));
			Thread.sleep(500);
			d1.findElement(By.xpath("//a[contains(text(),'Candidate Search')]")).click();
			Thread.sleep(500);
			d1.findElement(By.id("searchForm:candidateSearchPanelView:name")).sendKeys("Alexander");
			Thread.sleep(500);
			d1.findElement(By.id("searchForm:findCandidates")).click();
			Thread.sleep(500);
			WebElement candidateSearchResults = d1.findElement(By.id("candidateGridTitleForm:ReqTitleInCandidateGridTitle"));
			String candidateTitle = candidateSearchResults.getText();
			Thread.sleep(500);
			softAssert.assertEquals(candidateTitle, "Candidate Search Results","Candidate Search not loading properly");
			softAssert.assertAll();
		//	softAssert.assertEquals(inbox, "span","Email Inbox page not loading properly");
		//	softAssert.assertAll();
			Reporter.log("Candidate Search page loads");
			}catch(Exception e){
			Reporter.log("Candidate Search page not loading properly");
			throw new Exception("Candidate Search page not loading properly");
			}
		finally{
		
		d1.switchTo().defaultContent();	
		}
	}
	@Test(priority = 7)
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
			softAssert.assertEquals(inbox, "span","Email Inbox page not loading properly");
			softAssert.assertAll();
			Reporter.log("Email Inbox page loads");
			}catch(Exception e){
			Reporter.log("Email Inbox page not loading properly");
			throw new Exception("Email Inbox page not loading properly");
			}
		finally{
		
		d1.switchTo().defaultContent();	
		}
	}

	
	@Test(priority = 8)
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
		Thread.sleep(2000);	
		WebElement extschelmnt = d1.findElement(By.xpath("//a[contains(text(),'LinkedIn')]"));
		String externalsearch = extschelmnt.getText();
		softAssert.assertEquals(externalsearch,"LinkedIn","External Search page not loading properly");
		softAssert.assertAll();
		Reporter.log("External Search page loads");
		}catch(Exception e){
		Reporter.log("External Search page not loading properly");
		throw new Exception("External Search page not loading properly");
		}
		finally{
		d1.switchTo().defaultContent();	
		  }
		}	
					
@Test(priority = 9)
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


	
	@Test(priority = 10)
	public static void Companies() throws Exception{
		try{
		// Companies
			Thread.sleep(1000);
			((JavascriptExecutor)d1).executeScript(javaScript, d1.findElement(By.xpath("//span[contains(text(),'Companies')]")));
			Thread.sleep(1000);					
			d1.findElement(By.xpath("//a[contains(text(),'Company Search')]")).click();					
			Thread.sleep(1000);	
			WebElement companiesFrame = d1.findElement(By.id("companiesFrame"));
			d1.switchTo().frame(companiesFrame);
			Select lastUpdated = new Select (d1.findElement(By.id("companiesSearchForm:company-updated:companyUpdated")));
			lastUpdated.selectByVisibleText("Any Time");
			d1.findElement(By.id("companiesSearchForm:search")).click();
			WebElement companieselmnt = d1.findElement(By.xpath("//div[contains(text(),'Companies Search')]"));
			String Companies = companieselmnt.getText();
			softAssert.assertEquals(Companies, "Companies Search", "Companies page not loading properly");
			softAssert.assertAll();
			Reporter.log("Companies page loads");
			}catch(Exception e){
			Reporter.log("Companies page not loading properly");
			throw new Exception("Companies page not loading properly");
			}
		finally{
			d1.switchTo().defaultContent();	
		}
	}
	
@Test(priority = 11)
public static void EmailQueueAdmin() throws Exception{
	try{
		Thread.sleep(2000);
		((JavascriptExecutor)d1).executeScript(javaScript, d1.findElement(By.xpath("//span[contains(text(),'Administration')]")));
		Thread.sleep(2000);
		d1.findElement(By.xpath("//a[contains(text(),'Email Queue Admin')]")).click();
		Thread.sleep(2000);
		WebElement emailqueueadminelmnt = d1.findElement(By.xpath("//h2[contains(text(),'Administration/Email Queue Admin')]"));
		String EmailQueueAdmin = emailqueueadminelmnt.getText();
		softAssert.assertEquals(EmailQueueAdmin,"Administration/Email Queue Admin", "Email Queue Admin page not loading properly");
		softAssert.assertAll();
		Reporter.log("Email Queue Admin page loaded successfully");
		}catch(Exception e){
			Reporter.log("Email Queue Admin page not loading properly");
			throw new Exception("Email Queue Admin page not loading properly");
		}
}			



@Test(priority = 12)
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
	softAssert.assertEquals(Documenttemplate,"Add Template", "Document Template page not loading properly");
	softAssert.assertAll();
	Reporter.log("Document Template page loaded successfully");		
	}catch(Exception e){
    Reporter.log("Document Template page not loading properly");
    throw new Exception("Document Template page not loading properly");
	}								
	finally{
	d1.switchTo().defaultContent();
		
}
		
}
@Test(priority = 13)
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
	@Test(priority = 14)
	public static void Campaigns() throws Exception{
		try{
			Thread.sleep(1000);
			((JavascriptExecutor)d1).executeScript(javaScript, d1.findElement(By.xpath("//span[contains(text(),'Campaigns')]")));
			Thread.sleep(1000);					
			d1.findElement(By.xpath("//a[contains(text(),'Campaign Search')]")).click();					
			Thread.sleep(1000);	
			WebElement campaignsFrame = d1.findElement(By.id("campaignsFrame"));
			d1.switchTo().frame(campaignsFrame);
			WebElement Campaignselmnt = d1.findElement(By.xpath("//h3[contains(text(),'Search Campaigns')]"));
			String Campaigns = Campaignselmnt.getText();
			softAssert.assertEquals(Campaigns, "SEARCH CAMPAIGNS", "Campaigns page not loading properly");
			softAssert.assertAll();
			Reporter.log("Campaigns page loads");
			}catch(Exception e){
			Reporter.log("Campaigns page not loading properly");
			throw new Exception("Campaigns page not loading properly");
			}
		finally{
			d1.switchTo().defaultContent();	
			
		}
	}	
	
	@Test(priority = 15)
	public static void Tasks() throws Exception{
		try{
			Thread.sleep(1000);
			((JavascriptExecutor)d1).executeScript(javaScript, d1.findElement(By.xpath("//span[contains(text(),'Tasks')]")));
			Thread.sleep(1000);					
			d1.findElement(By.xpath("//a[contains(text(),'Task Search')]")).click();					
			Thread.sleep(1000);	
			WebElement tasksFrame = d1.findElement(By.id("raspframe"));
			d1.switchTo().frame(tasksFrame);
			WebElement tasksElmnt = d1.findElement(By.xpath("//h1[contains(text(),'Tasks Search')]"));
			String tasks = tasksElmnt.getText();
			softAssert.assertEquals(tasks, "Tasks Search", "Tasks page not loading properly");
			softAssert.assertAll();
			Reporter.log("Tasks page loads");
			}catch(Exception e){
			Reporter.log("Tasks page not loading properly");
			throw new Exception("Tasks page not loading properly");
			}
		finally{
			d1.switchTo().defaultContent();	
			
		}
	}	
	
	@Test(priority = 16)
	public static void FormsManagement() throws Exception{
		try{
			Thread.sleep(1000);
			((JavascriptExecutor)d1).executeScript(javaScript, d1.findElement(By.xpath("//span[contains(text(),'Administration')]")));	
			Thread.sleep(1000);
			d1.findElement(By.xpath("//a[contains(text(),'Forms Management')]")).click();					
			Thread.sleep(1000);	
			WebElement formsMgtFrame = d1.findElement(By.id("formsManagementFrame"));
			d1.switchTo().frame(formsMgtFrame);
			WebElement formsMgtElmnt = d1.findElement(By.xpath("//h3[contains(text(),'Search Forms')]"));
			String forms = formsMgtElmnt.getText();
			softAssert.assertEquals(forms, "Search Forms", "Forms Management page not loading properly");
			softAssert.assertAll();
			Reporter.log("Forms Management Page page loads");
			WebElement menuItemUL = d1.findElement(By.xpath("//div[starts-with(@id,'customFormSearchForm:recipientTypeMenu')]"));
			menuItemUL.click();
			d1.findElement(By.xpath("//li[starts-with(@id,'customFormSearchForm:recipientTypeMenu_2')]")).click();
			d1.findElement(By.xpath("//span[contains(text(),'Search')]")).click();
			Thread.sleep(10000);
			d1.findElement(By.cssSelector("#customFormSearchForm\\:formsGrid\\:0\\:editButtonRedirect")).click();
			Thread.sleep(2000);
			WebElement editFormElement = d1.findElement(By.xpath("//h3[contains(text(),'Edit/View Form')]"));
			String editViewForm = editFormElement.getTagName();
			softAssert.assertEquals(editViewForm,"h3", "Edit/View Form Management Page not loading properly");
			softAssert.assertAll();			
			Reporter.log("Edit/View Form Management Page loaded successfully");
			
				}catch(Exception e){
			Reporter.log("Forms Management Page not loading properly");
			throw new Exception("Forms Management Page not loading properly");
			}
		finally{
			d1.switchTo().defaultContent();	
			
		}
	}	

	@Test(priority = 17)
	public static void Holiday() throws Exception{
		try{
			Thread.sleep(1000);
			((JavascriptExecutor)d1).executeScript(javaScript, d1.findElement(By.xpath("//span[contains(text(),'Administration')]")));
			Thread.sleep(1000);				
			d1.findElement(By.xpath("//a[contains(text(),'Admin Console')]")).click();
			Thread.sleep(1000);
			d1.findElement(By.xpath("//a[contains(text(),'Holiday')]")).click();
			Thread.sleep(1000);
			WebElement f1 = d1.findElement(By.id("holidayAdmin"));
			d1.switchTo().frame(f1);
			WebElement holidayelmnt = d1.findElement(By.xpath("//h3[contains(text(),'Add a new holiday')]"));
			String Holiday = holidayelmnt.getTagName();
			softAssert.assertEquals(Holiday,"h3", "Holiday tab not loading properly");
			softAssert.assertAll();			
			Reporter.log("Holiday tab loaded successfully");
			}catch(Exception e){
				Reporter.log("Holiday tab not loading properly");
				throw new Exception("Holiday tab not loading properly");
			}
		finally{
		d1.switchTo().defaultContent();
		}
	}
	
	@Test(priority = 18)
	public static void Locations() throws Exception{
		try{			
			Thread.sleep(1000);
			d1.findElement(By.xpath("//a[contains(text(),'Locations')]")).click();
			Thread.sleep(1000);
			WebElement interviewLocations = d1.findElement(By.id("interviewLocations"));
			d1.switchTo().frame(interviewLocations);
			WebElement Locationserelmnt = d1.findElement(By.xpath("//span[@class='ui-icon ui-icon-seek-first']"));
			String Locations = Locationserelmnt.getTagName();
			softAssert.assertEquals(Locations,"span", "Locations tab not loading properly");
			softAssert.assertAll();
			Reporter.log("Locations tab loaded successfully");
			}catch(Exception e){
				Reporter.log("Locations tab not loading properly");	
				throw new Exception("Locations tab not loading properly");	
				
			}
		finally{
			d1.switchTo().defaultContent();	
			
		}
	}
	
	@Test(priority = 19)
	public static void TalentFolderProcesses()
					throws Exception {
		try{			
			Thread.sleep(1000);
			
			d1.findElement(By.xpath("//a[contains(text(),'Talent Folder Processes')]")).click();
			Thread.sleep(1000);
			WebElement TalentFolderelmnt =d1.findElement(By.xpath("//a[contains(text(),'Processes Configuration')]"));
			String TalentFolder = TalentFolderelmnt.getTagName();
			softAssert.assertEquals(TalentFolder,"a", "Talent Folder Processes tab not loading properly");
			softAssert.assertAll();
			Reporter.log("Talent Folder Processes tab loaded successfully");
			}catch(Exception e){
				Reporter.log("Talent Folder Processes tab not loading properly");
				throw new Exception("Talent Folder Processes tab not loading properly");
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
	public static void FormsAndFrontEndAdmin() throws Exception
														{
		try{
		// Document Templates
		Thread.sleep(1000);
		((JavascriptExecutor)d1).executeScript(javaScript, d1.findElement(By.xpath("//span[contains(text(),'Administration')]")));
		Thread.sleep(2000);		
		d1.findElement(By.xpath("//a[contains(text(),'Forms And Front-end Admin')]")).click();
		Thread.sleep(500);
		WebElement formElement  = d1.findElement(By.xpath("//span[contains(text(),'Create new Form')]"));
		String formsBuilderElement = formElement.getText();
		Assert.assertEquals(formsBuilderElement,"CREATE NEW FORM", "Forms and Front-End Admin page not loading properly");
		softAssert.assertAll();					
		Reporter.log("Forms and Front-End Admin  page loaded successfully");		
		}catch(Exception e){
	    Reporter.log("Forms and Front-End Admin  page not loading properly");
	    throw new Exception("Forms and Front-End Admin  page not loading properly");
		}								
		finally{
		d1.switchTo().defaultContent();
	}
			
	}

	/*	@Test(priority = 25)
		public static void delete_candidate(
		)
						throws InterruptedException, FailedLoginException {
			try{
				
				   WebElement searchButton = d1.findElement(By.xpath("//div[4]/div/div[1]/div/nav/span/div/div/span/div/i"));
				   
				  Thread.sleep(2000);
				  searchButton.click();
				  WebElement searchInputBox  = d1.findElement(By.name("mainQuickSearch:quickSearchForm:quickSearchAutoComplete_input"));
				  searchInputBox.sendKeys("Alexander Bell");
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
	 
	/* WIP 
	 @Test(priority = 17)
	public static void delete_candidate() 
			throws Exception {
		try{
			Thread.sleep(1000);
			d1.findElement(By.xpath("//i[@title='Candidate List']")).click();
			Thread.sleep(2000);		
			d1.findElement(By.id("candForm:candidateGrid:0:actionMenuButton")).click();
			//((JavascriptExecutor)d1).executeScript(javaScript, d1.findElement(By.xpath("//span[contains(text(),'Manage Candidate Record')]")));
			//Thread.sleep(1000);	
			//System.out.println("Done Mouse hover on 'Manage Candidate Record' from Menu");
			//((JavascriptExecutor)d1).executeScript(javaScript, d1.findElement(By.xpath("//span[contains(text(),'Job Delete Candidate')]")));
			//System.out.println("Selected 'Delete Candidate' from Menu");
			//Thread.sleep(3000);
					
			Actions action = new Actions(d1);
			WebElement manageCandOption = d1.findElement(By.xpath("//span[contains(text(),'Manage Candidate Record')]"));
			action.moveToElement(manageCandOption).moveToElement(d1.findElement(By.xpath("//span[contains(text(),'Delete Candidate')]"))).click().build().perform();
			Thread.sleep(5000);	
			///html/body/div[52]/ul/li[10]/a/span[1]
			//#candActionsSelector\:actionSelectorMenu > ul:nth-child(2) > li:nth-child(10) > a:nth-child(1)
			//actions.moveToElement(manageCandOption).perform();
			//System.out.println("Done Mouse hover on 'Manage Candidate Record' from Menu");
			//Thread.sleep(1000);
		   // d1.findElement(By.xpath("//div[@id='candActionsSelector:actionSelectorMenu']/ul/li[8]/ul/li[8]/a/span")).click();
		//	WebElement dltCandOption = d1.findElement(By.xpath("//span[contains(text(),'Delete Candidate']"));
		//	dltCandOption.click();
		//	System.out.println("Selected 'Delete Candidate' from Menu");
		//	Thread.sleep(3000);
		//	WebElement deleteFrame = d1.findElement(By.xpath("div[@id='j_idt483:DeleteCandidateActionIFramePanel']"));
		//	d1.switchTo().frame("deleteFrame");
		//	Thread.sleep(1000);
		//	WebElement dltCandidate = d1.findElement(By.xpath("div[@class= 'Delete Candidate from System']"));
		//	String deleteCand = dltCandidate.getTagName();
		//	softAssert.assertEquals(deleteCand,"span", "Delete Candidate not loading properly");
		//	softAssert.assertAll();			
			Reporter.log("Candidate Delete Successfull"); 	
			}catch(Exception e){
			Reporter.log("Candidate Delete Failed");
			throw new FailedLoginException("Candidate Delete Not Successfull!");
			}	
		finally{
			d1.switchTo().defaultContent();	
			d1.close();
		}
	} 
	
*/
	@Test(priority = 25)
	public static void delete_candidate(
	)
					throws InterruptedException, FailedLoginException {
		try{
			
			   WebElement searchButton = d1.findElement(By.xpath("//div[4]/div/div[1]/div/nav/span/div/div/span/div/i"));
			   
			  Thread.sleep(2000);
			  searchButton.click();
			  WebElement searchInputBox  = d1.findElement(By.name("mainQuickSearch:quickSearchForm:quickSearchAutoComplete_input"));
			  searchInputBox.sendKeys("Basil Pimlico");
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
	
	/*OLD one 
	@Test(priority = 17)
	public static void delete_candidate(
)
					throws InterruptedException, FailedLoginException {
		try{
			//Candidate Experience
			cr.clickSidebarMenuItem(d1,"Candidates");
			Thread.sleep(2000);
			d1.findElement(By.xpath("//input[@id='candidateSearch:searchForm:search-criteria:name']")).sendKeys("Micahel Kors");
			Thread.sleep(2000);
			d1.findElement(By.xpath("//input[@id='candidateSearch:searchForm:search-actions:findCandidates']")).click();
			Thread.sleep(3000);
			d1.switchTo().defaultContent();					
			//@formatter:off
			Optional <WebElement> menuItem_assesment = 				
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
	
	
		@Test(priority = 14)
	public static void HiringManagerFeedback() throws Exception{
		try{
			// HMF
			Thread.sleep(1000);
			((JavascriptExecutor)d1).executeScript(javaScript, d1.findElement(By.xpath("//span[contains(text(),'Administration')]")));
			Thread.sleep(1000);				
			d1.findElement(By.xpath("//a[contains(text(),'Admin Console')]")).click();
			Thread.sleep(1000);
			d1.findElement(By.xpath("//a[contains(text(),'Hiring Manager Feedback')]")).click();
			Thread.sleep(1000);
			WebElement hmFeedbackAdmin = d1.findElement(By.id("hmFeedbackAdmin"));
			d1.switchTo().frame(hmFeedbackAdmin);
			WebElement HiringManagerFeedbackelmnt = d1.findElement(By.xpath("//span[contains(text(),'Hiring Manager Feedback Administration')]"));
			String HiringManagerFeedback = HiringManagerFeedbackelmnt.getTagName();
			softAssert.assertEquals(HiringManagerFeedback,"span", "Hiring Manager Feedback tab not loading properly");
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
	 @Test(priority = 15)
	public static void CandidateAccounts() throws Exception{
		try{Thread.sleep(1000);
		((JavascriptExecutor)d1).executeScript(javaScript, d1.findElement(By.xpath("//span[contains(text(),'Administration')]")));
		Thread.sleep(1000);
		d1.findElement(By.xpath("//a[contains(text(),'Admin Console')]")).click();
		Thread.sleep(1000);			
			Thread.sleep(1000);
			d1.findElement(By.xpath("//a[contains(text(),'Candidate Accounts')]")).click();
			Thread.sleep(1000);
			WebElement CandidateAccountserelmnt = d1.findElement(By.xpath("//span[contains(text(),'First Name')]"));
			String CandidateAccounts = CandidateAccountserelmnt.getTagName();
			softAssert.assertEquals(CandidateAccounts,"span", "Candidate Accounts tab not loading properly");
			softAssert.assertAll();
			Reporter.log("Candidate Accounts tab loaded successfully");
			}catch(Exception e){
				Reporter.log("Candidate Accounts tab not loading properly");	
				throw new Exception("Candidate Accounts tab not loading properly");
			}
	}
	
	@Test(priority = 16)
	public static void SMSAccounts() throws Exception{
		try{			
			Thread.sleep(1000);
			d1.findElement(By.xpath("//a[contains(text(),'SMS Accounts')]")).click();
			Thread.sleep(1000);
			WebElement SMSAccountselmnt = d1.findElement(By.xpath("//div[contains(text(),'SMS Accounts')]"));
			String SMSAccounts = SMSAccountselmnt.getTagName();
			softAssert.assertEquals(SMSAccounts,"div", "SMS Accounts tab not loading properly");
			softAssert.assertAll();
			Reporter.log("SMS Accounts tab loaded successfully");
			}catch(Exception e){
				Reporter.log("SMS Accounts tab not loading properly");	
				throw new Exception("SMS Accounts tab not loading properly");
			}
	}
	@Test(priority = 17)
	public static void Broadcasts() throws Exception{
		try{	
			try{Thread.sleep(1000);
			((JavascriptExecutor)d1).executeScript(javaScript, d1.findElement(By.xpath("//span[contains(text(),'Administration')]")));
			Thread.sleep(1000);
			d1.findElement(By.xpath("//a[contains(text(),'Admin Console')]")).click();
			Thread.sleep(1000);
			d1.findElement(By.xpath("//a[contains(text(),'Broadcasts')]")).click();
			Thread.sleep(1000);
			WebElement Broadcastselmnt = d1.findElement(By.xpath("//h4[contains(text(),'Generate a notification to Springboard users')]"));
			String Broadcasts = Broadcastselmnt.getTagName();
			softAssert.assertEquals(Broadcasts,"h4", "Broadcasts tab not loading properly");
			softAssert.assertAll();
			Reporter.log("Broadcasts tab loaded successfully");
			}catch(Exception e){
				Reporter.log("Broadcasts tab not loading properly");	
				throw new Exception("Broadcasts tab not loading properly");
				
			}
		}		
			finally{
				d1.switchTo().defaultContent();				
			}
		
		
	}
	
	 @Test(priority = 19)
	public static void ApplicationAdmin() throws Exception{
		try{			
			Thread.sleep(1000);
			d1.findElement(By.xpath("//a[contains(text(),'Application Admin')]")).click();
			Thread.sleep(1000);
			WebElement ApplicationAdminF = d1.findElement(By.xpath("//div[@class='adminConsole-Tabcontent']/iframe"));
			d1.switchTo().frame(ApplicationAdminF);
			WebElement ApplicationAdminelmnt = d1.findElement(By.xpath("//h3[contains(text(),'Manage Items')]"));
			String ApplicationAdmin = ApplicationAdminelmnt.getTagName();
			softAssert.assertEquals(ApplicationAdmin,"h3", "Application Admin tab not loading properly");
			softAssert.assertAll();
			Reporter.log("Application Admin tab loaded successfully");
			}catch(Exception e){
				Reporter.log("Application Admin tab not loading properly");	
				throw new Exception("Application Admin tab not loading properly");
			}
		finally{
			d1.switchTo().defaultContent();
		}
	}*/
	
	/*@Test(priority = 20)
	public static void Holiday() throws Exception{
		try{
			Thread.sleep(1000);
			d1.findElement(By.xpath("//a[contains(text(),'Holiday')]")).click();
			Thread.sleep(1000);
			WebElement f1 = d1.findElement(By.id("holidayAdmin"));
			d1.switchTo().frame(f1);
			WebElement holidayelmnt = d1.findElement(By.xpath("//h3[contains(text(),'Add a new holiday')]"));
			String Holiday = holidayelmnt.getTagName();
			softAssert.assertEquals(Holiday,"h3", "Holiday tab not loading properly");
			softAssert.assertAll();			
			Reporter.log("Holiday tab loaded successfully");
			}catch(Exception e){
				Reporter.log("Holiday tab not loading properly");
				throw new Exception("Holiday tab not loading properly");
			}
		finally{
		d1.switchTo().defaultContent();
		}
	}*/
	
	
	/*@Test(priority = 24)
	public static void FormsSearch() throws Exception {
		try{
			// Forms Search
			Thread.sleep(2000);
			((JavascriptExecutor)d1).executeScript(javaScript, d1.findElement(By.xpath("//span[contains(text(),'Administration')]")));
			Thread.sleep(2000);	
			((JavascriptExecutor)d1).executeScript(javaScript, d1.findElement(By.xpath("//a[contains(text(),'Forms Management')]")));
			Thread.sleep(2000);
			d1.findElement(By.xpath("//a[contains(text(),'Forms Management')]")).click();
			Thread.sleep(2000);	
			WebElement formssearchframe = d1.findElement(By.id("formsManagementFrame"));
			d1.switchTo().frame(formssearchframe);
			d1.findElement(By.id("customFormSearchForm:formNameAutocomplete_input")).sendKeys("Test Form-11");
			Thread.sleep(1000);
			d1.findElement(By.id("customFormSearchForm:formNameAutocomplete_input")).click();
			Thread.sleep(1000);
			d1.findElement(By.id("customFormSearchForm:search")).click();
			Thread.sleep(2000);
			d1.findElement(By.xpath("//a[@id='customFormSearchForm:formsGrid:0:editButtonRedirect']")).click();
			Thread.sleep(2000);
			WebElement editorbeonframe = d1.findElement(By.id("edit-orbeon-form"));
			d1.switchTo().frame(editorbeonframe);
			WebElement orbeonelmnt = d1.findElement(By.xpath("//span[contains(text(),'Test Form-11')]"));	
			String editorbeon = orbeonelmnt.getTagName();
			softAssert.assertEquals(editorbeon,"span", "Forms Management page page not loading properly");
			softAssert.assertAll();					
			Reporter.log("Forms Management page loaded successfully");				
		}catch(Exception e){
        Reporter.log("Forms Management page not loading properly");
        throw new Exception("Forms Management page not loading properly");
		}					
		finally{	
			d1.switchTo().defaultContent();
			d1.close();	
		}	
	}		
	*/
	
	/*	@Test(priority = 15)
	public static void InterviewAutoCommunication() throws Exception {
		try{			
			Thread.sleep(1000);
			d1.findElement(By.xpath("//a[contains(text(),'Interview Auto Communication')]")).click();
			Thread.sleep(1000);
			WebElement InterviewAutoCommunicationerelmnt = d1.findElement(By.xpath("//span[contains(text(),'Automatic Notification :')]"));
			String InterviewAutoCommunication = InterviewAutoCommunicationerelmnt.getText();
			softAssert.assertEquals(InterviewAutoCommunication,"Automatic Notification :", "Interview Auto Communication tab not loading properly");
			softAssert.assertAll();
			Reporter.log("Interview Auto Communication tab loaded successfully");
			}catch(Exception e){
				Reporter.log("Interview Auto Communication tab not loading properly");	
				throw new Exception("Interview Auto Communication tab not loading properly");			
			}
	}
		
	
	@Test(priority = 12)
	public static void RTFTemplateAdmin() throws Exception {
		try{
			Thread.sleep(1000);
			((JavascriptExecutor)d1).executeScript(javaScript, d1.findElement(By.xpath("//span[contains(text(),'Administration')]")));
			Thread.sleep(1000);
			d1.findElement(By.xpath("//a[contains(text(),'Admin Console')]")).click();
			Thread.sleep(1000);
			WebElement RTFeselmnt =d1.findElement(By.xpath("//div[contains(text(),'RTF Template Administration')]"));
			String RTFTemplateAdmin = RTFeselmnt.getTagName();
			softAssert.assertEquals(RTFTemplateAdmin,"div", "RTF Template Admin tab not loading properly");
			softAssert.assertAll();
			Reporter.log("RTF Template Admin tab loaded successfully");
			}catch(Exception e){
				Reporter.log("RTF Template Admin tab not loading properly");
				throw new Exception("RTF Template Admin tab not loading properly");
			}
	}

	@Test(priority = 13)
	public static void DownloadRTFTemplateBuilder() throws Exception{
		try{			
			Thread.sleep(1000);
			d1.findElement(By.xpath("//a[contains(text(),'Download RTF Template Builder')]")).click();
			Thread.sleep(1000);
			WebElement RTFbuildereselmnt =d1.findElement(By.xpath("//div[contains(text(),' RTF Template Builder')]"));
			String RTFTemplateBuilder = RTFbuildereselmnt.getTagName();
			softAssert.assertEquals(RTFTemplateBuilder,"div", "Download RTF Template Builder tab not loading properly");
			softAssert.assertAll();
			Reporter.log("Download RTF Template Builder tab loaded successfully");
			}catch(Exception e){
				Reporter.log("Download RTF Template Builder tab not loading properly");
				throw new Exception("Download RTF Template Builder tab not loading properly");
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
	
	@Test(priority = 5)
	public static void boardsManagement()
					throws Exception {
		// Boards Management
		try{
		Thread.sleep(1000);
		((JavascriptExecutor)d1).executeScript(javaScript, d1.findElement(By.xpath("//span[contains(text(),'Boards Management')]")));
		Thread.sleep(1000);
		d1.findElement(By.xpath("//a[contains(text(),'Board Search')]")).click();			
		WebElement boardseselmnt = d1.findElement(By.xpath("//h[contains(text(),'Boards Management')]"));
		String Boards = boardseselmnt.getText();		
		softAssert.assertEquals(Boards,"Boards Management/Board Search","Boards Management page not loading properly");
		softAssert.assertAll();
		Reporter.log("Boards Management page loads");
		}catch(Exception e){
			Reporter.log("Boards Management page not loading properly");
			throw new Exception("Boards Management page not loading properly");
			
			}
		finally{
			d1.switchTo().defaultContent();				
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
	}
	 
		@AfterMethod
		 public void getResult(ITestResult result){
		 if(result.getStatus() == ITestResult.FAILURE){
		 logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getName());
		 logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getThrowable());
		 }else if(result.getStatus() == ITestResult.SKIP){
		 logger.log(LogStatus.SKIP, "Test Case Skipped is "+result.getName());
		 }
		 // ending test
		 //endTest(logger) : It ends the current test and prepares to create HTML report
		 extent.endTest(logger);
		 }
		 @AfterTest
		 public void endReport(){
		 // writing everything to document
		 //flush() - to write or update test information to your report. 
		                extent.flush();
		                //Call close() at the very end of your session to clear all resources. 
		                //If any of your test ended abruptly causing any side-affects (not all logs sent to ExtentReports, information missing), this method will ensure that the test is still appended to the report with a warning message.
		                //You should call close() only once, at the very end (in @AfterSuite for example) as it closes the underlying stream. 
		                //Once this method is called, calling any Extent method will throw an error.
		                //close() - To close all the operation
		                extent.close();
		    }
*/
	 @AfterClass
	    public static void tearDown() {
	       d1.quit();
	    
	    }
	@DataProvider(name="salesV7-data-provider")
	public String[][] usernameAndPasswordDataProvider() {
		return ExcelReadUtil.readExcelInto2DArray("./uat.xlsx","sales",5);
	}
}
