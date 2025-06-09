//package base;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.ITestResult;
//import org.testng.Reporter;
//import org.testng.annotations.*;
//
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.MediaEntityBuilder;
//
//import utils.EmailUtils;
//import utils.ExtentReportManager;
//import utils.Log;
//
//import java.lang.reflect.Method;
//
//public class BaseTest {
//	
//    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
//    public static WebDriver getDriver() {
//        return driver.get();
//    }
//
//    protected static ExtentReports extent;
//    protected static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
//    protected static boolean isDriverInitialized = false;
//
//    @BeforeSuite
//    public void setupReport() {
//        extent = ExtentReportManager.getReportInstance();
//    }
//
//    @AfterSuite
//    public void teardownReport() {
//        if (extent != null) {
//            extent.flush();
//        }
//        String reportPath = ExtentReportManager.reportPath;
//        EmailUtils.sendTestReport(reportPath);
//    }
//
//    @BeforeMethod
//    public void setUp(Method method) {
//        try {
//            WebDriver localDriver = new ChromeDriver();
//            localDriver.manage().window().maximize();
//            driver.set(localDriver);
//
//            ExtentTest extentTest = ExtentReportManager.createTest(method.getName());
//            test.set(extentTest);
//
//            Log.info("Starting WebDriver...");
//            test.get().info("Navigating to URL...");
//            localDriver.get("https://orgfarm-94b116bdb8-dev-ed.develop.my.salesforce.com");
//            test.get().pass("Navigated to URL");
//        } catch (Exception e) {
//            Log.error("Driver init failed: " + e.getMessage());
//        }
//    }
//
//    @AfterMethod
//    public void tearDown(ITestResult result) {
//        ExtentTest currentTest = test.get();
//        String testName = result.getMethod().getMethodName();
//
//        try {
//            if (result.getStatus() == ITestResult.FAILURE) {
//                String path = ExtentReportManager.captureScreenshot(getDriver(), "FAIL_" + testName);
//                currentTest.fail("Failure", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
//            } else if (result.getStatus() == ITestResult.SUCCESS) {
//                String path = ExtentReportManager.captureScreenshot(getDriver(), "PASS_" + testName);
//                currentTest.pass("Success", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
//            } else {
//                currentTest.skip("Skipped");
//            }
//        } catch (Exception e) {
//            Log.error("Screenshot capture failed: " + e.getMessage());
//        } finally {
//            if (getDriver() != null) {
//                getDriver().quit();
//                driver.remove();  // Clean up thread-local
//            }
//        }
//    }
//
//
//    @AfterGroups("login")
//    public void tearDownDriver() {
//        WebDriver localDriver = getDriver();
//        if (localDriver != null) {
//            Log.info("Final Driver Cleanup: Closing Browser...");
//            localDriver.quit();
//            driver.remove();
//        } else {
//            Log.warn("Driver was not initialized or already closed.");
//        }
//    }
//}
