package test;

import base.BaseTest;
import pages.Lead_Converstion;
import pages.Lead_Creation;
import utils.DataProviderUtils;
import utils.ExtentReportManager;

import org.testng.annotations.Test;



public class Lead_Creation_Test extends BaseTest {
	@Test(priority = 1, dependsOnGroups = "login", groups = "lead", dataProvider = "leadData", dataProviderClass = DataProviderUtils.class)
    public void createLeadTest(String fName, String lName, String company, String email, String phone) throws InterruptedException{
		Lead_Creation leadPage = new Lead_Creation(driver);
//		leadPage.openNewLeadForm();
		 leadPage.createLead(fName, lName, company, email, phone);
		 ExtentReportManager.captureScreenshot(driver, "Lead Created Successfully"); 
    }

    @Test(priority = 2, dependsOnGroups = "lead")
    public void convertLeadTest() throws InterruptedException {
    	Lead_Converstion lead_Converstion = new Lead_Converstion(driver);
    	lead_Converstion.convertLead();
		 ExtentReportManager.captureScreenshot(driver, "Lead Converted Successfully"); 
    }
}
