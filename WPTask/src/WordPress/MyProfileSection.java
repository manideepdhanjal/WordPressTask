package WordPress;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MyProfileSection {

	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/ChromeDriverMac/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		Methods met = new Methods();
		
		// Navigating to the WordPress site
		driver.get("https://wordpress.com/");
		
		// Generate random First Name
		String FName = "Manideep" + met.generateRandomString(5);
		
		// Going to the login page
		WebElement logInLink = driver.findElement(By.xpath("//a[@title='Log In' and contains(@class, 'x-nav-link')]"));
		met.waitUntilElementIsVisible(driver, logInLink);
		logInLink.click();
		
		// Entering login details
		WebElement emailAddressField = driver.findElement(By.xpath("//input[@id='usernameOrEmail']"));
		met.waitUntilElementIsVisible(driver, emailAddressField);
		emailAddressField.sendKeys("manideepdhanjal@gmail.com");
		
		WebElement continueButton = driver.findElement(By.xpath("//button[text()='Continue']"));
		met.waitUntilElementIsVisible(driver, continueButton);
		continueButton.click();
		
		WebElement passwordField = driver.findElement(By.id("password"));
		met.waitUntilElementIsVisible(driver, passwordField);
		passwordField.sendKeys("WordPress!");
		
		WebElement logInButton = driver.findElement(By.xpath("//button[text()='Log In']"));
		met.waitUntilElementIsVisible(driver, logInButton);
		logInButton.click();
		
		// Navigating to the My Profile section
		WebElement profileAvatar = driver.findElement(By.xpath("//img[@alt='My Profile']"));
		met.waitUntilElementIsVisible(driver, profileAvatar);
		met.sleepFor(1000);
		profileAvatar.click();
		
		// Checking if the save profile button is disabled or not
		WebElement saveProfileButton = driver.findElement(By.xpath("//button[text()='Save profile details']"));
		met.isButtonEnabled(saveProfileButton);
		// Using TestNG we can assert true or false here
		// Button should be disabled here
		
		// Adding some details
		WebElement firstNameField = driver.findElement(By.id("first_name"));
		met.waitUntilElementIsVisible(driver, firstNameField);
		firstNameField.clear();
		firstNameField.sendKeys(FName);
		
		// Checking if the button is enabled or not
		met.isButtonEnabled(saveProfileButton);
		// Using TestNG we can assert true or false here
		// Button should be enabled here
		
		// Adding other details
		WebElement lastNameField = driver.findElement(By.id("last_name"));
		met.waitUntilElementIsVisible(driver, lastNameField);
		lastNameField.clear();
		lastNameField.sendKeys("Dhanjal");
		
		WebElement aboutMeField = driver.findElement(By.id("description"));
		met.waitUntilElementIsVisible(driver, aboutMeField);
		aboutMeField.clear();
		aboutMeField.sendKeys("I'm and automation engineer");
		
		// Saving details
		saveProfileButton.click();
		WebElement notificationSettingsSaved = driver.findElement(By.xpath("//span[text()='Settings saved successfully!']"));
		met.isSaveSuccessful(notificationSettingsSaved);
		// Using TestNG we can assert true or false here
		// Notification should be displayed here
		
		// Verify the information was saved
		met.waitUntilElementIsVisible(driver, firstNameField);
		met.doesTheFieldHaveData(firstNameField, FName);
		met.doesTheFieldHaveData(lastNameField, "Dhanjal");
		
		// Add Profile links
		WebElement addProfileLinksButton = driver.findElement(By.xpath("//button//span[text()='Add']"));
		addProfileLinksButton.click();
		WebElement addURLLink = driver.findElement(By.xpath("//button[text()='Add URL']"));
		met.waitUntilElementIsVisible(driver, addURLLink);
		addURLLink.click();
		WebElement addSiteButton = driver.findElement(By.xpath("//button[text()='Add Site']"));
		met.isButtonEnabled(addSiteButton);
		// Button should be disabled here
		
		// Add profile Description
		WebElement descriptionField = driver.findElement(By.xpath("//input[@placeholder='Enter a description']"));
		descriptionField.clear();
		descriptionField.sendKeys("Random Test Description");
		
		// Add Invalid Profile URL
		WebElement URLField = driver.findElement(By.xpath("//input[@placeholder='Enter a URL']"));
		met.waitUntilElementIsVisible(driver, URLField);
		URLField.clear();
		URLField.sendKeys("invalid url");
		met.isButtonEnabled(addSiteButton);
		// Button should be disabled here
		
		// Add Valid Profile URL
		String profileData = "MyTestURL.com" + met.generateRandomString(5);
		URLField.clear();
		URLField.sendKeys(profileData);
		met.isButtonEnabled(addSiteButton);
		// Button should be enabled here
		addSiteButton.click();
		met.refresh(driver);
		
		// Verify profile was added
		WebElement profileDivContent = driver.findElement(By.xpath("//p[text()='Manage which sites appear in your profile.']//parent::div//parent::div"));
		met.profileHasData(profileDivContent, profileData);
		
		// Click my Gravatar Slider
		WebElement GravatarSlider = driver.findElement(By.xpath("//input[contains(@id, 'inspector-toggle-control')]"));
		GravatarSlider.click();
		WebElement saveProfileeButton = driver.findElement(By.xpath("//button[text()='Save profile details']"));
		met.waitUntilElementIsVisible(driver, saveProfileeButton);
		met.scrollTo(driver, saveProfileeButton);
		saveProfileeButton.click();
		//met.refresh(driver);
		
		// Verify Slider status
		WebElement hideGravatarSliderStatus = driver.findElement(By.xpath("//input[contains(@id, 'inspector-toggle-control')]//parent::span"));
		met.isGravatarSliderChecked(hideGravatarSliderStatus);
		// Slider should be checked or unchecked here depending upon the run
		
		// Close browser
		driver.quit();

	}
	
}
