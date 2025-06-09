package pages;

import base.BaseTest;
import utils.ExtentReportManager;

import org.testng.Assert;

import org.testng.annotations.Test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Lead_Converstion extends BaseTest {
	WebDriver driver;
	WebDriverWait wait;

	public Lead_Converstion(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(100));
		PageFactory.initElements(driver, this); // Initialize elements
	}

	@FindBy(xpath = "//a[@title='Leads' and contains(@class,'slds-context-bar__label-action')]")
	WebElement leadsTab;

	public void convertLead() throws InterruptedException {

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", leadsTab);
		test.get().info("Navigated to Recently Created Leads Table");
		Thread.sleep(4000);
		
		
		// Wait for first lead link and click
		WebElement firstLeadLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"(//table[contains(@class,'slds-table')]//tbody/tr[1]//a[contains(@class,'slds-truncate')])[1]")));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstLeadLink);
		
		test.get().info("Selected the Recently created first lead");
		Thread.sleep(1000);
		
		
		
		By moreActionsButton = By.xpath(
				"//button[contains(@class,'slds-button_icon-border-filled') and .//span[contains(text(),'Show more actions')]]");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		
		// Wait for presence
		WebElement button = wait.until(ExpectedConditions.presenceOfElementLocated(moreActionsButton));

		// Scroll into view (optional but useful)
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
		test.get().info("Clicked on the More action Dropdown");
		
		// Wait for clickable
		wait.until(ExpectedConditions.elementToBeClickable(button));

		// Use JS click to avoid some UI blocking issues
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
		
		

		test.get().info("Scrolled and clicked on the Convert Link");
		// Click "Convert" option
		WebElement convertOption = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//div[contains(@class, 'slds-dropdown')]//a[normalize-space(.)='Convert']")));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", convertOption);
		
		
		Thread.sleep(5000);
		
		// Click "Convert" button on modal
		WebElement convertModalBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//div[contains(@class, 'modal-footer')]//button[normalize-space(text())='Convert' and contains(@class, 'slds-button_brand')]")));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", convertModalBtn);
		Thread.sleep(5000);
		test.get().pass("Lead Created Sucessfully");
		
		test.get().pass("Created new Account, Contact and Opportunity");


//    	// Click "Go to Leads" button on modal
		WebElement GoToLeadsBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//div[contains(@class, 'modal-footer')]//button[normalize-space(text())='Go to Leads' and contains(@class, 'slds-button_brand')]")));
//    	String screenshotPath = ExtentReportManager.captureScreenshot(driver, "Lead Created Sucessfully");
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", GoToLeadsBtn);
		
	}

}
