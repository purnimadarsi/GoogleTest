package mytests;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import SeleniumFactory.WebDriverFactory;

public class GoogleHomePageTest {
	WebDriver driver ;
	WebDriverWait wait;
	
	@BeforeMethod
	public void setUp() {
		driver = WebDriverFactory.getDriver("chrome");
		driver.get("https://www.google.com/");

		wait = new WebDriverWait(driver,Duration.ofSeconds(5));
	}
	
	@Test(enabled=false)
	public void googleTitleTest() {
		String title = driver.getTitle();
		Assert.assertEquals(title, "Google");
	}
	
	@Test(enabled=false)
	public void isTextBoxDisplayed() {
		waitForElementToLoad(By.name("q"));
		boolean displayed = driver.findElement(By.xpath("//textarea[@name='q']")).isDisplayed();
		Assert.assertTrue(displayed);
		
	}
	@Test(enabled=false)
	public void dispaly_Searchbox_Suggestions() {
		driver.findElement(By.xpath("//textarea[@name='q']")).sendKeys("selenium testing");
		waitForElementToLoad(By.cssSelector("ul[role='listbox'] >li"));
		List<WebElement> webElemets = driver.findElements(By.cssSelector("ul[role='listbox'] >li"));
		System.out.println("The search box suggestions are");

		for(WebElement ele:webElemets) {
			System.out.println(ele.getText());
		}

	}
	
	@Test(enabled=false)
	public void verifyAllWindows() {

		String parentWindow = driver.getWindowHandle();
		WebDriver faceBookDriver = driver.switchTo().newWindow(WindowType.TAB);
		faceBookDriver.get("https://www.facebook.com/");

		WebDriver instaDriver = driver.switchTo().newWindow(WindowType.WINDOW);
		instaDriver.get("https://www.instagram.com/");

		Set<String> allWindows = driver.getWindowHandles();
		for(String window:allWindows) {
			if(!window.equals(parentWindow)) {
			driver.switchTo().window(window);
			driver.getCurrentUrl();
			driver.close();
			
			}

		}
	   driver.switchTo().window(parentWindow);
	   // driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//textarea[@name='q']")).sendKeys("selenium testing");

		}
	
	public void waitForElementToLoad(By  locator) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));


	}
	
	
	@AfterMethod
	public void tearDown() {
		//driver.quit();
	}

}
