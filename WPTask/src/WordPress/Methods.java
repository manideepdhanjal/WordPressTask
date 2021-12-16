package WordPress;

import java.util.Random;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Methods {
	
	public void waitUntilElementIsVisible(WebDriver drv, WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(drv, 10);
			wait.until(ExpectedConditions.visibilityOf(element));
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public Boolean isButtonEnabled(WebElement button) {
		try {
			if(button.getAttribute("disabled").toString().equalsIgnoreCase("true")) {
				System.out.println("Button is disabled");
				return false;
			}
		}
		catch(Exception e) {
			System.out.println("Button is enabled");
			return true;
		}
		System.out.println("Button is enabled");
		return true;
	}
	
	public void scrollTo(WebDriver drv, WebElement element) {
		((JavascriptExecutor) drv).executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public void sleepFor(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public Boolean isSaveSuccessful(WebElement notification) {
		sleepFor(1000);
		if(notification.isDisplayed()) {
			System.out.println("Save successful");
			return true;
		}
		else {
			System.out.println("Saving changes failed");
			return false;
		}
	}
	
	public Boolean areSitesAvailable(WebElement profileSection) {
		sleepFor(1000);
		try {
			if(profileSection.getText().contains("You have no sites in your profile links")) {
				System.out.println("No Sites Available");
				return false;
			}
		}
		catch(Exception e) {
			System.out.println("Sites are available");
			return true;
		}
		System.out.println("Sites are available");
		return true;
	}
	
	public Boolean doesTheFieldHaveData(WebElement field, String data) {
		if(field.getAttribute("value").contains(data)) {
			System.out.println("Field has data " + data);
			return true;
		}
		else {
			System.out.println("Field has wrong data - " + field.getAttribute("value"));
			return false;
		}
	}
	
	public String generateRandomString(int length) {
		String str = "";
		Random ran = new Random();
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		for (int i = 0; i < length; i++) {
			str = str + (alphabet.charAt(ran.nextInt(alphabet.length())));
		}
		return str;
	}
	
	public Boolean profileHasData(WebElement div, String data) {
		if(div.getText().contains(data)) {
			System.out.println("Profile has data " + data);
			return true;
		}
		else {
			System.out.println("Profile has wrong data - " + div.getText());
			return false;
		}
	}
	
	public Boolean isGravatarSliderChecked(WebElement slider) {
		sleepFor(1000);
		if(slider.getAttribute("class").contains("is-checked")) {
			System.out.println("Slider is checked");
			return true;
		}
		else {
			System.out.println("Slider is unchecked");
			return false;
		}
	}
	
	public void refresh(WebDriver drv) {
		drv.navigate().refresh();
		sleepFor(1000);
	}

}
