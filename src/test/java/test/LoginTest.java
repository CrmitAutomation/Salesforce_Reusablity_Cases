package test;

import java.io.IOException;
import java.time.Duration;

//import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import utils.ExcelUtils;
import utils.ExtentReportManager;
import utils.Log;
import org.testng.Assert;

import java.lang.reflect.Method;

//import org.openqa.selenium.support.FindBy;

public class LoginTest extends BaseTest {
	
	@DataProvider(name="LoginData")
	public Object[][] getLoginData() throws IOException{
		
		String filePath = System.getProperty("user.dir")+"/testdata/Test Data.xlsx";
		ExcelUtils.loadExcel(filePath, "Sheet1");
		int rowCount = ExcelUtils.getRowCount();
		Object[][] data = new Object[rowCount-1][2];
		
		for(int i=1; i<rowCount; i++) {
			
			data[i-1][0] = ExcelUtils.getCellData(i, 0);	// Username
			data[i-1][1] = ExcelUtils.getCellData(i, 1);	// Password
		}
		ExcelUtils.closeExcel();
		return data;
	}
	
	
	@DataProvider(name="LoginData2")
	public Object[][] getData(){
		
		return new Object[][] {
			{"admin@yourstore.com","admin"}
//			{"user2","pass2"},
//			{"user3","pass3"}
		};
	}
	
	

	@Test(dataProvider = "LoginData", groups = "login")
//	@Test
	@Parameters({"username","password"})
//	@Test
	public void testValidLogin(String username, String password, Method method) throws InterruptedException {
		if (!isDriverInitialized || driver == null) {
			Assert.fail("Driver was not initialized properly.");
		}

		Log.info("Starting login test...");
//		test = ExtentReportManager.createTest("Login Test - "+ username);
		 String testName = method.getName();
	        test.set(ExtentReportManager.createTest("Login Test - "+ testName));
	        
		test.get().info("Navigating to URL");
		LoginPage loginPage = new LoginPage(driver);

		Log.info("Adding credentials");
		test.get().info("Adding Credentails");
//		loginPage.enterUsername("admin@yourstore.com");
//		loginPage.enterPassword("admin");
		loginPage.enterUsername(username);
		loginPage.enterPassword(password);
		test.get().info("Clicking on Login button");
		loginPage.clickLogin();
		ExtentReportManager.captureScreenshot(driver, "Logged_In Successfully"); 
		Log.info("Verifying page title");
		test.get().info("Verifying page title");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		System.out.println(driver.getTitle());
		if(driver.getTitle().equals("Salesforce - Developer Edition"))  {
			loginPage.clickOnSwitchToLightning();
			Log.info("Switched to Lightning Page");
			test.get().info("Switched to Lightning Page");
		}
		else if(driver.getTitle().equals("Register Your Mobile Phone | Salesforce")) {
			loginPage.clickOnRemindMe();
			System.out.println("clicked on remaindLater");
			test.get().info("clicked on remaindLater");
		}
		
	}

//	@Test
//	public void testLoginWithInvalidCredentials() {
//
//		Log.info("Starting login test...");
//		test = ExtentReportManager.createTest("Login Test with Invalid Credentials");
//
//		test.info("Navigating to URL");
//		LoginPage loginPage = new LoginPage(driver);
//
//		Log.info("Adding credentials");
//		test.info("Adding Credentails");
//		loginPage.enterUsername("admin1234@yourstore.com");
//		loginPage.enterPassword("admin123");
//		test.info("Clicking on Login button");
//		loginPage.clickLogin();
//
//		System.out.println("Title of the page is : " + driver.getTitle());
//		Log.info("Verifying page title");
//		test.info("Verifying page title");
//		Assert.assertEquals(driver.getTitle(), "Just a moment...123");
//
//		test.pass("Login Successful");
//
//	}

}
