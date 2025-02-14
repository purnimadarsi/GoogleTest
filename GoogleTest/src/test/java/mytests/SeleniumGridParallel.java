package mytests;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.URI;
import java.net.URL;

public class SeleniumGridParallel {
    private WebDriver driver;

    @BeforeTest
    @Parameters("browser")
    public void setup(String browser) throws Exception {
        URL gridUrl = URI.create("http://localhost:4444/wd/hub").toURL();
        DesiredCapabilities capabilities = new DesiredCapabilities();

        switch (browser.toLowerCase()) {
            case "chrome":
                capabilities.setBrowserName("chrome");
                break;
            case "firefox":
                capabilities.setBrowserName("firefox");
                break;
            case "edge":
                capabilities.setBrowserName("MicrosoftEdge");
                break;
            default:
                throw new IllegalArgumentException("Browser not supported: " + browser);
        }
        try {

        driver = new RemoteWebDriver(gridUrl, capabilities);
        }catch (Exception e) {
        	e.printStackTrace();
        }
    }

    @Test
    public void testGoogle() {
        driver.get("https://www.google.com");
        System.out.println("Title [" + driver.getTitle() + "] - Browser: " + ((RemoteWebDriver) driver).getCapabilities().getBrowserName());
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
