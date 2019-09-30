package seleniumUITest;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class seleniumTest {
	WebDriver driver;
	WebElement element;

	void lanuchBrowser(String url) {
		System.setProperty("webdriver.gecko.driver", "/Users/ah/Documents/selenium_projects/geckodriver");
		driver = new FirefoxDriver();
		driver.get(url);

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	void clickMyShopTab() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 100);
			element = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-code='QuickShopNavigationNodeLL']")));
		} catch (Exception e) {

			System.out.println("_error:" + e);
		}
	}

	void clickSignInBtn() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 100);
			element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(), 'SIGN IN')]")));
		} catch (Exception e) {

			System.out.println("_error:" + e);
		}
	}

	void fillInForm(String username, String password) {

		try {
			WebElement usernameField = driver.findElement(By.name("email"));
			WebElement newPasswordField = driver.findElement(By.name("newPassword"));
			WebElement acceptTandC = driver.findElement(By.id("termsAndConditions__label"));
			WebElement submitFormBtn = driver.findElement(By.xpath("//*[contains(text(), 'Sign In')]"));

			usernameField.sendKeys(username);
			newPasswordField.sendKeys(password);
			acceptTandC.click();
			submitFormBtn.click();

		} catch (Exception e) {
			System.out.println("_error " + e);
		}
	}

	void verifyFormComplete() {
		try {
			assertEquals("Complete your profile",
					driver.findElement(By.xpath("//*[contains(text(), 'Complete your profile')]")).getText());
		} catch (Exception e) {
			System.out.println("_error " + e);
		}
	}

	@Test
	public static void main(String[] args) {

		seleniumTest obj = new seleniumTest();
		obj.lanuchBrowser("https://www.loblaws.ca/");
		obj.clickMyShopTab();
		obj.clickSignInBtn();
		obj.fillInForm("adamharaz@mail.com", "Password!123");
		obj.verifyFormComplete();

	}

}
