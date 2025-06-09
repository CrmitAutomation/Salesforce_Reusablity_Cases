package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
//
//import utils.Log;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.util.Assert;

import utils.ExtentReportManager;

import org.openqa.selenium.interactions.Actions;

public class Lead_Creation {

	WebDriver driver;
    WebDriverWait wait;
    protected static ThreadLocal<ExtentTest> test = new ThreadLocal<>();


	   public Lead_Creation(WebDriver driver) {
	        this.driver = driver;
	        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	        PageFactory.initElements(driver, this); // Initialize elements
	    }

	    @FindBy(xpath = "//a[@title='Leads' and contains(@class,'slds-context-bar__label-action')]")
	    WebElement leadsTab;

	    @FindBy(xpath = "//ul[contains(@class,'oneActionsRibbon')]//a[@title='New' and contains(@class,'forceActionLink')]")
	    WebElement newButton;

	    @FindBy(xpath = "//input[@placeholder='First Name']")
	    WebElement firstName;

	    @FindBy(xpath = "//input[@placeholder='Last Name']")
	    WebElement lastName;

	    @FindBy(xpath = "//input[@name='Company']")
	    WebElement company;
	    
	    @FindBy(name = "email")
	    WebElement email;

	    @FindBy(name = "phone")
	    WebElement phone;

	    @FindBy(xpath = "//button[@name='SaveEdit']")
	    WebElement saveBtn;
	    
	    @FindBy(xpath = "//button[contains(@class,'slds-button_icon-border-filled']")
	    WebElement moreAction;

	    @FindBy(xpath = "//button[@title='Convert']")
	    WebElement convertBtn;

	    @FindBy(xpath = "//span[contains(text(),'Created Date')]/ancestor::a")
	    WebElement createdDateHeader;

	    @FindBy(xpath = "//table[contains(@class, 'slds-table')]")
	    WebElement leadsTable;

	    @FindBy(xpath = "//table//tr[.//span[contains(text(),'Open')]][1]")
	    WebElement firstOpenLeadRow;

	    @FindBy(xpath = ".//button[contains(@class,'slds-button_icon-border-filled')]")
	    WebElement dropdownButtonInRow;

	    @FindBy(xpath = "//a[@role='menuitem' and normalize-space()='Convert']")
	    WebElement convertOption;

	    @FindBy(xpath = "//button[@name='Convert']")
	    WebElement convertModalBtn;
	    
//	    public void safeClick(WebElement leadsTab2) {
//	        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(leadsTab2));
//	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", el);
//	        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
//	    }
	    
	    public void leadTabClick() {
	    	WebElement el = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='Leads' and contains(@class,'slds-context-bar__label-action')]")));
	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", el);
	        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
	        test.get().info("Navigated To Lead Section");
	    }
	    
	    
	    
	    public void createLead(String fName, String lName, String comp, String emailAddr, String phoneNo) throws InterruptedException{
	    	// Click Leads tab
	    	System.out.println("Lead details: " + fName + ", " + lName + ", " + comp + ", " + emailAddr + ", " + phoneNo);

//	    	safeClick(leadsTab);
//			test.get().info("Navigated To Lead Section");
//			Thread.sleep(5000);
//			safeClick(newButton);
			
//	    	test.get().info("Creating New Lead");
//	    	Thread.sleep(3000);
//	        firstName.sendKeys(fName);
//	        lastName.sendKeys(lName);
//	        company.sendKeys(comp);
//	        email.sendKeys(emailAddr);
//	        phone.sendKeys(phoneNo);
//	        saveBtn.click();
	    	 // Wait for spinner to disappear
	        
	        
	        try {
	        	leadTabClick();
	            // Click New button
	            WebElement newBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='New']")));
	            newBtn.click();
				test.get().info("Navigated To Lead Section");

	            // Fill form fields
	            driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys(fName);
	            driver.findElement(By.xpath("//input[@name='lastName']")).sendKeys(lName);
	            driver.findElement(By.xpath("//input[@name='Company']")).sendKeys(comp);
	            driver.findElement(By.name("email")).sendKeys(emailAddr);
	            driver.findElement(By.name("phone")).sendKeys(phoneNo);

	            // Click Save
	            driver.findElement(By.xpath("//button[@title='Save']")).click();
	            
	            By spinner = By.cssSelector(".forceModalSpinner"); // adjust if custom spinner
		        wait.until(ExpectedConditions.invisibilityOfElementLocated(spinner));
		        test.get().pass("Created New Lead");
		        Thread.sleep(5000); // Optional – use only if list view doesn't update immediately
	        } catch (Exception e) {
	            e.printStackTrace();
	            ExtentReportManager.captureScreenshot(driver, "Lead Creation Failed");
//	            Assert.fail("Lead creation failed due to: " + e.getMessage());
	        }

	    }
	    
		
}
