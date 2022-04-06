package test;

import java.util.Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class cr {
	
	//static Properties prop = null;
	public cr(){
		/*prop = new Properties();
		
		//Reporter.log(System.getProperty("user.dir"));
		
		try {
			FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"\\src\\xls\\object.properties");
			prop.load(fs);
		} catch (IOException e) {
			
			e.printStackTrace();
		} */
	}
	
	/* public static boolean baseURL(String url){		
		Reporter.log(String.format("Running test [dashboard_smoke_test] with: %s", url));
		try {
			
            int resp_code= Request.Get(url).execute().returnResponse().getStatusLine().getStatusCode();
        Reporter.log("Respose code for Base URL  is -> "+ resp_code);
           if(resp_code == 200)
        	   return true;
           else
        	   return false;
        } catch (Exception e) {
     	   return false;
        }}
	
   public static boolean linkType(String Typeurl){
	   Reporter.log(String.format("Running test [dashboard_smoke_test] with: %s", Typeurl));
	   try {
        int resp_code= Request.Get(Typeurl).execute().returnResponse().getStatusLine()
                .getStatusCode();
        Reporter.log("Respose code for link Type  is -> "+ resp_code);
       if(resp_code == 200)
    	   return true;
       else
    	   return false;
    } catch (Exception e) {
 	   return false;
    }}
	
	 public static boolean linkJob(String JobURL){
		 Reporter.log(String.format("Running test [dashboard_smoke_test] with: %s", JobURL));
		 try {
	            int resp_code= Request.Get(JobURL).execute().returnResponse().getStatusLine()
	                    .getStatusCode();
	            Reporter.log("Respose code for link Job is -> "+ resp_code);
	           if(resp_code == 200)
	        	   return true;
	           else
	        	   return false;
	        } catch (Exception e) {
	     	   return false;
	        }
   }
	public static boolean linkDueDate(String dueDateUrl) {
		Reporter.log(String.format("Running test [dashboard_smoke_test] with: %s", dueDateUrl));
		try {
            int resp_code= Request.Get(dueDateUrl).execute().returnResponse().getStatusLine()
                    .getStatusCode();
            Reporter.log("Respose code for link Due Date is -> "+ resp_code);
           if(resp_code == 200)
        	   return true;
           else
        	   return false;
        } catch (Exception e) {
     	   return false;
        }
	}

	public static boolean linkStatus(String StatusUrl) {
		Reporter.log(String.format("Running test [dashboard_smoke_test] with: %s", StatusUrl));
		try {
            int resp_code= Request.Get(StatusUrl).execute().returnResponse().getStatusLine()
                    .getStatusCode();
            Reporter.log("Respose code for link Status is -> "+ resp_code);
           if(resp_code == 200)
        	   return true;
           else
        	   return false;
        } catch (Exception e) {
     	   return false;
        }
	}

	public static boolean linkMoreTask(String moreTaskUrl) {
		Reporter.log(String.format("Running test [dashboard_smoke_test] with: %s", moreTaskUrl));
		try {
            int resp_code= Request.Get(moreTaskUrl).execute().returnResponse().getStatusLine()
                    .getStatusCode();
            Reporter.log("Respose code for link More (Task) is -> "+ resp_code);
           if(resp_code == 200)
        	   return true;
           else
        	   return false;
        } catch (Exception e) {
     	   return false;
        }
	}

	public static boolean candidateProfileURL(String myProfile) {
		//Reporter.log(String.format("Running test [application_test] with: %s", myProfile));
		try {
			
            int resp_code= Request.Get(myProfile).execute().returnResponse().getStatusLine()
                    .getStatusCode();
        Reporter.log("Link Name: My Profile (Candidate: Front End) is OK - Response Code : "+ resp_code);
           if(resp_code == 200)
        	   return true;
           else
        	   return false;
        } catch (Exception e) {
     	   return false;
        	}
	}

	

	public static boolean checkWebServiceURL(String wsUrl) {
		Reporter.log(String.format("Running test [application_test] with: %s", wsUrl));
		try {
            int resp_code= Request.Get(wsUrl).execute().returnResponse().getStatusLine()
                    .getStatusCode();
            Reporter.log("Web Service (Prod) up and running. Response Code : "+ resp_code);
           if(resp_code == 200)
        	   return true;
           else
        	   return false;
        } catch (Exception e) {
     	   return false;
        }
	}
	
	public static boolean formsLibraryURL(String formsLibraryUrl) {
		//Reporter.log(String.format("Running test [application_test_backEnd] with: %s", formsLibraryUrl));
		try {
            int resp_code= Request.Get(formsLibraryUrl).execute().returnResponse().getStatusLine()
                    .getStatusCode();
            Reporter.log("Link Forms Library is OK. Response Code "+ resp_code);
           if(resp_code == 200)
        	   return true;
           else
        	   return false;
        } catch (Exception e) {
     	   return false;
        }
	}
	*/
	public static void clickSidebarMenuItem(WebDriver d1, String menuItemLabel) {
		d1.switchTo().defaultContent();
		d1.findElements(By.xpath("//div[starts-with(@id,'sideMenuForm:j_id')]/span")).stream()
		.filter(p -> Objects.equals(p.getText(), menuItemLabel))
		.findAny().ifPresent(WebElement::click);		
	}
}

	/* public static boolean editOrbeonURL(String editOrbeonURL) {
		//Reporter.log(String.format("Running test [application_test_backEnd] with: %s", editOrbeonURL));
		try {
            int resp_code= Request.Get(editOrbeonURL).execute().returnResponse().getStatusLine()
                    .getStatusCode();
            Reporter.log("Link Forms Library (Edit Orbeon) is OK. Response Code "+ resp_code);
           if(resp_code == 200)
        	   return true;
           else
        	   return false;
        } catch (Exception e) {
     	   return false;
        }
	}
	
	public static boolean emailInboxUrl(String emailInboxUrl) {
		//Reporter.log(String.format("Running test [application_test_backEnd] with: %s", emailInboxUrl));
		try {
            int resp_code= Request.Get(emailInboxUrl).execute().returnResponse().getStatusLine()
                    .getStatusCode();
            Reporter.log("Link Email Inbox is OK. Response Code "+ resp_code);
           if(resp_code == 200)
        	   return true;
           else
        	   return false;
        } catch (Exception e) {
     	   return false;
        }
	}

	public static boolean adminconsoleUrl(String adminconsoleUrl) {
		//Reporter.log(String.format("Running test [application_test_backEnd] with: %s", adminconsoleUrl));
				try {
		            int resp_code= Request.Get(adminconsoleUrl).execute().returnResponse().getStatusLine()
		                    .getStatusCode();
		            Reporter.log("Link Admin Console is OK. Response Code "+ resp_code);
		           if(resp_code == 200)
		        	   return true;
		           else
		        	   return false;
		        } catch (Exception e) {
		  } 	   return false;
	}
	
	

	public static boolean EmailQueueManagementUrl(String emailQueueManagementUrl) {
		try {
            int resp_code= Request.Get(emailQueueManagementUrl).execute().returnResponse().getStatusLine()
                    .getStatusCode();
            Reporter.log("Link Email Queue Management is OK. Response Code "+ resp_code);
           if(resp_code == 200)
        	   return true;
           else
        	   return false;
        } catch (Exception e) {
  } 	   return false;
}
	*/