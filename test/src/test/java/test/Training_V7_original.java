package test;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import javax.security.auth.login.FailedLoginException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Training_V7_original {

	static WebDriver d1;
	 public static Assertion hardAssert = new Assertion();
	 public static SoftAssert softAssert = new SoftAssert();
	 public static String javaScript = "var evObj = document.createEvent('MouseEvents');" +
             "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);" +
             "arguments[0].dispatchEvent(evObj);";

		@Test(dataProvider = "trnv7-data-provider", priority =1)
		public static void login_backend(
				String UserName_BackEnd,
				String Password_BackEnd,
				String profileName,
				String maximize,
				String Environment)
						throws InterruptedException, MalformedURLException, FailedLoginException {
			WebDriverManager.chromedriver().setup();
			d1 = new ChromeDriver();
			DesiredCapabilities caps = DesiredCapabilities.chrome();
			caps.setBrowserName("chrome");
			caps.setPlatform(Platform.WINDOWS);
			//d1 = new RemoteWebDriver(new URL("http://localhost:5566/wd/hub"), caps);
		Reporter.log(
					"===============================================================");
			
			Reporter.log(Training_V7_original.class.getSimpleName());
			Reporter.log("Browser Name:" + caps.getBrowserName().toUpperCase());
			Reporter.log(Environment);
			d1.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
			d1.get("https://training.smartjobs.govnet.qld.gov.au/jobtools/jncustomorglogin.logout?in_organid=15306");
	    d1.manage().window().maximize();
	
		// throw new SkipException("Skipping Test");
		d1.findElement(By.xpath("//input[@name='in_username']")).sendKeys(UserName_BackEnd);
		d1.findElement(By.xpath("//input[@type='PASSWORD']")).sendKeys(Password_BackEnd);
		d1.findElement(By.xpath("//input[@value='Login']")).click();
		Thread.sleep(1000);
	// try{
			//Thread.sleep(2000);			
			// Validation for Login Check
		//	Assert.assertEquals("Springboard", d1.getTitle());
		//	d1.findElement(By.xpath("//h2[contains(text(),'Dashboard')]"));
		//	Reporter.log(Training_V7.class.getSimpleName()+"-"+UserName_BackEnd+"- Springboard Login Passed");
		//	}catch(Exception e){
		//		Reporter.log("Dashboard Load Failed");
				/*String URL = "https://vicboards-qa.hrxtech.com.au/rasp6/home.seam;jsessionid=A248A6C25F88E379463A978269FFE99B.node2?userId=1086203432&userName=vpsc_dedjtr&userOrgId=18545&appOrgId=18452&sessionId=1364422938950782470&login=true&in_redirect_url=&cid=200";
				Reporter.log("<a href=" + URL+ ">click to open screenshot</a>");*/				
			//	throw new FailedLoginException("Dashboard load failed");
			
			}
		
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
		         
		/*     try {
		            Thread.sleep(500);
		            d1.findElement(By.xpath("//*[@id='headerMenuForm:menuAddCandidateLinkWithoutMenu']")).click();
		            Thread.sleep(1000);
		            d1.findElement(By.xpath("//h2[contains(text(),'Create a new candidate')]"));
		            d1.findElement(By.xpath("//a[@title='Close']")).click();
		        //    d1.findElement(By.xpath("//a[@title="Close"]"))
		            #j_idt307
		     
		          
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
		  */
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
		public static void JobTab()
						throws Exception {
			
				/*Thread.sleep(2000);
				((JavascriptExecutor)d1).executeScript(javaScript, d1.findElement(By.xpath("//span[contains(text(),'Dashboard')]")));
				
				Thread.sleep(2000);
				d1.findElement(By.xpath("//span[contains(text(),'Dashboard')]")).click();*/
				Thread.sleep(2000);
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
		public static void AllTabs() throws Exception {
			
				Thread.sleep(2000);
				try{
				WebElement f1 = d1.findElement(By.id("raspframe"));
				d1.switchTo().frame(f1);
				d1.findElement(By.xpath("//span[contains(text(),'Applications')]")).click();
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
				Thread.sleep(1000);		
				//d1.findElement(By.xpath("//a[@id='candForm:candidateGrid:0:j_idt710']")).click();
				Thread.sleep(1000);			
				WebElement resumeelement = d1.findElement(By.xpath("//div[starts-with(@id,'reviewTabPanel:resumeTabForm:j_idt')]"));
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
				/*try{
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
				}*/
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
				/*
				try{
				Thread.sleep(1000);
				d1.findElement(By.xpath("//td[contains(text(),'Experience')]")).click();
				WebElement f3 = d1.findElement(By.id("candidateExperienceFrame"));
				d1.switchTo().frame(f3);
				WebElement Experienceelement = d1.findElement(By.xpath("//span[contains(text(),'Company')]"));
				String Experience = Experienceelement.getText();
				softAssert.assertEquals(Experience, "Company","Experience tab not loaded properly");
				softAssert.assertAll();
				Reporter.log("EXPERIENCE tab working");
				}catch(Exception e){
				Reporter.log("EXPERIENCE tab not working");
				throw new Exception("EXPERIENCE tab not working");
				}*/
			
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

		/*@Test(priority = 6)
		public static void EmailInbox() throws Exception{
			try{		
			
			((JavascriptExecutor)d1).executeScript(javaScript, d1.findElement(By.xpath("//span[contains(text(),'Candidates')]")));
			Thread.sleep(1000);
			d1.findElement(By.xpath("//a[contains(text(),'Email Inbox')]")).click();
			WebElement inboxframe = d1.findElement(By.id("MailServiceInboxIFrame"));
			d1.switchTo().frame(inboxframe);
			d1.findElement(By.xpath("//input[@id='inboundMessagesSearchForm:inboundMessagesSearchButton']")).click();
			WebElement inboxelmnt = d1.findElement(By.xpath("//span[contains(text(),'Subject:')]"));
			String inbox = inboxelmnt.getText();
			softAssert.assertEquals(inbox, "Subject:","Email Inbox page not loading properly");
			softAssert.assertAll();
			Reporter.log("Email Inbox page loads");
			}catch(Exception e){
			Reporter.log("Email Inbox page not loading properly");
			throw new Exception("Email Inbox page not loading properly");

			}
			finally{
			
			d1.switchTo().defaultContent();	
			}
		}*/

		
@Test(priority = 6)
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

@Test(priority = 7)
public static void FormsSearch() throws Exception {
	try{
	// Forms Search
	Thread.sleep(1000);
	((JavascriptExecutor)d1).executeScript(javaScript, d1.findElement(By.xpath("//span[contains(text(),'Administration')]")));
	Thread.sleep(1000);	
	((JavascriptExecutor)d1).executeScript(javaScript, d1.findElement(By.xpath("//a[contains(text(),'Forms Management')]")));
	Thread.sleep(1000);
	d1.findElement(By.xpath("//a[contains(text(),'Forms Management')]")).click();
	Thread.sleep(2000);	
	WebElement formssearchframe = d1.findElement(By.id("formsManagementFrame"));
	d1.switchTo().frame(formssearchframe);
	WebElement formssearcheelmnt = d1.findElement(By.xpath("//h3[contains(text(),'Search Forms')]"));	
	String FormsSearch = formssearcheelmnt.getText();
	softAssert.assertEquals(FormsSearch,"Search Forms", "Forms Management page page not loading properly");
	softAssert.assertAll();					
	Reporter.log("Forms Management page loaded successfully");		
	}catch(Exception e){
    Reporter.log("Forms Management page not loading properly");
    throw new Exception("Forms Management page not loading properly");
	}					
	finally{	
		d1.switchTo().defaultContent();
		
	}	
}	

		/*@Test(priority = 8)
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

		/*@Test(priority = 9)
		public static void Companies() throws Exception{
			try{
			// Companies
				Thread.sleep(1000);
				((JavascriptExecutor)d1).executeScript(javaScript, d1.findElement(By.xpath("//span[contains(text(),'Companies')]")));
				Thread.sleep(1000);					
				d1.findElement(By.xpath("//a[contains(text(),'Company Search')]")).click();					
				Thread.sleep(1000);	
				//WebElement jobrequestframe = d1.findElement(By.id("jobRequestsFrame"));
				//d1.switchTo().frame(jobrequestframe);
				WebElement companieselmnt = d1.findElement(By.xpath("//label[contains(text(),'Company Name :')]"));
				String Companies = companieselmnt.getTagName();
				softAssert.assertEquals(Companies, "label", "Companies page not loading properly");
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


		@Test(priority = 9)
		public static void DownloadRTFTemplateBuilder() throws Exception{
			try{			
				Thread.sleep(1000);
				((JavascriptExecutor)d1).executeScript(javaScript, d1.findElement(By.xpath("//span[contains(text(),'Administration')]")));
				Thread.sleep(1000);
				d1.findElement(By.xpath("//a[contains(text(),'Admin Console')]")).click();
				Thread.sleep(1000);
				d1.findElement(By.xpath("//a[contains(text(),'Download RTF Template Builder')]")).click();
				Thread.sleep(1000);
				WebElement RTFbuildereselmnt = d1.findElement(By.xpath("//td[contains(text(),'Full')]"));
				String RTFTemplateBuilder = RTFbuildereselmnt.getTagName();
				softAssert.assertEquals(RTFTemplateBuilder,"td", "Download RTF Template Builder tab not loading properly");
				softAssert.assertAll();
				Reporter.log("Download RTF Template Builder tab loaded successfully");
				}catch(Exception e){
					Reporter.log("Download RTF Template Builder tab not loading properly");
					throw new Exception("Download RTF Template Builder tab not loading properly");
				}
		}*/
		
		/*@Test(priority = 10)
		public static void CandidateAccounts() throws Exception{
			try{			
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
		}*/
		/*@Test(priority = 11)
		public static void CandidatePortalConfig() throws Exception{
			try{			
				Thread.sleep(1000);
				d1.findElement(By.xpath("//a[contains(text(),'Candidate Portal Config')]")).click();
				Thread.sleep(1000);
				WebElement CandidatePortalConfigelmnt = d1.findElement(By.xpath("//span[contains(text(),'Candidate Portal Config Details')]"));
				String CandidatePortalConfig = CandidatePortalConfigelmnt.getTagName();
				softAssert.assertEquals(CandidatePortalConfig,"span", "Candidate Portal Config tab not loading properly");
				softAssert.assertAll();
				Reporter.log("Candidate Portal Config tab loaded successfully");
				}catch(Exception e){
					Reporter.log("Candidate Portal Config tab not loading properly");	
					throw new Exception("Candidate Portal Config tab not loading properly");
				}
		}*/
		
		/*@Test(priority = 12)
		public static void EmailAddressOverride() throws Exception{
			try{			
				Thread.sleep(1000);
				d1.findElement(By.xpath("//a[contains(text(),'Email Address Override')]")).click();
				Thread.sleep(1000);
				WebElement EmailAddressOverrideelmnt = d1.findElement(By.xpath("//span[contains(text(),'Sending Emails')]"));
				String EmailAddressOverride = EmailAddressOverrideelmnt.getTagName();
				softAssert.assertEquals(EmailAddressOverride,"span", "Email Address Override tab not loading properly");
				softAssert.assertAll();
				Reporter.log("Email Address Override tab loaded successfully");
				}catch(Exception e){
					Reporter.log("Email Address Override tab not loading properly");	
					throw new Exception("Email Address Override tab not loading properly");
				}
		}*/
		
		/*@Test(priority = 13)
		public static void Broadcasts() throws Exception{
			try{			
				Thread.sleep(1000);
				d1.findElement(By.xpath("//a[contains(text(),'Broadcasts')]")).click();
				Thread.sleep(1000);
				WebElement Broadcastselmnt = d1.findElement(By.xpath("//span[contains(text(),'Generate a notification to Springboard users')]"));
				String Broadcasts = Broadcastselmnt.getTagName();
				softAssert.assertEquals(Broadcasts,"span", "Broadcasts tab not loading properly");
				softAssert.assertAll();
				Reporter.log("Broadcasts tab loaded successfully");
				}catch(Exception e){
					Reporter.log("Broadcasts tab not loading properly");	
					throw new Exception("Broadcasts tab not loading properly");
				}
		}
		
		@Test(priority = 14)
		public static void UserGroups() throws Exception{
			try{			
				Thread.sleep(1000);
				d1.findElement(By.xpath("//a[contains(text(),'User Groups')]")).click();
				Thread.sleep(1000);
				WebElement UserGroupselmnt = d1.findElement(By.xpath("//span[contains(text(),'User Groups Admin')]"));
				String UserGroups = UserGroupselmnt.getTagName();
				softAssert.assertEquals(UserGroups,"span", "User Groups tab not loading properly");
				softAssert.assertAll();
				Reporter.log("User Groups tab loaded successfully");
				}catch(Exception e){
					Reporter.log("User Groups tab not loading properly");	
					throw new Exception("User Groups tab not loading properly");
				}
		}*/

		@Test(priority = 15)
		public static void Locations() throws Exception{
			try{			
				Thread.sleep(1000);
				((JavascriptExecutor)d1).executeScript(javaScript, d1.findElement(By.xpath("//span[contains(text(),'Administration')]")));
				Thread.sleep(1000);
				d1.findElement(By.xpath("//a[contains(text(),'Admin Console')]")).click();
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
		
		/*@Test(priority = 16)
		public static void InterviewAutoCommunication() throws Exception{
			try{			
				Thread.sleep(1000);
				d1.findElement(By.xpath("//a[contains(text(),'Interview Auto Communication')]")).click();
				Thread.sleep(1000);
				WebElement InterviewAutoCommunicationelmnt = d1.findElement(By.xpath("//span[contains(text(),'Automatic Notification :')]"));
				String InterviewAutoCommunication = InterviewAutoCommunicationelmnt.getTagName();
				softAssert.assertEquals(InterviewAutoCommunication,"span", "Interview Auto Communication tab not loading properly");
				softAssert.assertAll();
				Reporter.log("Interview Auto Communication tab loaded successfully");
				}catch(Exception e){
					Reporter.log("Interview Auto Communication tab not loading properly");	
					throw new Exception("Interview Auto Communication tab not loading properly");
				}
			finally{
				d1.switchTo().defaultContent();
			}
		}*/
		
		@Test(priority = 17)
		public static void HiringManagerFeedback() throws Exception{
			try{			
				Thread.sleep(1000);
				d1.findElement(By.xpath("//a[contains(text(),'Hiring Manager Feedback')]")).click();
				Thread.sleep(500);
				WebElement hmFeedbackAdmin = d1.findElement(By.id("hmFeedbackAdmin"));
				d1.switchTo().frame(hmFeedbackAdmin);
				Thread.sleep(1000);
				//WebElement HiringManagerFeedbackelmnt = d1.findElement(By.xpath("//span[contains(text(),'Hiring Manager Feedback Administration')]"));
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
		
		
		/*@Test(priority = 18)
		public static void ApplicationAdmin() throws Exception{
			try{			
				Thread.sleep(1000);
				d1.findElement(By.xpath("//a[contains(text(),'Application Admin')]")).click();
				Thread.sleep(1000);
				WebElement applicationAdmin = d1.findElement(By.id("applicationAdmin"));
				d1.switchTo().frame(applicationAdmin);
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
		/*@Test(priority = 17)
		public static void EmailQueueAdmin() throws Exception{
			try{
				Thread.sleep(1000);
				((JavascriptExecutor)d1).executeScript(javaScript, d1.findElement(By.xpath("//span[contains(text(),'Administration')]")));
				Thread.sleep(1000);
				d1.findElement(By.xpath("//a[contains(text(),'Email Queue Admin')]")).click();
				Thread.sleep(1000);
				WebElement EmailQueueAdminIFrame = d1.findElement(By.id("EmailQueueAdminIFrame"));
				d1.switchTo().frame(EmailQueueAdminIFrame);
				WebElement emailqueueadminelmnt = d1.findElement(By.xpath("//span[contains(text(),'This page auto refreshes every 60 seconds.')]"));
				String EmailQueueAdmin = emailqueueadminelmnt.getTagName();
				softAssert.assertEquals(EmailQueueAdmin,"span", "Email Queue Admin page not loading properly");
				softAssert.assertAll();			
				Reporter.log("Email Queue Admin page loaded successfully");
				}catch(Exception e){
					Reporter.log("Email Queue Admin page not loading properly");
					throw new Exception("Email Queue Admin page not loading properly");
				}
			finally{
				d1.switchTo().defaultContent();
				}
		
		}			*/
		
		@Test(priority = 20)
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
				softAssert.assertEquals(OrbeonForm,"span", "Orbeon form not loading properly");
				softAssert.assertAll();					
				Reporter.log("Orbeon form loaded successfully");		
				}catch(Exception e){
		        Reporter.log("Orbeon form not loading properly");
		        throw new Exception("Orbeon form not loading properly");
		        
				}								
			finally{				
				d1.switchTo().defaultContent();	
			}
		}
		@Test(priority = 21)
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
				d1.close();	
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
	@DataProvider(name="trnv7-data-provider")
	public String[][] usernameAndPasswordDataProvider() {
		return ExcelReadUtil.readExcelInto2DArray("./trainingV7.xlsx","TRN_v7",5);
	}
}

