package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import utils.EmailUtils;
import utils.ExtentReportManager;
import utils.Log;

import java.lang.reflect.Method;

public class BaseTest {

    public static WebDriver driver;
    protected static ExtentReports extent;
    protected static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    protected static boolean isDriverInitialized = false;

    @BeforeSuite
    public void setupReport() {
        extent = ExtentReportManager.getReportInstance();
    }

    @AfterSuite
    public void teardownReport() {
        if (extent != null) {
            extent.flush();
        }
        String reportPath = ExtentReportManager.reportPath;
        EmailUtils.sendTestReport(reportPath);
    }

    @BeforeMethod
    public void setUp(Method method) {
    	
        try {
            if (driver == null) {
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                // Initialize ExtentTest for this test method
                ExtentTest extentTest = extent.createTest(method.getName());
                test.set(extentTest);                Log.info("Starting WebDriver...");
                test.get().info("Starting WebDriver...");
                isDriverInitialized = true;
                Log.info("Navigating to URL...");
                driver.get("https://orgfarm-94b116bdb8-dev-ed.develop.my.salesforce.com");
                test.get().pass("Navigated to the URL https://orgfarm-94b116bdb8-dev-ed.develop.my.salesforce.com");
            }

           

        } catch (Exception e) {
            isDriverInitialized = false;
            Log.error("Driver initialization failed: " + e.getMessage());
        }
     // Initialize ExtentTest here
        String testName = method.getName();
        test.set(ExtentReportManager.createTest(testName));
    }

//    @AfterMethod
//    public void tearDown(ITestResult result) {
//        ExtentTest currentTest = test.get();  // Get the test object for this thread
//        String testName = result.getClass().getName();
//       
//        if (currentTest != null) {
//        	 String status = (result.getStatus() == ITestResult.SUCCESS) ? "PASS" : "FAIL";
//            if (result.getStatus() == ITestResult.FAILURE) {
//                String screenshotFailurePath = ExtentReportManager.captureScreenshot(driver, "LoginFailure :- " + testName + " _ " +status);
//                if (screenshotFailurePath != null && !screenshotFailurePath.isEmpty()) {
//                    Reporter.log("<br><img src='" + screenshotFailurePath + "' height='400' width='400'/><br>");
//                } else {
//                    Reporter.log("Screenshot not captured due to error.");
//                }
//                currentTest.fail("Test Failed.. Check Screenshot",
//                        MediaEntityBuilder.createScreenCaptureFromPath(screenshotFailurePath).build());
//            } else if (result.getStatus() == ITestResult.SUCCESS) {
//                String screenshotSuccessPath = ExtentReportManager.captureScreenshot(driver, "Success_Screen :- " + testName + " _ " + status);
//                if (screenshotSuccessPath != null && !screenshotSuccessPath.isEmpty()) {
//                    Reporter.log("<br><img src='" + screenshotSuccessPath + "' height='400' width='400'/><br>");
//                } else {
//                    Reporter.log("Screenshot not captured due to error.");
//                }
//
//                currentTest.pass("Test Passed.. Check Screenshot",
//                        MediaEntityBuilder.createScreenCaptureFromPath(screenshotSuccessPath).build());
//            } else if (result.getStatus() == ITestResult.SKIP) {
//                currentTest.skip("Test Skipped......");
//            }
//        } else {
//            Log.warn("ExtentTest was not initialized. Skipping report logging.");
//        }
//
//        if (driver != null) {
//            Log.info("Closing Browser...");
//             driver.quit(); // Uncomment if you want to close browser after each test
//        }
//    }
//
//    @AfterGroups("login")
//    public void tearDownDriver() {
//        if (isDriverInitialized && driver != null) {
//            Log.info("Final Driver Cleanup: Closing Browser...");
//            driver.quit();
//        } else {
//            Log.warn("Driver was not initialized or already closed.");
//        }
//    }
}
