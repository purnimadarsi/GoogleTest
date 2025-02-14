package mytests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class SeleniumGridTest {
    public static void main(String[] args) throws MalformedURLException {
        // Use URI.create() to avoid the deprecated constructor
        URL gridUrl = URI.create("http://localhost:4444/wd/hub").toURL();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome"); // Use "firefox" or "edge" if needed

        WebDriver driver = new RemoteWebDriver(gridUrl, capabilities);

        driver.get("https://www.google.com");
        System.out.println("Page Title: " + driver.getTitle());

       // driver.quit();
    }
}
