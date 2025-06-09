package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.apache.hc.client5.http.utils.Base64;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import utils.EncryptAndDecrypt;
import utils.Log;

public class LoginPage {
	private WebDriver driver;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	@FindBy(id = "username")
	WebElement usernameTextbox;

	@FindBy(id = "password")
	WebElement passwordTextbox;

	@FindBy(id = "Login")
	WebElement loginButton;

	@FindBy(xpath = "//a[@title='Switch to Lightning Experience' and contains(@class, 'menuButtonMenuLink')]")
	WebElement switchToLightning;

	@FindBy(xpath = "//a[contains(text(), 'Remind Me Later')]")
	WebElement remindMe;

//	private By usernameTextBox = By.id("Email");
//	private By passwordTextBox = By.id("Password");
//	private By loginButton = By.xpath("//*[@id=\"main\"]/div/div/div/div[2]/div[1]/div/form/div[3]/button");

	public LoginPage(WebDriver driver) {
		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	public void enterUsername(String username) {

		usernameTextbox.clear();
		usernameTextbox.sendKeys(username);
//		driver.findElement(usernameTextBox).clear();
//		driver.findElement(usernameTextBox).sendKeys(username);
	}

	public void enterPassword(String password) {

		passwordTextbox.clear();
		passwordTextbox.sendKeys(password);
//		driver.findElement(passwordTextBox).clear();
//		driver.findElement(passwordTextBox).sendKeys(password);
	}

	public void clickLogin() {

		Log.info("Clicking login button..");
		loginButton.click();
//		driver.findElement(loginButton).click();	
	}

	public void clickOnSwitchToLightning() throws InterruptedException {
		// --- Optional: Wait for the homepage to load and user menu to appear ---
		Thread.sleep(2000); // Replace with WebDriverWait in production

		// --- Open the User Menu if needed (click on user nav button) ---
		WebElement userNav = driver.findElement(By.id("userNavButton"));
		userNav.click();

		// --- Wait for the menu to expand ---
		Thread.sleep(2000); // Replace with WebDriverWait if preferred

		// --- Click the "Switch to Lightning Experience" link ---
		
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", switchToLightning);

	
	}

	public void clickOnRemindMe() {
		remindMe.click();
		System.out.println("Clicking remindMe button..");
	}
	
}
