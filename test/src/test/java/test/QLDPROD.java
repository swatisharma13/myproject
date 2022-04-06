package test;

import java.util.concurrent.TimeUnit;

import javax.security.auth.login.FailedLoginException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class QLDPROD {
	static WebDriver d1;
	public static String javaScript = "var evObj = document.createEvent('MouseEvents');" +
	         "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);" +
	           "arguments[0].dispatchEvent(evObj);";

	@Test(dataProvider = "prodv6-data-provider", priority =1)
	public static void login_backend(
			String UserName_BackEnd,
			String Password_BackEnd,
			String profileName,
			String maximize,
			String Environment)	throws InterruptedException {

		DesiredCapabilities caps = DesiredCapabilities.chrome();
		caps.setBrowserName("chrome");
		WebDriverManager.chromedriver().setup();
		d1 = new ChromeDriver();
		Reporter.log(
				"===============================================================");
			Reporter.log(QLDPROD.class.getSimpleName());
			Reporter.log("Browser Name:" + caps.getBrowserName().toUpperCase());
			// Reporter.log("Browser Version:"+caps.getVersion().toString());
			d1.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
			//d1.get("https://smartjobs.qld.gov.au/jobtools/jncustomorglogin.checkuser?in_organid=14903&in_username=v7_HRX&in_password=test5000");
			d1.get("https://smartjobs.govnet.qld.gov.au/jobtools/jncustomorglogin.logout?in_organid=15306");
			d1.manage().window().maximize();
		// throw new SkipException("Skipping Test");
		d1.findElement(By.xpath("//input[@name='in_username']")).sendKeys(UserName_BackEnd);
		d1.findElement(By.xpath("//input[@type='PASSWORD']")).sendKeys(Password_BackEnd);
		d1.findElement(By.xpath("//input[@value='Login']")).click();
		Thread.sleep(1500);
		// Validation for Login Check
		/*Assert.assertEquals("Springboard", d1.getTitle());
		Reporter.log("Login Passed");*/
		
		try{
			//Thread.sleep(2000);
			WebElement f1 = d1.findElement(By.id("raspframeEncoded"));
			d1.switchTo().frame(f1);
			WebElement f2 = d1.findElement(By.id("dashboardFrame"));
			d1.switchTo().frame(f2);
			// Validation for Login Check
			d1.findElement(By.xpath("//span[contains(text(),'My Jobs')]"));
			//Assert.assertEquals("Springboard", d1.getTitle());
			Reporter.log(QLDPROD.class.getSimpleName()+"-"+UserName_BackEnd+"- Springboard Login Passed");
			}catch(Exception e){
				Reporter.log("Dashboard Load Failed");
				throw new NoSuchElementException("Dashboard load failed");
				
			}
	}

	
	@Test(priority = 2)
	public static void Tasks() throws InterruptedException, NoSuchElementException {
		// Tasks
		cr.clickSidebarMenuItem(d1, "Tasks");
	try{	
		
		WebElement f1 = d1.findElement(By.id("raspframe"));
		d1.switchTo().frame(f1);		
		d1.findElement(By.xpath("//label[contains(text(),'Task Type')]"));
	    Reporter.log("Tasks Page loaded Successfully");
	}catch(Exception e){	
	        Reporter.log("Tasks Page Is Broken");
		throw new NoSuchElementException("Tasks Page fails to load");
	}	
	d1.switchTo().defaultContent();
	}
	
	
	@Test(priority = 3)
	public static void TalentFolders() throws InterruptedException, NoSuchElementException {
		// Talent Folders
		cr.clickSidebarMenuItem(d1, "Talent Folders");
	try{	
			
		d1.findElement(By.xpath("//label[contains(text(),'Folder Name')]"));
	    Reporter.log("Talent Folders Page loaded Successfully");
	}catch(Exception e){	
	        Reporter.log("Talent Folders Page Is Broken");
		throw new NoSuchElementException("Talent Folders Page fails to load");
	}	
}

	@Test(priority = 4)
	public static void Jobs()
					throws InterruptedException, NoSuchElementException {
		try{
		//Jobs		
		cr.clickSidebarMenuItem(d1,"Jobs");
		WebElement f1 = d1.findElement(By.id("raspframe"));
		d1.switchTo().frame(f1);
		d1.findElement(By.xpath("//input[@name='in_search']"));
		Reporter.log("Job Page Loaded");
		}catch(Exception e){
		Reporter.log("Job Page Fail to Load");
		throw new NoSuchElementException("Job Page Broken!");
		}			
		d1.switchTo().defaultContent();
	}	
	
	@Test(priority = 5)
	public static void Advertisements()
					throws InterruptedException, NoSuchElementException {
		try{
		//Advertisements
		
		cr.clickSidebarMenuItem(d1,"Advertisements");
		Thread.sleep(2000);
		WebElement f1 = d1.findElement(By.id("raspframe"));
		d1.switchTo().frame(f1);
		d1.findElement(By.xpath("//input[@name='in_jobRef']"));
		Reporter.log("Advertisements Page Loaded");
		}catch(Exception e){
		Reporter.log("Advertisements Page Fail to Load");
		throw new NoSuchElementException("Advertisements Page Broken!");
		}			
		d1.switchTo().defaultContent();
	}	
	
	@Test(priority = 6)
	public static void Candidates()
					throws InterruptedException, NoSuchElementException {
		try{
		//Candidates	
		cr.clickSidebarMenuItem(d1,"Candidates");
		d1.findElement(By.xpath("//label[contains(text(),'Include Keyword in Context:')]"));
		Reporter.log("Candidates Page Loaded");
		}catch(Exception e){
		Reporter.log("Candidates Page Faile to Load");
		throw new NoSuchElementException("Candidates Page Broken!");
		}			
	}	
	
	@Test(priority = 7)
	public static void Campaigns()
					throws InterruptedException, NoSuchElementException {
		try{
		//Campaigns	
		cr.clickSidebarMenuItem(d1,"Campaigns");
		WebElement f1 = d1.findElement(By.id("campaignsFrame"));
		d1.switchTo().frame(f1);
		d1.findElement(By.xpath("//h3[contains(text(),'Search Campaigns')]"));
		Reporter.log("Campaigns Page Loaded");
		}catch(Exception e){
		Reporter.log("Campaigns Page Fail to Load");
		throw new NoSuchElementException("Campaigns Page Broken!");
		}			
		d1.switchTo().defaultContent();
	}		
	
	@Test(priority = 8)
	public static void Interviews() throws InterruptedException, NoSuchElementException {
		// Interviews
		cr.clickSidebarMenuItem(d1, "Interviews");
	try{	
		
		WebElement f1 = d1.findElement(By.id("interviewSchedulerFrame"));
		d1.switchTo().frame(f1);		
		d1.findElement(By.xpath("//h3[contains(text(),'Search Interviews')]"));
	    Reporter.log("Interviews Page loaded Successfully");
	}catch(Exception e){	
	        Reporter.log("Interviews Page Is Broken");
		throw new NoSuchElementException("Interview Page fails to load");
	}	
	d1.switchTo().defaultContent();
	}
	
	@Test(priority = 9)
	public static void Reports()
					throws InterruptedException, NoSuchElementException {
		try{
		//Reports	
		cr.clickSidebarMenuItem(d1,"Reports");
		WebElement f1 = d1.findElement(By.id("raspframe"));
		d1.switchTo().frame(f1);
		d1.findElement(By.xpath("//h2[contains(text(),'My Recent Reports')]"));
		Reporter.log("Reports Page Loaded");
		}catch(Exception e){
		Reporter.log("Reports Page Faile to Load");
		throw new NoSuchElementException("Reports Page Broken!");
		}			
		d1.switchTo().defaultContent();
	}
	
	@Test(priority = 10)
	public static void Administration()
					throws InterruptedException, NoSuchElementException {
		try{
		//Administration	
		
		cr.clickSidebarMenuItem(d1,"Administration");
		Thread.sleep(2000);
		WebElement f1 = d1.findElement(By.id("raspframe"));
		d1.switchTo().frame(f1);
		d1.findElement(By.xpath("//input[@name='in_old_pwd']"));
		Reporter.log("Administration Page Loaded");
		}catch(Exception e){
		Reporter.log("Administration Page Faile to Load");
		throw new NoSuchElementException("Administration Page Broken!");
		}			
		d1.switchTo().defaultContent();
	}
	
	@Test(priority = 11)
	public static void Candidate_Tabs()
					throws InterruptedException, NoSuchElementException {
		try{
			//Candidate Tabs
			cr.clickSidebarMenuItem(d1,"Candidates");
			Thread.sleep(1000);
			d1.findElement(By.xpath("//input[@id='candidateSearch:searchForm:search-criteria:name']")).sendKeys("Nasir Shah");
			Thread.sleep(1000);
			d1.findElement(By.xpath("//input[@id='candidateSearch:searchForm:search-actions:findCandidates']")).click();
			Thread.sleep(1000);
			/*d1.switchTo().defaultContent();					
			//@formatter:off
			Optional<WebElement> menuItem_assesment = 				
					d1.findElements(By.xpath("//span[contains(text(),'My Folders')]")).stream().filter(p -> p.isDisplayed()).findAny();
					//@formatter:on				
			menuItem_assesment.ifPresent(p -> p.click());
			Thread.sleep(1000);*/
			d1.findElement(By.xpath("//input[starts-with(@id, 'candForm:candidateGrid:')]")).click();
			Thread.sleep(1000);
			d1.findElement(By.xpath("//a[@id='candidateGridTitleForm:switchToReviewModeLink']/img")).click();
			Thread.sleep(2000);
			//WebElement f1 = d1.findElement(By.id("attachementsFrame"));
			//d1.switchTo().frame(f1);
			//d1.findElement(By.xpath("//button[@id='viewFind']"));
			/*Thread.sleep(2000);
			d1.findElement(By.xpath("//tb[contains(text(),'Resume']"));*/
			Reporter.log("Resume Tab Successfully Loaded");
			}catch(Exception e){
			Reporter.log("Resume Tab Fail to Load");
			throw new NoSuchElementException("Resume Tab Broken!");
			}	
			try{
			d1.switchTo().defaultContent();
			Thread.sleep(2000);
			//Details Tab
			d1.findElement(By.xpath("//td[@id='DETAILS_lbl']/table/tbody/tr/td[2]")).click();
			d1.findElement(By.xpath("//th[contains(text(),'Personal Details')]"));
			Reporter.log("Details Tab Successfully Loaded");
			}catch(Exception e){
			Reporter.log("Details Tab Fail to Load");
			throw new NoSuchElementException("Details Tab Broken!");
			}	
			try{
			Thread.sleep(2000);
			//Forms Tab
			d1.findElement(By.xpath("//td[@id='APPLICATIONS_lbl']/table/tbody/tr/td[2]")).click();
			WebElement f1 = d1.findElement(By.id("applicationFrame"));
			d1.switchTo().frame(f1);
			d1.findElement(By.xpath("//h1[contains(text(),'My applications')]"));
			Reporter.log("Forms Tab Successfully Loaded");
			}catch(Exception e){
			Reporter.log("Forms Tab Fail to Load");
			throw new NoSuchElementException("Forms Tab Broken!");
			}
			try{
			d1.switchTo().defaultContent();
			Thread.sleep(2000);
			//Notes Tab
			d1.findElement(By.xpath("//td[@id='SUMMARY_lbl']/table/tbody/tr/td[2]")).click();		
			d1.findElement(By.xpath("//span[contains(text(),'Scheduled Tasks')]"));
			Reporter.log("Notes Tab Successfully Loaded");
			}catch(Exception e){
			Reporter.log("Notes Tab Fail to Load");
			throw new NoSuchElementException("Notes Tab Broken!");
			}
			try{
			Thread.sleep(2000);
			//Experience Tab
			d1.findElement(By.xpath("//td[@id='DOCUMENTS_lbl']/table/tbody/tr/td[2]")).click();
			d1.findElement(By.xpath("//th[contains(text(),'Document name')]"));
			Reporter.log("Docs Tab Successfully Loaded");
			}catch(Exception e){
			Reporter.log("Docs Tab Fail to Load");
			throw new NoSuchElementException("Docs Tab Broken!");
			}
			try{
			Thread.sleep(2000);
			d1.findElement(By.xpath("//td[@id='EXPERIENCE_lbl']/table/tbody/tr/td[2]")).click();						
			WebElement f1 = d1.findElement(By.id("candidateExperienceFrame"));
			d1.switchTo().frame(f1);		
			Reporter.log("Experience Tab Successfully Loaded");
			}catch(Exception e){
			Reporter.log("Experience Tab Fail to Load");
			throw new NoSuchElementException("Experience Tab Broken!");
			}	
	}
	
	@Test(priority = 12)
	public static void Job_Notes()
					throws InterruptedException, NoSuchElementException {
		//try{
			//Job Tab		
			cr.clickSidebarMenuItem(d1,"Jobs");
			//d1.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
			WebElement f1 = d1.findElement(By.id("raspframe"));
			d1.switchTo().frame(f1);
			d1.findElement(By.name("in_contact_filter")).sendKeys("Anna Mendham");
			Thread.sleep(1000);
			d1.findElement(By.xpath("//input[@name='in_search']")).click();
			Thread.sleep(2000);
			d1.findElement(By.xpath("//a[contains(text(),'QLD/350540')]")).click();
			Thread.sleep(3000);
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
			Reporter.log("Job Tab Faile to Load");
			throw new NoSuchElementException("Job Tab Broken!");
			}			
	d1.switchTo().defaultContent();
	}
	
	@Test(priority = 13)
	public static void AdminConsole() throws InterruptedException, NoSuchElementException {
		// RTF Template Admin
		try{
			cr.clickSidebarMenuItem(d1, "Administration");
			Thread.sleep(2000);
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
		//Download RTF Template Builder
		try{
			d1.findElement(By.xpath("//td[contains(text(),'Download RTF Template Builder')]")).click();
			Thread.sleep(1000);
			d1.findElement(By.xpath("//p[contains(text(),'Download the RTF Template Builder to help you build marked up RTF templates for your offer letters, contracts, reference checks and other MSWord based documents.')]"));
			Reporter.log("Download RTF Template Builder Tab Loaded Successfully");
			}catch(Exception e){
				Reporter.log("Download RTF Template Builder Tab Load Fail");
				throw new NoSuchElementException("Download RTF Template Builder Tab Load Fail");
			}
		//Talent Folder Processes
		try{
			d1.findElement(By.xpath("//td[contains(text(),'Talent Folder Processes')]")).click();
			Thread.sleep(1000);
			d1.findElement(By.xpath("//div[contains(text(),'Talent Folder Processes')]"));
			Reporter.log("Talent Folder Processes Tab Loaded Successfully");
			}catch(Exception e){
				Reporter.log("Talent Folder Processes Tab Load Fail");
				throw new NoSuchElementException("Talent Folder Processes Tab Load Fail");
			}
		//Candidate Accounts
		try{
			d1.findElement(By.xpath("//td[contains(text(),'Candidate Accounts')]")).click();
			Thread.sleep(1000);
			d1.findElement(By.xpath("//div[contains(text(),'Candidate Account Administration')]"));
			Reporter.log("Candidate Accounts Tab Loaded Successfully");
			}catch(Exception e){
				Reporter.log("Candidate Accounts Tab Load Fail");
				throw new NoSuchElementException("Candidate Accounts Tab Load Fail");
			}
		
		/*//Broadcasts
		try{
			d1.findElement(By.xpath("//td[contains(text(),'Broadcasts')]")).click();
			Thread.sleep(1000);
			d1.findElement(By.xpath("//div[contains(text(),'Generate a notification to Springboard users')]"));
			Reporter.log("Broadcasts Tab Loaded Successfully");
			}catch(Exception e){
				Reporter.log("Broadcasts Tab Load Fail");
				throw new NoSuchElementException("Broadcasts Tab Load Fail");
			}
		
		//User Groups
		try{
			d1.findElement(By.xpath("//td[contains(text(),'User Groups')]")).click();
			Thread.sleep(1000);
			d1.findElement(By.xpath("//div[contains(text(),'User Groups Admin')]"));
			Reporter.log("User Groups Tab Loaded Successfully");
			}catch(Exception e){
				Reporter.log("User Groups Tab Load Fail");
				throw new NoSuchElementException("User Groups Tab Load Fail");
			}*/
		//Locations
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
		//Interview Auto Communication
		try{
			d1.switchTo().defaultContent();
			d1.findElement(By.xpath("//td[contains(text(),'Interview Auto Communication')]")).click();
			Thread.sleep(1000);
			WebElement f1 = d1.findElement(By.xpath("//iframe[starts-with(@src,'../scheduling-manager/auto-communication-setup/auto-comm-config-setup.jsf;')]"));
			d1.switchTo().frame(f1);
			d1.findElement(By.xpath("//span[contains(text(),'Disabled')]"));
			Reporter.log("Interview Auto Communication Tab Loaded Successfully");
			}catch(Exception e){
				Reporter.log("Interview Auto Communication Tab Load Fail");
				throw new NoSuchElementException("Interview Auto Communication Tab Load Fail");
			}
		
	
		//Grid Columns
		try{
			d1.switchTo().defaultContent();
			d1.findElement(By.xpath("//td[contains(text(),'Grid Columns')]")).click();
			Thread.sleep(1000);
			d1.findElement(By.xpath("//div[contains(text(),'Grid Columns')]"));
			Reporter.log("Grid Columns Tab Loaded Successfully");
			}catch(Exception e){
				Reporter.log("Grid Columns Tab Load Fail");
				throw new NoSuchElementException("Grid Columns Tab Load Fail");
			}
		
		// Hiring Manager Feedback
		try{
			d1.findElement(By.xpath("//td[contains(text(),'Hiring Manager Feedback')]")).click();
			Thread.sleep(1000);
			WebElement f1 = d1.findElement(By.id("hmFeedbackAdmin"));
			d1.switchTo().frame(f1);
			d1.findElement(By.xpath("//h3[contains(text(),'Hiring Manager Feedback Administration')]"));
			Reporter.log("Hiring Manager Feedback Tab Loaded Successfully");
			}catch(Exception e){
				Reporter.log("Hiring Manager Feedback Tab Load Fail");
				throw new NoSuchElementException("Hiring Manager Feedback Tab Load Fail");
			}
		d1.switchTo().defaultContent();
		//Application Admin
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
		//Holiday
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
	@Test(priority = 14)
	public static void emailQueueAdmin()
					throws InterruptedException, NoSuchElementException {
		try{
		cr.clickSidebarMenuItem(d1, "Administration");
		Thread.sleep(2000);
		d1.findElement(By.xpath("//a[contains(text(),'Email Queue Admin')]")).click();
		Thread.sleep(1000);
		WebElement f1 = d1.findElement(By.id("EmailQueueAdminIFrame"));
		d1.switchTo().frame(f1);
		d1.findElement(By.xpath("//span[contains(text(),'This page auto refreshes every 60 seconds.')]"));
		Reporter.log("Email Queue Management Page loaded Successfully");
		}catch(Exception e){
			Reporter.log("Email Queue Management Page Is Broken");
			throw new NoSuchElementException("Email Queue Management Page Is Broken");
		}
	d1.switchTo().defaultContent();
	}
	
	@Test(priority = 15)
	public static void orbeon()
					throws InterruptedException, NoSuchElementException {
		try{
		// Orbeon
		cr.clickSidebarMenuItem(d1, "Administration");
		Thread.sleep(2000);		
		d1.findElement(By.xpath("//a[contains(text(),'Forms Library')]")).click();
		d1.findElement(By.xpath("//img[@src='/rasp6/themes/spring/includes/modules/images/modify_form.gif']/ancestor::a[1]")).click();
		Thread.sleep(2000);
		WebElement f1 = d1.findElement(By.id("formBuilderFrame"));
		d1.switchTo().frame(f1);
		d1.findElement(By.xpath("//span[contains(text(),'Basic Application Test Form')]"));		
	    Reporter.log("Orbeon Form loaded Successfully");		
		}catch(Exception e){
			Reporter.log("The Orbeon Form Is Broken");
			throw new NoSuchElementException("Orbeon Form Fails to Load");
	    }			
	}
	
	@Test(priority = 16)
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
		
	}	
	
	@Test(priority = 18)
	public static void externalSearch()
					throws InterruptedException {
		try{
		// Search String Builder
		cr.clickSidebarMenuItem(d1, "Candidates");	
		Thread.sleep(2000);
		d1.findElement(By.xpath("//a[contains(text(),'External Search')]")).click();
		Thread.sleep(2000);
		WebElement f1 = d1.findElement(By.id("externalSearchFrame"));
		d1.switchTo().frame(f1);
		d1.findElement(By.xpath("//h3[contains(text(),'Use Google or Bing to search for profiles on LinkedIn')]"));
		Reporter.log("Search String Builder Page loaded Successfully");
		}catch(Exception e){
			Reporter.log("The External Search Link Is Broken");
		}
	}
	
	@Test(priority = 17)
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
	@Test(priority = 25)
	public static void delete_candidate(
	)
					throws InterruptedException, FailedLoginException {
		try{
			
			   WebElement searchButton = d1.findElement(By.xpath("//div[4]/div/div[1]/div/nav/span/div/div/span/div/i"));
			   
			  Thread.sleep(2000);
			  searchButton.click();
			  WebElement searchInputBox  = d1.findElement(By.name("mainQuickSearch:quickSearchForm:quickSearchAutoComplete_input"));
			  searchInputBox.sendKeys("Swati Sharma");
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
		finally{
			d1.close();
	}
	
	
	}
	
	@DataProvider(name="prodv6-data-provider")
	public String[][] usernameAndPasswordDataProvider() {
		return ExcelReadUtil.readExcelInto2DArray("./qldProdv6.xlsx","PROD_V6",5);
	}
}
